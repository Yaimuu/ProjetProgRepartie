package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.repositories.InscriptionActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InscritpionActionController {
    private final Logger logger = LoggerFactory.getLogger(InscritpionActionController.class);
    private final InscriptionActionRepository inscriptionActionRepository;

    public InscritpionActionController(InscriptionActionRepository inscriptionActionRepository) {
        this.inscriptionActionRepository = inscriptionActionRepository;
    }

    
}
