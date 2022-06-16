package fr.polytech.projetprogrepartiapi.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
public class Action {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "wording", nullable = true, length = 25)
    private String wording;
    @Basic
    @Column(name = "scoreminimum", nullable = true)
    private Integer scoreMinimum;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "fk_action", referencedColumnName = "id")
    private Action actionByFkAction;
    @JsonBackReference
    @OneToMany(mappedBy = "actionByFkAction")
    private Collection<Action> actionsById;




    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "action__mission", joinColumns = {
            @JoinColumn(name = "fk_action", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "fk_mission",
                    nullable = false, updatable = false) })
    private List<Mission> MissionsById;
    @JsonManagedReference
    @OneToMany(mappedBy = "actionByFkAction")
    private Collection<Indicator> indicatorsById;
    @JsonBackReference
    @OneToMany(mappedBy = "actionByFkAction")
    private Collection<InscriptionAction> inscriptionActionsById;

    public Action(){}
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
    @JsonIgnore
    public List<Mission> getMissionsById() {
        return MissionsById;
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
