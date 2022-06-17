package fr.polytech.projetprogrepartiapi.repositories;

import fr.polytech.projetprogrepartiapi.entities.Indicator;
import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
}
