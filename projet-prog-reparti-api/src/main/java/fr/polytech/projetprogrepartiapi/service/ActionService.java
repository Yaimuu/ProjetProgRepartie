package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.Action;
import fr.polytech.projetprogrepartiapi.entities.Mission;
import fr.polytech.projetprogrepartiapi.repositories.ActionRepository;
import fr.polytech.projetprogrepartiapi.repositories.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionService {
    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository){
        this.actionRepository = actionRepository;
    }

    public Optional<Action> getActionById(int id){
        return actionRepository.findById(id);
    }

    public List<Action> getAllActions(){
        return actionRepository.findAll();
    }

    public void createAction(Action action){
        actionRepository.save(action);
    }

    public long getNumberActions(){
        return actionRepository.count();
    }

    public void deleteAction(Action action){
        actionRepository.delete(action);
    }
}
