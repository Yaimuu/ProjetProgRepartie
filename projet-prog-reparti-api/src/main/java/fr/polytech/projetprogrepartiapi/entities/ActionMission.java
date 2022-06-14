package fr.polytech.projetprogrepartiapi.entities;

import javax.persistence.*;

@Entity
@Table(name = "action__mission", schema = "projetpermis1", catalog = "")
@IdClass(ActionMissionPK.class)
public class ActionMission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fk_action", nullable = false)
    private int fkAction;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fk_mission", nullable = false)
    private int fkMission;
    @ManyToOne
    @JoinColumn(name = "fk_action", referencedColumnName = "id", nullable = false)
    private Action actionByFkAction;
    @ManyToOne
    @JoinColumn(name = "fk_mission", referencedColumnName = "id", nullable = false)
    private Mission missionByFkMission;

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

    public Action getActionByFkAction() {
        return actionByFkAction;
    }

    public void setActionByFkAction(Action actionByFkAction) {
        this.actionByFkAction = actionByFkAction;
    }

    public Mission getMissionByFkMission() {
        return missionByFkMission;
    }

    public void setMissionByFkMission(Mission missionByFkMission) {
        this.missionByFkMission = missionByFkMission;
    }
}
