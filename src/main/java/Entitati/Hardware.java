package Entitati;

public class Hardware {
    private String hardwareID;
    private String hardwareType;
    private String model;
    private String manufacturerID;  // Added manufacturerID

    public Hardware(String hardwareID, String hardwareType, String model, String manufacturerID) {
        this.hardwareID = hardwareID;
        this.hardwareType = hardwareType;
        this.model = model;
        this.manufacturerID = manufacturerID;
    }

    public String getHardwareID() {
        return hardwareID;
    }

    public void setHardwareID(String hardwareID) {
        this.hardwareID = hardwareID;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(String manufacturerID) {
        this.manufacturerID = manufacturerID;
    }
}
