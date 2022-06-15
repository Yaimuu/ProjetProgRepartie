package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.repositories.MissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api")
@RestController
public class MissionController {
    private final Logger logger = LoggerFactory.getLogger(MissionController.class);
    private final MissionRepository missionRepository;

    public MissionController(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }


}
