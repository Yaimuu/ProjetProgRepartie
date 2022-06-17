package fr.polytech.projetprogrepartiapi.repositories;

import fr.polytech.projetprogrepartiapi.entities.Action;
import fr.polytech.projetprogrepartiapi.entities.Indicator;
import fr.polytech.projetprogrepartiapi.entities.Inscription;
import fr.polytech.projetprogrepartiapi.entities.InscriptionAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InscriptionActionRepository extends JpaRepository<InscriptionAction, Integer> {

    @Query("SELECT ia FROM InscriptionAction ia  WHERE ia.inscriptionByFkInscription=?1 and ia.actionByFkAction=?2")
    public Optional<InscriptionAction> findByValues(Inscription inscription, Action action);
}
