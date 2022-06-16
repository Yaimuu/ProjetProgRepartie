package fr.polytech.projetprogrepartiapi.controller;


import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.InscriptionService;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        logger.info("error");

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

    @PostMapping("/api/user/remove/{id}")
    public ResponseEntity<Object> removeUser(@PathVariable int id, HttpServletRequest request) {
        logger.info("POST user/remove/" + id);

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            UtilisateurService uService = new UtilisateurService(utilisateurRepository);
            if(uService.isAdmin((int) session.getAttribute("id")))
            {
                Optional<Utilisateur> utilisateurToRemove = uService.getUtilisateurById(id);

                if(!utilisateurToRemove.isPresent())
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);

                uService.deleteUtilisateur(utilisateurToRemove.get());
                return ResponseEntity.ok(uService.getUtilisateurById(id));
            }
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/api/user/{id}/inscriptions")
    public ResponseEntity<Object> getInscriptionsForUser(@PathVariable int id, HttpServletRequest request) {
        logger.info("GET user/" +id+"/inscriptions" );

        HttpSession session = request.getSession();
        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        Utilisateur u = uService.getAutenticatedUtilisateur(request);
        if(u != null)
        {
            logger.info("Authentied user found");
            if(uService.isAdmin(u.getNumUtil()) || u.getNumUtil() == id){
                logger.info("Authorization granted");
                Optional<Utilisateur> uconcerne = uService.getUtilisateurById(id);
                if(uconcerne.isPresent()){
                    logger.info("User found");
                    List<Inscription> inscriptionsUser = uService.getAllInscriptionsFromUser(uconcerne.get());
                    List<Inscription> inscriptions = new ArrayList<>();
                    for(Inscription i: inscriptionsUser){
                        logger.info(i.getMissionByFkMission().getWording());
                        inscriptions.add(i);
                    }
                    return ResponseEntity.ok(inscriptions);
                }
            }
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
