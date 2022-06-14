package fr.polytech.projetprogrepartiapi.repositories;

import fr.polytech.projetprogrepartiapi.entities.Indicator;
import fr.polytech.projetprogrepartiapi.entities.InscriptionAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionActionRepository extends JpaRepository<InscriptionAction, Integer> {
}
