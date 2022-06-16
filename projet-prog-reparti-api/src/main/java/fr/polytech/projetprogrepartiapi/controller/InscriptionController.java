package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
public class InscriptionController {
    private final Logger logger = LoggerFactory.getLogger(InscriptionController.class);
    private final InscriptionRepository inscriptionRepository;

    public InscriptionController(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }


}
