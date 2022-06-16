package fr.polytech.projetprogrepartiapi.repositories;

import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    public Utilisateur rechercheNom(String login);

    @Query("SELECT u FROM Utilisateur u  WHERE u.role LIKE ?1")
    public List<Utilisateur> findAllByRole(String role);
}
