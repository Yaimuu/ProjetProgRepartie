package fr.polytech.projetprogrepartiapi.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ActionMissionPK implements Serializable {
    @Column(name = "fk_action", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fkAction;
    @Column(name = "fk_mission", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fkMission;

    public int getFkAction() {
        return fkAction;
    }

    public void setFkAction(int fkAction) {
        this.fkAction = fkAction;
    }

    public int getFkMission() {
        return fkMission;
    }

    public void setFkMission(int fkMission) {
        this.fkMission = fkMission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionMissionPK that = (ActionMissionPK) o;
        return fkAction == that.fkAction && fkMission == that.fkMission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fkAction, fkMission);
    }
}
