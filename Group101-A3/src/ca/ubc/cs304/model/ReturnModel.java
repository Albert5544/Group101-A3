package ca.ubc.cs304.model;

public class ReturnModel {
    Integer rid;
    String datetime;
    Integer odometer;
    Integer fullTank;
    Integer value;

    public ReturnModel(Integer rid, String datetime, Integer odometer, Integer fullTank, Integer value) {
        this.rid = rid;
        this.datetime = datetime;
        this.odometer = odometer;
        this.fullTank = fullTank;
        this.value = value;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public Integer getFullTank() {
        return fullTank;
    }

    public void setFullTank(Integer fullTank) {
        this.fullTank = fullTank;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
