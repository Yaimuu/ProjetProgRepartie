package fr.polytech.projetprogrepartiapi.entities;

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
    @JoinColumn(name = "fk_user", referencedColumnName = "NumUtil", nullable = false)
    private Utilisateur utilisateurByFkUser;
    @ManyToOne
    @JoinColumn(name = "fk_mission", referencedColumnName = "id", nullable = false)
    private Mission missionByFkMission;
    @OneToMany(mappedBy = "inscriptionByFkInscription")
    private Collection<InscriptionAction> inscriptionActionsById;

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
