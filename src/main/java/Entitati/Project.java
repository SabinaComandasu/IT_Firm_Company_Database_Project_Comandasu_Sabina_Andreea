package Entitati;

import java.sql.Date;

public class Project {
    private String projectID;
    private String name;
    private Date startLine;
    private Date deadline;
    private int budget;
    private String departmentID;
    private String clientID;

    public Project(String projectID, String name, Date startLine, Date deadline, int budget, String departmentID, String clientID) {
        this.projectID = projectID;
        this.name = name;
        this.startLine = startLine;
        this.deadline = deadline;
        this.budget = budget;
        this.departmentID = departmentID;
        this.clientID = clientID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartLine() {
        return startLine;
    }

    public void setStartLine(Date startLine) {
        this.startLine = startLine;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
