package fr.polytech.projetprogrepartiapi.controller;


import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UtilisateurController {

    private final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {

        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping(value={"/api/user/all", "/api/users"})
    public ResponseEntity<Object> getAllUsers(HttpServletRequest request) {
        logger.info("GET user/all");

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            UtilisateurService uService = new UtilisateurService(utilisateurRepository);
            if(uService.isAdmin((int) session.getAttribute("id")))
                return ResponseEntity.ok(uService.getAllUtilisateurs());
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id, HttpServletRequest request) {
        logger.info("GET user/" + id);

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            UtilisateurService uService = new UtilisateurService(utilisateurRepository);
            if(uService.isAdmin((int) session.getAttribute("id")) || session.getAttribute("id").equals(id))
                return ResponseEntity.ok(uService.getUtilisateurById(id));
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
