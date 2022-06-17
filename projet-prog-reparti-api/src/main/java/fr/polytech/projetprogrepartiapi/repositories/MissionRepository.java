package fr.polytech.projetprogrepartiapi.repositories;

import fr.polytech.projetprogrepartiapi.entities.Mission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MissionRepository extends JpaRepository<Mission, Integer> {
}
