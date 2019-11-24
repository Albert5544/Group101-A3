package ca.ubc.cs304.model;

public class ReservationModel {
    private final int confno;
    private final String vtname;
    private final int cellphone;
    private final int fromdatetime;
    private final int todatetime;

    public ReservationModel(int confno, String vtname, int dlicense, String fromDateTime, String toDateTime) {
        this.confno = confno;
        this.vtname = vtname;
        this.dlicense = dlicense;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public int getConfno() {
        return confno;
    }

    public String getVtname() {
        return vtname;
    }

    public int getDlicense() {
        return dlicense;
    }

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getToDateTime() {
        return toDateTime;
    }
}
