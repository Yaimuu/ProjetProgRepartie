package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
public class InscriptionController {
    private final Logger logger = LoggerFactory.getLogger(InscriptionController.class);
    private final InscriptionRepository inscriptionRepository;

    public InscriptionController(InscriptionRepository inscriptionRepository, UtilisateurRepository utilisateurRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @GetMapping("/api/inscriptions")
    List<Inscription> getAllInscription(){
        return this.inscriptionRepository.findAll();
    }

    @GetMapping("api/inscription/{id}")
    Inscription getInscriptionById(@PathVariable int id){
        Optional<Inscription> inscription = this.inscriptionRepository.findById(id);
        Inscription i = null;
        if(inscription.isPresent()){
            i = inscription.get();
        }
        return i;

    }


}
