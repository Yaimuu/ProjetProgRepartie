package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.controller.UtilisateurController;
import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> getUtilisateurById(int id){
        return utilisateurRepository.findById(id);
    }

    public boolean utilisateurExists(int id){
        return utilisateurRepository.existsById(id);
    }

    public List<Utilisateur> getAllUtilisateurs(){
        return utilisateurRepository.findAll();
    }

    public List<Utilisateur> getAllUtilisateursByRole(String role){
        return utilisateurRepository.findAllByRole(role);
    }
    public boolean isAdmin(int id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.isPresent() && utilisateur.get().isAdmin();
    }

    public void createUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.save(utilisateur);
    }

    public long getNumberUtilisateurs(){
        return utilisateurRepository.count();
    }

    public void deleteUtilisateur(Utilisateur utilisateur){
        utilisateurRepository.delete(utilisateur);
    }

    public List<Inscription> getAllInscriptionsFromUser(Utilisateur utilisateur){
        return utilisateur.getInscriptionsById();
    }
    public Utilisateur getAutenticatedUtilisateur(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if(session.getAttribute("id") != null)
        {
            return this.getUtilisateurById((int) session.getAttribute("id")).get();
        }

        return null;
    }


}
