package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.Indicator;
import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.repositories.IndicatorRepository;
import fr.polytech.projetprogrepartiapi.repositories.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndicatorService {
    private final IndicatorRepository indicatorRepository;

    public IndicatorService(IndicatorRepository indicatorRepository){
        this.indicatorRepository = indicatorRepository;
    }

    public Optional<Indicator> getIndicatorById(int id){
        return indicatorRepository.findById(id);
    }

    public List<Indicator> getAllIndicators(){
        return indicatorRepository.findAll();
    }

    public void createIndicator(Indicator inscription){
        indicatorRepository.save(inscription);
    }

    public long getNumberIndicators(){
        return indicatorRepository.count();
    }

    public void deleteIndicator(Indicator inscription){
        indicatorRepository.delete(inscription);
    }
}
