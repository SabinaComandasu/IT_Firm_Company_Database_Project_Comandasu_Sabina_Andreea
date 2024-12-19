package Entitati;

public class Department {
    private String name;
    private String departmentID;

    public Department(String name, String departmentID) {
        this.name = name;
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }



}
