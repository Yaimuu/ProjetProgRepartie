package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.Action;
import fr.polytech.projetprogrepartiapi.entities.Indicator;
import fr.polytech.projetprogrepartiapi.entities.SimulationResponse;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.ActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.IndicatorRepository;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.ActionService;
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
import java.util.List;
import java.util.Random;

@RequestMapping(value = "/api")
@RestController
public class ActionController {
    private final Logger logger = LoggerFactory.getLogger(ActionController.class);
    private final ActionRepository actionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final IndicatorRepository indicatorRepository;
    private final InscriptionActionRepository inscriptionActionRepository;

    public ActionController(ActionRepository actionRepository, UtilisateurRepository utilisateurRepository, IndicatorRepository indicatorRepository, InscriptionActionRepository inscriptionActionRepository) {

        this.actionRepository = actionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.indicatorRepository = indicatorRepository;
        this.inscriptionActionRepository = inscriptionActionRepository;
    }

    @GetMapping(value = "/actions")
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

    @GetMapping(value = "/action/simuler/{idAction}")
    public ResponseEntity<Object> simulerAction(@PathVariable int idAction, HttpServletRequest request){
        ActionService actionService = new ActionService(actionRepository);
        UtilisateurService utilisateurService = new UtilisateurService(utilisateurRepository);
        Utilisateur u = utilisateurService.getAutenticatedUtilisateur(request);
        if(u != null) {

            Random rand = new Random();

            List<Indicator> indicators = indicatorRepository.findAllByActionId(idAction);

            int score = 0;

            for (Indicator i : indicators) {
                boolean isIndicatorChecked = rand.nextBoolean();

                score += isIndicatorChecked ? i.getValueIfCheck() : i.getValueIfUnCheck();

                i.setChecked(isIndicatorChecked);
            }
            Action action = actionService.getActionById(idAction).get();
            SimulationResponse simulationResponse =
                    new SimulationResponse(action, score >= action.getScoreMinimum(),
                    score, indicators);



            return ResponseEntity.ok(simulationResponse);
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
