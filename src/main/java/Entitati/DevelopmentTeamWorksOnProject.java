package Entitati;

public class DevelopmentTeamWorksOnProject {
    private String teamID;
    private String projectID;

    public DevelopmentTeamWorksOnProject(String teamID, String projectID) {
        this.teamID = teamID;
        this.projectID = projectID;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

}

