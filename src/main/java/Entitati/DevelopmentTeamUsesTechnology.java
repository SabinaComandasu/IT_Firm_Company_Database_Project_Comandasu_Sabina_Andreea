package Entitati;

public class DevelopmentTeamUsesTechnology {
    private String teamID;
    private String technologyID;

    public DevelopmentTeamUsesTechnology(String teamID, String technologyID) {
        this.teamID = teamID;
        this.technologyID = technologyID;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getTechnologyID() {
        return technologyID;
    }

    public void setTechnologyID(String technologyID) {
        this.technologyID = technologyID;
    }

}

