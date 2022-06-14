package fr.polytech.projetprogrepartiapi.entities;

import javax.persistence.*;

@Entity
@Table(name = "inscription__action", schema = "projetpermis1", catalog = "")
public class InscriptionAction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "sort", nullable = true)
    private Integer sort;
    @Basic
    @Column(name = "score", nullable = true)
    private Integer score;
    @ManyToOne
    @JoinColumn(name = "fk_inscription", referencedColumnName = "id", nullable = false)
    private Inscription inscriptionByFkInscription;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false)
    private Action actionByFkAction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Inscription getInscriptionByFkInscription() {
        return inscriptionByFkInscription;
    }

    public void setInscriptionByFkInscription(Inscription inscriptionByFkInscription) {
        this.inscriptionByFkInscription = inscriptionByFkInscription;
    }

    public Action getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(Action actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }
}
