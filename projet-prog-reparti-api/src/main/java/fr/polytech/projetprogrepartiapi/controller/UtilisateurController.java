package fr.polytech.projetprogrepartiapi.controller;


import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {

    private final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {

        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping(value={"/api/user/all", "/api/users"})
    public ResponseEntity<Object> getAllUsers() {
        logger.info("GET user/all");

        UtilisateurService uService = new UtilisateurService(utilisateurRepository);

        return ResponseEntity.ok(uService.getAllUtilisateurs());
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id) {
        logger.info("GET user/" + id);

        UtilisateurService uService = new UtilisateurService(utilisateurRepository);

        return ResponseEntity.ok(uService.getUtilisateurById(id));
    }
}
