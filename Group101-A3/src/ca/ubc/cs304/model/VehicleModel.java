package ca.ubc.cs304.model;

public class VehicleModel implements Model{
    Integer vid;
    String vlicense;
    String make;
    String model;
    String year;
    String color;
    Integer odometer;
    String status;
    String vtname;
    String location;
    String city;

    public VehicleModel(String vlicense,Integer vid,  String make, String model, String year, String color, Integer odometer, String vtname, String location, String city) {
        this.vid = vid;
        this.vlicense = vlicense;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.odometer = odometer;
        this.vtname = vtname;
        this.location = location;
        this.city = city;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVtname(String vtname) {
        this.vtname = vtname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getVid() {
        return vid;
    }

    public String getVlicense() {
        return vlicense;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public String getStatus() {
        return status;
    }

    public String getVtname() {
        return vtname;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }
}
