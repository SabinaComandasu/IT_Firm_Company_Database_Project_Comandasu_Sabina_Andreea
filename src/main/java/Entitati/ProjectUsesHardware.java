package Entitati;

public class ProjectUsesHardware {
    private String projectID;
    private String hardwareID;

    public ProjectUsesHardware(String projectID, String hardwareID) {
        this.projectID = projectID;
        this.hardwareID = hardwareID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getHardwareID() {
        return hardwareID;
    }

    public void setHardwareID(String hardwareID) {
        this.hardwareID = hardwareID;
    }
}
