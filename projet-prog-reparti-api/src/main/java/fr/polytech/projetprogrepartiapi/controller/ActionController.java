package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.*;
import fr.polytech.projetprogrepartiapi.repositories.*;
import fr.polytech.projetprogrepartiapi.service.ActionService;
import fr.polytech.projetprogrepartiapi.service.InscriptionActionService;
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
    private final InscriptionRepository inscriptionRepository;

    public ActionController(ActionRepository actionRepository, UtilisateurRepository utilisateurRepository, IndicatorRepository indicatorRepository, InscriptionActionRepository inscriptionActionRepository, InscriptionRepository inscriptionRepository) {

        this.actionRepository = actionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.indicatorRepository = indicatorRepository;
        this.inscriptionActionRepository = inscriptionActionRepository;
        this.inscriptionRepository = inscriptionRepository;
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

    @GetMapping(value = "/action/simuler/{idAction}/{idInscription}")
    public ResponseEntity<Object> simulerAction(@PathVariable int idAction, @PathVariable int idInscription, HttpServletRequest request){
        ActionService actionService = new ActionService(actionRepository);
        UtilisateurService utilisateurService = new UtilisateurService(utilisateurRepository);
        InscriptionService inscriptionService = new InscriptionService(inscriptionRepository);
        InscriptionActionService inscriptionActionService = new InscriptionActionService(inscriptionActionRepository);

        Utilisateur u = utilisateurService.getAutenticatedUtilisateur(request);

        if(!actionService.getActionById(idAction).isPresent() || !inscriptionService.getInscriptionById(idInscription).isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Action action = actionService.getActionById(idAction).get();
        InscriptionAction inscriptionAction = inscriptionActionService.getInscriptionActionFromValue(inscriptionService.getInscriptionById(idInscription).get(), action);

        if(u != null) {

            Random rand = new Random();

            List<Indicator> indicators = indicatorRepository.findAllByActionId(idAction);
            Action actionPrec = action.getActionByFkAction();
            if(actionPrec!=null){
                InscriptionAction inscriptionActionPrec = inscriptionActionService.getInscriptionActionFromValue(inscriptionService.getInscriptionById(idInscription).get(), actionPrec);
                if(inscriptionActionPrec!=null && inscriptionActionPrec.getScore()==null){
                    return new ResponseEntity("Action précédente non réalisée", HttpStatus.BAD_REQUEST);
                }
            }

            int score = 0;

            for (Indicator i : indicators) {
                boolean isIndicatorChecked = rand.nextBoolean();
                score += isIndicatorChecked ? i.getValueIfCheck() : i.getValueIfUnCheck();
                i.setChecked(isIndicatorChecked);
            }

            SimulationResponse simulationResponse =
                    new SimulationResponse(action, score >= action.getScoreMinimum(),
                    score, indicators);

            inscriptionAction.setScore(score);
            inscriptionActionService.createInscription(inscriptionAction);

            return ResponseEntity.ok(simulationResponse);
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
