package fr.polytech.projetprogrepartiapi.entities;

import javax.persistence.*;

@Entity
public class Indicator {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "fk_action", nullable = false)
    private int fkAction;
    @Basic
    @Column(name = "wording", nullable = true, length = 50)
    private String wording;
    @Basic
    @Column(name = "valueIfCheck", nullable = true)
    private Integer valueIfCheck;
    @Basic
    @Column(name = "valueIfUnCheck", nullable = true)
    private Integer valueIfUnCheck;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false)
    private Action actionByFkAction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkAction() {
        return fkAction;
    }

    public void setFkAction(int fkAction) {
        this.fkAction = fkAction;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public Integer getValueIfCheck() {
        return valueIfCheck;
    }

    public void setValueIfCheck(Integer valueIfCheck) {
        this.valueIfCheck = valueIfCheck;
    }

    public Integer getValueIfUnCheck() {
        return valueIfUnCheck;
    }

    public void setValueIfUnCheck(Integer valueIfUnCheck) {
        this.valueIfUnCheck = valueIfUnCheck;
    }

    public Action getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(Action actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }
}
