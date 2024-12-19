package Entitati;

public class Position {
    private String positionID;
    private String name;
    private String description;

    public Position(String positionID, String name, String description) {
        this.positionID = positionID;
        this.name = name;
        this.description = description;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
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


}
