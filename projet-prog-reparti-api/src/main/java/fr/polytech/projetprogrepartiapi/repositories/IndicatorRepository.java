package fr.polytech.projetprogrepartiapi.repositories;

import fr.polytech.projetprogrepartiapi.entities.Indicator;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndicatorRepository extends JpaRepository<Indicator, Integer> {

    @Query( "SELECT i FROM Indicator i WHERE i.actionByFkAction.id = :actionId")
    List<Indicator> findAllByActionId(@Param("actionId") int actionId);
}
