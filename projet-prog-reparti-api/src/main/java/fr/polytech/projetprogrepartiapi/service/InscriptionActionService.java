package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.InscriptionAction;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    public InscriptionAction createInscription(InscriptionAction inscriptionAction){
        return inscriptionActionRepository.save(inscriptionAction);
    }

    public long getNumberInscriptions(){
        return inscriptionActionRepository.count();
    }

    public void deleteInscription(InscriptionAction inscriptionAction){
        inscriptionActionRepository.delete(inscriptionAction);
    }

    public Collection<InscriptionAction> getInscriptionsActionsFromInscription(Inscription inscription){
        return inscription.getInscriptionActionsById();
    }
}
