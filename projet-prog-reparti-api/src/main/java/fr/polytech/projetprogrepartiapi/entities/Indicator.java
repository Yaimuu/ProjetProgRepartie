package fr.polytech.projetprogrepartiapi.entities;

import javax.persistence.*;

@Entity
public class Indicator {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "wording", nullable = true, length = 50)
    private String wording;
    @Basic
    @Column(name = "valueifcheck", nullable = true)
    private Integer valueIfCheck;
    @Basic
    @Column(name = "valueifuncheck", nullable = true)
    private Integer valueIfUnCheck;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false)
    private Action actionByFkAction;

    protected Indicator(){}
    public Indicator(Action action){
        actionByFkAction = action;
    }
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
