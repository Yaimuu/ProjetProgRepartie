package fr.polytech.projetprogrepartiapi.service;

import fr.polytech.projetprogrepartiapi.entities.LoginRequest;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface IAuthentificationService {

    public Utilisateur authentification(@RequestBody LoginRequest unUti) throws Exception;

}
