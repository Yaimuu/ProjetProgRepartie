package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.InscriptionAction;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import fr.polytech.projetprogrepartiapi.service.InscriptionActionService;
import fr.polytech.projetprogrepartiapi.service.InscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.desktop.OpenFilesEvent;
import java.util.Collection;
import java.util.Optional;
@RestController
public class InscriptionActionController {
    private final Logger logger = LoggerFactory.getLogger(InscriptionActionController.class);
    private final InscriptionActionRepository inscriptionActionRepository;
    private final InscriptionRepository inscriptionRepository;

    public InscriptionActionController(InscriptionActionRepository inscriptionActionRepository, InscriptionRepository inscriptionRepository) {
        this.inscriptionActionRepository = inscriptionActionRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @GetMapping("api/inscription/{idInscription}/actions")
    public ResponseEntity<Object> getInscriptionsActions(@PathVariable int idInscription){
        logger.info("GET InscriptionsAction/"+idInscription);
        InscriptionActionService inscriptionActionService = new InscriptionActionService(inscriptionActionRepository);
        InscriptionService inscriptionService = new InscriptionService(inscriptionRepository);
        Optional<Inscription> tryinscription = inscriptionService.getInscriptionById(idInscription);
        if(tryinscription.isPresent()){
            Inscription inscription = tryinscription.get();
            Collection<InscriptionAction> inscriptionActions = inscriptionActionService.getInscriptionsActionsFromInscription(inscription);
            return ResponseEntity.ok(inscriptionActions);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    
}
