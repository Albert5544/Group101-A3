package ca.ubc.cs304.model;

public class BranchModel {
    String city;
    String location;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BranchModel(String city, String location) {
        this.city = city;
        this.location = location;
    }


}
