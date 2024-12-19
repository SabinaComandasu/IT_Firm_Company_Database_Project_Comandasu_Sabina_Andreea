package Entitati;

public class ProjectUsesSoftware {
    private String projectID;
    private String softwareID;

    public ProjectUsesSoftware(String projectID, String softwareID) {
        this.projectID = projectID;
        this.softwareID = softwareID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getSoftwareID() {
        return softwareID;
    }

    public void setSoftwareID(String softwareID) {
        this.softwareID = softwareID;
    }
}
