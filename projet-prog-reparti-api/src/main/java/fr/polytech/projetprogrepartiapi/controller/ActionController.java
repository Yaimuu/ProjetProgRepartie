package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.Action;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.ActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.ActionService;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class ActionController {
    private final Logger logger = LoggerFactory.getLogger(ActionController.class);
    private final ActionRepository actionRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ActionController(ActionRepository actionRepository, UtilisateurRepository utilisateurRepository) {

        this.actionRepository = actionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping(value = "/api/actions")
    public ResponseEntity<Object> getAllActions(HttpServletRequest request){
        ActionService actionService = new ActionService(actionRepository);
        UtilisateurService utilisateurService = new UtilisateurService(utilisateurRepository);
        Utilisateur u = utilisateurService.getAutenticatedUtilisateur(request);
        if(u != null){
            List<Action> actions = actionService.getAllActions();
            return ResponseEntity.ok(actions);
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

}
