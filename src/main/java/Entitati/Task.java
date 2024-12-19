package Entitati;

import java.util.Date;

public class Task {
    private String taskID;
    private String name;
    private String description;
    private String projectID;
    private String assignedTeamID;
    private String status;
    private Date startline;
    private Date deadline;

    public Task(String taskID, String name, String description, String projectID, String assignedTeamID,
                String status, Date startline, Date deadline) {
        this.taskID = taskID;
        this.name = name;
        this.description = description;
        this.projectID = projectID;
        this.assignedTeamID = assignedTeamID;
        this.status = status;
        this.startline = startline;
        this.deadline = deadline;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getAssignedTeamID() {
        return assignedTeamID;
    }

    public void setAssignedTeamID(String assignedTeamID) {
        this.assignedTeamID = assignedTeamID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartline() {
        return startline;
    }

    public void setStartline(Date startline) {
        this.startline = startline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

}

