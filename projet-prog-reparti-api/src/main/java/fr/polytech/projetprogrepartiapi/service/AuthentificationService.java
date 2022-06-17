package fr.polytech.projetprogrepartiapi.service;


import fr.polytech.projetprogrepartiapi.controller.UtilisateurController;
import fr.polytech.projetprogrepartiapi.entities.LoginRequest;
import fr.polytech.projetprogrepartiapi.entities.Utilisateur;
import fr.polytech.projetprogrepartiapi.helpers.PasswordHelper;
import fr.polytech.projetprogrepartiapi.repositories.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService implements IAuthentificationService {
    private final Logger logger = LoggerFactory.getLogger(AuthentificationService.class);

    private UtilisateurRepository unUtilisateurRepostory;

    @Autowired
    public AuthentificationService(UtilisateurRepository UtilisateurRepostory) {
        this.unUtilisateurRepostory = UtilisateurRepostory;
    }

    @Override
    public Utilisateur authentification(LoginRequest unUti) throws Exception {
        Utilisateur unUtilisateur = null;
        String message = "";
        String login = unUti.getNomUtil();
        String pwd = unUti.getMotPasse();
        unUtilisateur = unUtilisateurRepostory.rechercheNom(unUti.getNomUtil());
        if (unUtilisateur != null) {
            try {
                // on récupère le sel
                String sel = unUtilisateur.getSalt();
                // on récupère le mot de passe
                String mdp = unUtilisateur.getMotPasse();
                // on génère le mot de passe avec les données de connexion
                byte[] salt = PasswordHelper.transformeEnBytes(unUtilisateur.getSalt());
                char[] pwd_char = PasswordHelper.converttoCharArray(pwd);
                byte[] monpwdCo = PasswordHelper.generatePasswordHash(pwd_char, salt);
                // on récupère le mot de passe enregistré
                byte[] mdp_byte = PasswordHelper.transformeEnBytes(mdp);
                if (!PasswordHelper.verifyPassword(monpwdCo, mdp_byte)) {
                    message = "mot de passe erroné";
                    logger.info(message);
                    return null;
                }
            } catch (Exception e) {
                throw e;
            }
        }

        logger.info(message);
        return unUtilisateur;
    }


}
