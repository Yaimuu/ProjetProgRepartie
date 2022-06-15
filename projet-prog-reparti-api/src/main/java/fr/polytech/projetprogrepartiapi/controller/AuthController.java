package fr.polytech.projetprogrepartiapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fr.polytech.projetprogrepartiapi.entities.LoginRequest;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.AuthentificationService;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import net.bytebuddy.implementation.bind.annotation.DefaultMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping(value = "/api/auth")
@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
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

//        logger.info(requestString);
        logger.info(message);


        return ResponseEntity.ok(utilisateur);
    }
}
