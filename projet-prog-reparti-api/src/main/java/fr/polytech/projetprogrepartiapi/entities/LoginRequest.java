package fr.polytech.projetprogrepartiapi.entities;

public class LoginRequest {
    private String nomUtil;
    private String motPasse;

    public String getMotPasse() {
        return motPasse;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }


    public String getNomUtil() {
        return nomUtil;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "nomUtil='" + nomUtil + '\'' +
                ", motPasse='" + motPasse + '\'' +
                '}';
    }
}
