package fr.polytech.projetprogrepartiapi.repositories;

import fr.polytech.projetprogrepartiapi.entities.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mission extends JpaRepository<Mission, Integer> {
}
