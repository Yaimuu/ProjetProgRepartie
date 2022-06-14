package fr.polytech.projetprogrepartiapi.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Mission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "wording", nullable = true, length = 25)
    private String wording;
    @OneToMany(mappedBy = "missionByFkMission")
    private Collection<ActionMission> actionMissionsById;
    @OneToMany(mappedBy = "missionByFkMission")
    private Collection<Inscription> inscriptionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public Collection<ActionMission> getActionMissionsById() {
        return actionMissionsById;
    }

    public void setActionMissionsById(Collection<ActionMission> actionMissionsById) {
        this.actionMissionsById = actionMissionsById;
    }

    public Collection<Inscription> getInscriptionsById() {
        return inscriptionsById;
    }

    public void setInscriptionsById(Collection<Inscription> inscriptionsById) {
        this.inscriptionsById = inscriptionsById;
    }
}
