package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.Mission;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.MissionRepository;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.MissionService;
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
import java.util.List;
import java.util.Optional;


@RestController
public class MissionController {
    private final Logger logger = LoggerFactory.getLogger(MissionController.class);
    private final MissionRepository missionRepository;
    private final UtilisateurRepository utilisateurRepository;

    public MissionController(MissionRepository missionRepository, UtilisateurRepository utilisateurRepository) {
        this.missionRepository = missionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping(value = "/api/missions")
    public ResponseEntity<Object> getInscriptionsForUser(HttpServletRequest request) {
        logger.info("GET user/missions" );

        HttpSession session = request.getSession();
        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        Utilisateur u = uService.getAutenticatedUtilisateur(request);
        if(u != null)
        {
            MissionService missionService = new MissionService(missionRepository);
            List<Mission> missions = missionService.getAllMissions();
            return ResponseEntity.ok(missions);

        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
