package Entitati;

public class ProjectUsesTechnology {
    private String projectID;
    private String technologyID;

    public ProjectUsesTechnology(String projectID, String technologyID) {
        this.projectID = projectID;
        this.technologyID = technologyID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getTechnologyID() {
        return technologyID;
    }

    public void setTechnologyID(String technologyID) {
        this.technologyID = technologyID;
    }
}

