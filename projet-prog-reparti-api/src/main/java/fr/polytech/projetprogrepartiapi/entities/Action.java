package fr.polytech.projetprogrepartiapi.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Action {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "fk_action", nullable = true)
    private Integer fkAction;
    @Basic
    @Column(name = "wording", nullable = true, length = 25)
    private String wording;
    @Basic
    @Column(name = "scoreMinimum", nullable = true)
    private Integer scoreMinimum;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id")
    private Action actionByFkAction;
    @OneToMany(mappedBy = "actionByFkAction")
    private Collection<Action> actionsById;
    @OneToMany(mappedBy = "actionByFkAction")
    private Collection<ActionMission> actionMissionsById;
    @OneToMany(mappedBy = "actionByFkAction")
    private Collection<Indicator> indicatorsById;
    @OneToMany(mappedBy = "actionByFkAction")
    private Collection<InscriptionAction> inscriptionActionsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFkAction() {
        return fkAction;
    }

    public void setFkAction(Integer fkAction) {
        this.fkAction = fkAction;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public Integer getScoreMinimum() {
        return scoreMinimum;
    }

    public void setScoreMinimum(Integer scoreMinimum) {
        this.scoreMinimum = scoreMinimum;
    }

    public Action getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(Action actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }

    public Collection<Action> getActionsById() {
        return actionsById;
    }

    public void setActionsById(Collection<Action> actionsById) {
        this.actionsById = actionsById;
    }

    public Collection<ActionMission> getActionMissionsById() {
        return actionMissionsById;
    }

    public void setActionMissionsById(Collection<ActionMission> actionMissionsById) {
        this.actionMissionsById = actionMissionsById;
    }

    public Collection<Indicator> getIndicatorsById() {
        return indicatorsById;
    }

    public void setIndicatorsById(Collection<Indicator> indicatorsById) {
        this.indicatorsById = indicatorsById;
    }

    public Collection<InscriptionAction> getInscriptionActionsById() {
        return inscriptionActionsById;
    }

    public void setInscriptionActionsById(Collection<InscriptionAction> inscriptionActionsById) {
        this.inscriptionActionsById = inscriptionActionsById;
    }
}
