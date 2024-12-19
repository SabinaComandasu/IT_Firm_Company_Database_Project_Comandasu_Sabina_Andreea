package Entitati;

public class Manufacturer {
    private String manufacturerID;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;   // Added address of type VARCHAR(50)

    public Manufacturer(String manufacturerID, String name, String email, String phoneNumber, String address) {
        this.manufacturerID = manufacturerID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;  // Set the address value
    }

    public String getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(String manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
