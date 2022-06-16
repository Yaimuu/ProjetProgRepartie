package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.repositories.InscriptionActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InscriptionActionController {
    private final Logger logger = LoggerFactory.getLogger(InscriptionActionController.class);
    private final InscriptionActionRepository inscriptionActionRepository;

    public InscriptionActionController(InscriptionActionRepository inscriptionActionRepository) {
        this.inscriptionActionRepository = inscriptionActionRepository;
    }

    
}
