package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.repositories.ActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
public class ActionController {
    private final Logger logger = LoggerFactory.getLogger(ActionController.class);
    private final ActionRepository actionRepository;

    public ActionController(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }
}
