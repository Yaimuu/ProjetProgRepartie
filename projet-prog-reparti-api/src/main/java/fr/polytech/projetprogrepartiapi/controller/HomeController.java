package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.*;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/home")

public class HomeController {
    private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    private final UtilisateurRepository utilisateurRepository;

    public HomeController(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }
    /**
     * @return ResponseEntity Home
     */
    @GetMapping
    public ResponseEntity<String> home() {
        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        int id = -1;
        Optional<Utilisateur> u = uService.getUtilisateurById(id);
        boolean value = uService.utilisateurExists(id);
        logger.info("GET home");
        String message = "\"Utilisateur non connecté !\"";

        if(u.isPresent()){
            message = "Je m'appelle "+ u.get().getNomUtil() + "!";
            logger.info(message);
            Collection<Inscription> inscriptions = u.get().getInscriptionsById();
            for(Inscription i: inscriptions){
                message +=i.getDate().toString();
                logger.info(i.getDate().toString());
                logger.info(i.getUtilisateurByFkUser().getNomUtil());
                Collection<InscriptionAction> actionsInscriptions = i.getInscriptionActionsById();
                for(InscriptionAction ia: actionsInscriptions){
                    Action action = ia.getActionByFkAction();
                    logger.info(action.getWording());
                    Collection<Indicator> indicators = action.getIndicatorsById();
                    for(Indicator indic: indicators){
                        logger.info(indic.getWording());
                    }
                }
                logger.info("MissionId: "+((Integer)i.getMissionByFkMission().getId()).toString());
                Collection<Action> actions = i.getMissionByFkMission().getActionsById();
                for(Action a: actions){
                    logger.info(a.getWording());
                }
            }
            message = "\"" +message + "\"";
        }
        return ResponseEntity.ok(message);
    }

}
