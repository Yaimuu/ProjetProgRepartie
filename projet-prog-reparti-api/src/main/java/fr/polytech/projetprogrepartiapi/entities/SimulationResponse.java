package fr.polytech.projetprogrepartiapi.entities;

import java.util.List;

public class SimulationResponse {

    private Action action;
    private boolean actionChecked;
    private int score;
    private List<Indicator> indicators;

    public SimulationResponse(Action action, boolean actionChecked, int score, List<Indicator> indicators) {
        this.action = action;
        this.actionChecked = actionChecked;
        this.score = score;
        this.indicators = indicators;
    }

    public boolean isActionChecked() {
        return actionChecked;
    }

    public void setActionChecked(boolean actionChecked) {
        this.actionChecked = actionChecked;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<Indicator> indicators) {
        this.indicators = indicators;
    }
}
