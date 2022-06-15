package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.*;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value={"/api/home", "/api"})

public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final UtilisateurRepository utilisateurRepository;

    public HomeController(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }
    /**
     * @return ResponseEntity Home
     */
    @GetMapping
    public ResponseEntity<Object> home(HttpServletRequest request) {
        logger.info("GET home");

        UtilisateurService uService = new UtilisateurService(utilisateurRepository);

        return ResponseEntity.ok(uService.getAutenticatedUtilisateur(request));
    }

}
