package Entitati;

public class Technology {
    private String technologyID;
    private String name;
    private String version;
    private String licenseType;

    public Technology(String technologyID, String name, String version, String licenseType) {
        this.technologyID = technologyID;
        this.name = name;
        this.version = version;
        this.licenseType = licenseType;
    }

    public String getTechnologyID() {
        return technologyID;
    }

    public void setTechnologyID(String technologyID) {
        this.technologyID = technologyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }


}
