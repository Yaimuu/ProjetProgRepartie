package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.InscriptionService;
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
public class InscriptionController {
    private final Logger logger = LoggerFactory.getLogger(InscriptionController.class);
    private final InscriptionRepository inscriptionRepository;
    private final UtilisateurRepository utilisateurRepository;

    public InscriptionController(InscriptionRepository inscriptionRepository, UtilisateurRepository utilisateurRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }
    @GetMapping("/api/inscriptions/{userid}")
    public ResponseEntity<Object> getInscriptionsForUser(@PathVariable int userid, HttpServletRequest request) {
        logger.info("GET inscriptions/" + userid);

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            UtilisateurService uService = new UtilisateurService(utilisateurRepository);
            if(uService.isAdmin((int) session.getAttribute("id")) || session.getAttribute("id").equals(userid)){
                InscriptionService inscriptionService = new InscriptionService(inscriptionRepository);
                // return ResponseEntity.ok();
            }

        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

}
