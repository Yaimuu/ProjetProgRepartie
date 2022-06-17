package fr.polytech.projetprogrepartiapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.helpers.PasswordHelper;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import fr.polytech.projetprogrepartiapi.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UtilisateurController {

    private final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurController(UtilisateurRepository utilisateurRepository) {

        this.utilisateurRepository = utilisateurRepository;
    }


    @GetMapping(value={"/api/user/all", "/api/users"})
    public ResponseEntity<Object> getAllUsersWithRole(HttpServletRequest request) {
        String rolename = "learner";
        logger.info("GET users/role/"+rolename);

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            UtilisateurService uService = new UtilisateurService(utilisateurRepository);
            return ResponseEntity.ok(uService.getAllUtilisateursByRole(rolename));
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
    @GetMapping("/api/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable int id, HttpServletRequest request) {
        logger.info("GET user/" + id);

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            UtilisateurService uService = new UtilisateurService(utilisateurRepository);
            return ResponseEntity.ok(uService.getUtilisateurById(id));
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/api/user/remove/{id}")
    public ResponseEntity<Object> removeUser(@PathVariable int id, HttpServletRequest request) {
        logger.info("POST user/remove/" + id);

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            UtilisateurService uService = new UtilisateurService(utilisateurRepository);
            if(uService.isAdmin((int) session.getAttribute("id")))
            {
                Optional<Utilisateur> utilisateurToRemove = uService.getUtilisateurById(id);

                if(!utilisateurToRemove.isPresent())
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);

                uService.deleteUtilisateur(utilisateurToRemove.get());
                return ResponseEntity.ok(uService.getUtilisateurById(id));
            }
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/api/user/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, HttpServletRequest request) {
        logger.info("POST user/update/" + id);

        HttpSession session = request.getSession();
        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        String requestString = "";
        Map<String, Object> mappedRequest = new HashMap<>();

        try {
            requestString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            mappedRequest = mapper.readValue(requestString, Map.class);
        } catch (Exception e) { e.printStackTrace(); }

        Utilisateur u = uService.getAutenticatedUtilisateur(request);
        if(u != null)
        {
            if(uService.isAdmin(u.getNumUtil()) || u.getNumUtil() == id)
            {
                Optional<Utilisateur> orginalUser = uService.getUtilisateurById(id);

                if(!orginalUser.isPresent())
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);



                Utilisateur utilisateurOriginal = orginalUser.get();
                if(mappedRequest.get("nomUtil")!=null){
                    utilisateurOriginal.setNomUtil(mappedRequest.get("nomUtil").toString());
                }
                if(mappedRequest.get("forename")!=null){
                    utilisateurOriginal.setForename(mappedRequest.get("forename").toString());
                }
                if(mappedRequest.get("surname")!=null) {
                    utilisateurOriginal.setSurname(mappedRequest.get("surname").toString());
                }
                if(mappedRequest.get("email")!=null){
                    utilisateurOriginal.setEmail(mappedRequest.get("email").toString());
                }
                if(mappedRequest.get("motPasse")!=null){
                    byte[] salt = PasswordHelper.GenerateSalt();
                    byte[] password = PasswordHelper.generatePasswordHash(mappedRequest.get("motPasse").toString().toCharArray(), salt);
                    utilisateurOriginal.setMotPasse(PasswordHelper.bytesToString(password));
                    utilisateurOriginal.setSalt(PasswordHelper.bytesToString(salt));
                }
                uService.createUtilisateur(utilisateurOriginal);

                return ResponseEntity.ok(uService.getUtilisateurById(id));
            }
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/api/user/{id}/inscriptions")
    public ResponseEntity<Object> getInscriptionsForUser(@PathVariable int id, HttpServletRequest request) {
        logger.info("GET user/" +id+"/inscriptions" );

        HttpSession session = request.getSession();
        UtilisateurService uService = new UtilisateurService(utilisateurRepository);
        Utilisateur u = uService.getAutenticatedUtilisateur(request);
        if(u != null)
        {
            if(uService.isAdmin(u.getNumUtil()) || u.getNumUtil() == id){
                Optional<Utilisateur> uconcerne = uService.getUtilisateurById(id);
                if(uconcerne.isPresent()){
                    List<Inscription> inscriptionsUser = uService.getAllInscriptionsFromUser(uconcerne.get());
                    return ResponseEntity.ok(inscriptionsUser);
                }
            }
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
