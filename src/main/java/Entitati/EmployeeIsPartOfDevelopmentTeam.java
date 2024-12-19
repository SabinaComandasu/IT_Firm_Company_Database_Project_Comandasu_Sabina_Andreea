package Entitati;

public class EmployeeIsPartOfDevelopmentTeam {
    private String employeeID;
    private String teamID;

    public EmployeeIsPartOfDevelopmentTeam(String employeeID, String teamID) {
        this.employeeID = employeeID;
        this.teamID = teamID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }
}
