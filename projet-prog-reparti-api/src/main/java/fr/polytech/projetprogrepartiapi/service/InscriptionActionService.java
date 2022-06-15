package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.InscriptionAction;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionActionService {
    private final InscriptionActionRepository inscriptionActionRepository;

    public InscriptionActionService(InscriptionActionRepository inscriptionActionRepository){
        this.inscriptionActionRepository = inscriptionActionRepository;
    }

    public Optional<InscriptionAction> getInscriptionById(int id){
        return inscriptionActionRepository.findById(id);
    }

    public List<InscriptionAction> getAllInscriptions(){
        return inscriptionActionRepository.findAll();
    }

    public void createInscription(InscriptionAction inscriptionAction){
        inscriptionActionRepository.save(inscriptionAction);
    }

    public long getNumberInscriptions(){
        return inscriptionActionRepository.count();
    }

    public void deleteInscription(InscriptionAction inscriptionAction){
        inscriptionActionRepository.delete(inscriptionAction);
    }
}
