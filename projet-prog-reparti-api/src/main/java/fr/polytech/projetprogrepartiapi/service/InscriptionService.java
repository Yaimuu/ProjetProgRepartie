package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.InscriptionAction;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository){
        this.inscriptionRepository = inscriptionRepository;
    }

    public Optional<Inscription> getInscriptionById(int id){
        return inscriptionRepository.findById(id);
    }

    public List<Inscription> getAllInscriptions(){
        return inscriptionRepository.findAll();
    }

    public Inscription createInscription(Inscription inscription){
        return inscriptionRepository.save(inscription);
    }

    public long getNumberInscriptions(){
        return inscriptionRepository.count();
    }

    public void deleteInscription(Inscription inscription){
        inscriptionRepository.delete(inscription);
    }

}
