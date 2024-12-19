package Entitati;

public class Client {
    private String clientID;
    private String name;
    private String industry;
    private String email;
    private String phoneNumber;

    public Client(String clientID, String name, String industry, String email, String phoneNumber) {
        this.clientID = clientID;
        this.name = name;
        this.industry = industry;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
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
}
