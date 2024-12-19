package Entitati;

public class DevelopmentTeam {
    private String teamID;
    private String name;
    private String teamLeaderID;

    public DevelopmentTeam(String teamID, String name, String teamLeaderID) {
        this.teamID = teamID;
        this.name = name;
        this.teamLeaderID = teamLeaderID;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamLeaderID() {
        return teamLeaderID;
    }

    public void setTeamLeaderID(String teamLeaderID) {
        this.teamLeaderID = teamLeaderID;
    }


}
