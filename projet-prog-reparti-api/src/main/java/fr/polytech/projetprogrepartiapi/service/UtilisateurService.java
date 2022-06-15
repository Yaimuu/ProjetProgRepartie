package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
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

    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurRepository.findAll();
    }

    public boolean isAdmin(int id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.isPresent() ? utilisateur.get().isAdmin() : false;
    }
}
