package fr.polytech.projetprogrepartiapi.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
    @Autowired
    private AuthentificationService authentificationService;
    private final UtilisateurRepository utilisateurRepository;

    public AuthController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @RequestMapping("/api/login")
    public ResponseEntity<Object> login(HttpServletRequest request, HttpServletResponse response) {
        logger.info("GET login");

        if (!"POST".equalsIgnoreCase(request.getMethod()))
            return new ResponseEntity("Only POST requests are allowed !", HttpStatus.BAD_REQUEST);

        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        String test = "";
        try {
            test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info(test);

        //TODO: Login


        return ResponseEntity.ok(test);
//        return ResponseEntity.ok(uService.getUtilisateurById());
    }
}
