package fr.polytech.projetprogrepartiapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fr.polytech.projetprogrepartiapi.entities.LoginRequest;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.helpers.PasswordHelper;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.AuthentificationService;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import net.bytebuddy.implementation.bind.annotation.DefaultMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping(value = "/api/auth")
@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthentificationService authentificationService;
    private final UtilisateurRepository utilisateurRepository;

    public AuthController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Object> login(HttpServletRequest request, HttpServletResponse response) {
        logger.info("GET login");

        String message = "";

        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        String requestString = "";
        LoginRequest loginRequest = new LoginRequest();
        Utilisateur utilisateur = null;
        Map<String, Object> mappedRequest = new HashMap<>();

        HttpSession session;
        try {
            requestString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            mappedRequest = mapper.readValue(requestString, Map.class);

            if(mappedRequest.get("login") == null || mappedRequest.get("password") == null)
                return new ResponseEntity("Incorrect format !", HttpStatus.BAD_REQUEST);

            loginRequest.setNomUtil((String) mappedRequest.get("login"));
            loginRequest.setMotPasse((String) mappedRequest.get("password"));

            message = "Tentative de connexion au compte de " + loginRequest.getNomUtil() + "... ";
            logger.info((String) mappedRequest.get("login"));
            logger.info(message);
            logger.debug(loginRequest.toString());

            //TODO: Login
            utilisateur = authentificationService.authentification( loginRequest );

            if (utilisateur != null) {
                message = "Mot de passe correct";
                session = request.getSession();
                session.setAttribute("id", utilisateur.getNumUtil());
                logger.info((String) session.toString());
                logger.info(session.getAttribute("id").toString());
            } else {
                message = "Mot de passe incorrect";
                request.setAttribute("message", message);
                return new ResponseEntity("Incorrect login or password !", HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info(message);


        return ResponseEntity.ok(utilisateur);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            logger.info("logout");
            try {
                request.logout();
            }
            catch (Exception e) { e.printStackTrace(); }
            session.invalidate();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity register(HttpServletRequest request, HttpServletResponse response) {

        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        String requestString = "";
        Map<String, Object> mappedRequest = new HashMap<>();

        try {
            requestString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            mappedRequest = mapper.readValue(requestString, Map.class);
        } catch (Exception e) { e.printStackTrace(); }

        if(mappedRequest.get("password") == null || mappedRequest.get("login") == null ||
                mappedRequest.get("surname") == null || mappedRequest.get("email") == null || mappedRequest.get("forename") == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        byte[] salt = PasswordHelper.GenerateSalt();
        byte[] password = PasswordHelper.generatePasswordHash(mappedRequest.get("password").toString().toCharArray(), salt);
        long id = uService.getNumberUtilisateurs() + 1;

        Utilisateur userToRegister = new Utilisateur((int) id,
                (String) mappedRequest.get("login"),
                PasswordHelper.bytesToString(password),
                PasswordHelper.bytesToString(salt),
                "learner");


        userToRegister.setForename(mappedRequest.get("forename").toString());
        userToRegister.setSurname(mappedRequest.get("surname").toString());
        userToRegister.setEmail(mappedRequest.get("email").toString());
        uService.createUtilisateur(userToRegister);
        return new ResponseEntity(HttpStatus.OK);
    }
}
