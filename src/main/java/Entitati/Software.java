package Entitati;

public class Software {
    private String softwareID;
    private String name;
    private String licenseKey;    // Added licenseKey as requested
    private String supplierID;    // Added supplierID as requested
    private String version;       // Added version of type VARCHAR(50)

    public Software(String softwareID, String name, String licenseKey, String supplierID, String version) {
        this.softwareID = softwareID;
        this.name = name;
        this.licenseKey = licenseKey;
        this.supplierID = supplierID;
        this.version = version;    // Set version value
    }

    public String getSoftwareID() {
        return softwareID;
    }

    public void setSoftwareID(String softwareID) {
        this.softwareID = softwareID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

