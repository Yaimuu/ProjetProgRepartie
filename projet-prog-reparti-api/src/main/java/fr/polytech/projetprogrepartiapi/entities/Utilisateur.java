package fr.polytech.projetprogrepartiapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur", schema = "projetpermis1", catalog = "")
@NamedQuery(name = "Utilisateur.rechercheNom", query = "select ut from Utilisateur ut where ut.nomUtil = ?1")
public class Utilisateur {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "numutil", nullable = false)
    private int numUtil;
    @Basic
    @Column(name = "nomutil", nullable = false, length = 100)
    private String nomUtil;
    @Basic
    @Column(name = "motpasse", nullable = false, length = 100)
    private String motPasse;
    @Basic
    @Column(name = "salt", nullable = false, length = 100)
    private String salt;
    @Basic
    @Column(name = "role", nullable = false, length = 100)
    private String role;


    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Basic
    @Column(name = "surname", nullable = true, length = 50)
    private String surname;
    @Basic
    @Column(name = "forename", nullable = true, length = 50)
    private String forename;
    @OneToMany(mappedBy = "utilisateurByFkUser")
    @JsonBackReference
    private Collection<Inscription> inscriptionsById;

    protected Utilisateur(){}
    /**
     * Seuls les paramètres obligatoires sont dans le constructeur. Il faudra passer les autres avec les setters
     * @param numUtil
     * @param nomUtil
     * @param motPasse /!\ il faut qu'il soit hash avant d'être passé en paramètre
     * @param salt
     * @param role
     */
    public Utilisateur(int numUtil, String nomUtil, String motPasse, String salt, String role){
        this.numUtil = numUtil;
        this.nomUtil = nomUtil;
        this.motPasse = motPasse;
        this.salt = salt;
        this.role = role;
    }
    public Collection<Inscription> getInscriptionsById() {
        return inscriptionsById;
    }

    public void setInscriptionsById(Collection<Inscription> inscriptionsById) {
        this.inscriptionsById = inscriptionsById;
    }


    public int getNumUtil() {
        return numUtil;
    }

    public void setNumUtil(int numUtil) {
        this.numUtil = numUtil;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public boolean isAdmin() {return this.getRole().equals("admin");}
}
