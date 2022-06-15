package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.Mission;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import fr.polytech.projetprogrepartiapi.repositories.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository){
        this.missionRepository = missionRepository;
    }

    public Optional<Mission> getMissionById(int id){
        return missionRepository.findById(id);
    }

    public List<Mission> getAllMissions(){
        return missionRepository.findAll();
    }

    public void createMission(Mission mission){
        missionRepository.save(mission);
    }

    public long getNumberMissions(){
        return missionRepository.count();
    }

    public void deleteMission(Mission mission){
        missionRepository.delete(mission);
    }
}
