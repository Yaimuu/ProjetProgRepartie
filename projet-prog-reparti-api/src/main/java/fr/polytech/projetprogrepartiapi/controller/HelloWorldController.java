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
@RequestMapping("/api/hello")
public class HelloWorldController {
	private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
	private final UtilisateurRepository utilisateurRepository;

	public HelloWorldController(UtilisateurRepository utilisateurRepository){
		this.utilisateurRepository = utilisateurRepository;
	}
	/**
	 * @return ResponseEntity Hello World
	 */
	@GetMapping
	public ResponseEntity<String> helloWorldTest() {
		UtilisateurService uService = new UtilisateurService(utilisateurRepository);
		int id = 2;
		Optional<Utilisateur> u = uService.getUtilisateurById(id);
		boolean value = uService.utilisateurExists(id);
		logger.info("GET helloWorldTest");
		String message = "\"Hello World 5!\"";

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
