package fr.polytech.projetprogrepartiapi.controller;

import fr.polytech.projetprogrepartiapi.entities.*;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import fr.polytech.projetprogrepartiapi.repositories.MissionRepository;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.InscriptionActionService;
import fr.polytech.projetprogrepartiapi.service.InscriptionService;
import fr.polytech.projetprogrepartiapi.service.MissionService;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
public class InscriptionController {
    private final Logger logger = LoggerFactory.getLogger(InscriptionController.class);
    private final InscriptionRepository inscriptionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final MissionRepository missionRepository;
    private final InscriptionActionRepository inscriptionActionRepository;

    public InscriptionController(InscriptionRepository inscriptionRepository, UtilisateurRepository utilisateurRepository,
                                 MissionRepository missionRepository, InscriptionActionRepository inscriptionActionRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.missionRepository = missionRepository;
        this.inscriptionActionRepository = inscriptionActionRepository;
    }

    @GetMapping("/api/inscriptions")
    ResponseEntity<Object> getAllInscription(){
        return ResponseEntity.ok(this.inscriptionRepository.findAll());
    }

    @GetMapping("api/inscription/{id}")
    ResponseEntity<Object> getInscriptionById(@PathVariable int id){
        Optional<Inscription> inscription = this.inscriptionRepository.findById(id);
        Inscription i = null;
        if(inscription.isPresent()){
            i = inscription.get();
        }
        return ResponseEntity.ok(i);
    }

    @PostMapping("api/inscription/register/{userid}/{missionid}")
    ResponseEntity<Object> registerUserToMission(@PathVariable int userid, @PathVariable int missionid, HttpServletRequest request){
        UtilisateurService utilisateurService = new UtilisateurService(utilisateurRepository);
        Utilisateur u = utilisateurService.getAutenticatedUtilisateur(request);
        if(u!=null && (u.isAdmin() || u.getNumUtil()==userid)){
            LocalDate date = LocalDate.now();
            MissionService missionService = new MissionService(missionRepository);
            Optional<Mission> trymission = missionService.getMissionById(missionid);
            Optional<Utilisateur> tryUtilisateur = utilisateurService.getUtilisateurById(userid);

            if(!tryUtilisateur.isPresent() || !trymission.isPresent()){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            Mission mission = trymission.get();
            Utilisateur utilisateur = tryUtilisateur.get();

            InscriptionService inscriptionService = new InscriptionService(inscriptionRepository);
            Inscription newInscription = new Inscription(utilisateur, mission);
            newInscription.setDate(Date.valueOf(date));
            Inscription inscription = inscriptionService.createInscription(newInscription);
            Collection<Action> actions = mission.getActionsById();
            InscriptionActionService inscriptionActionService = new InscriptionActionService(inscriptionActionRepository);
            for(Action action: actions){
                InscriptionAction inscriptionAction = new InscriptionAction(inscription, action);
                inscriptionActionService.createInscription(inscriptionAction);
            }

            Optional<Inscription> finalInscription = inscriptionService.getInscriptionById(inscription.getId());
            if(finalInscription.isPresent()){
                return ResponseEntity.ok(finalInscription.get());
            }

        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("api/inscription/remove/{id}")
    ResponseEntity removeInscription(@PathVariable int id, HttpServletRequest request){
        UtilisateurService utilisateurService = new UtilisateurService(utilisateurRepository);
        InscriptionService inscriptionService = new InscriptionService(inscriptionRepository);
        Optional<Inscription> tryinscription = inscriptionService.getInscriptionById(id);
        Utilisateur u = utilisateurService.getAutenticatedUtilisateur(request);
        if(u!=null && (u.isAdmin()
                || (tryinscription.isPresent()
                        && tryinscription.get().getUtilisateurByFkUser().getNumUtil()==u.getNumUtil()))) {
            InscriptionActionService inscriptionActionService = new InscriptionActionService(inscriptionActionRepository);

            Collection<InscriptionAction> inscriptionActions = tryinscription.get().getInscriptionActionsById();
            boolean missionCommencee = false;
            for(InscriptionAction inscriptionAction: inscriptionActions){
                if(inscriptionAction.getScore()!=null){
                    missionCommencee = true;
                }
            }
            if(missionCommencee){
                return new ResponseEntity("Mission commencée", HttpStatus.BAD_REQUEST);
            }
            for(InscriptionAction inscriptionAction: inscriptionActions){
                inscriptionActionService.deleteInscription(inscriptionAction);
            }
            inscriptionService.deleteInscription(tryinscription.get());
            return ResponseEntity.ok(inscriptionService.getInscriptionById(tryinscription.get().getId()));
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


}
