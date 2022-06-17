package fr.polytech.projetprogrepartiapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Inscription {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "date", nullable = true)
    private Date date;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "fk_user", referencedColumnName = "NumUtil", nullable = false)
    private Utilisateur utilisateurByFkUser;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "fk_mission", referencedColumnName = "id", nullable = false)
    private Mission missionByFkMission;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscriptionByFkInscription")
    private Collection<InscriptionAction> inscriptionActionsById;

    protected Inscription(){}
    public Inscription(Utilisateur user, Mission mission){
        utilisateurByFkUser = user;
        missionByFkMission = mission;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getUtilisateurByFkUser() {
        return utilisateurByFkUser;
    }

    public void setUtilisateurByFkUser(Utilisateur utilisateurByFkUser) {
        this.utilisateurByFkUser = utilisateurByFkUser;
    }

    public Mission getMissionByFkMission() {
        return missionByFkMission;
    }

    public void setMissionByFkMission(Mission missionByFkMission) {
        this.missionByFkMission = missionByFkMission;
    }

    public Collection<InscriptionAction> getInscriptionActionsById() {
        return inscriptionActionsById;
    }

    public void setInscriptionActionsById(Collection<InscriptionAction> inscriptionActionsById) {
        this.inscriptionActionsById = inscriptionActionsById;
    }
}