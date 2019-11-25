package ca.ubc.cs304.model;

public class ReservationModel {
    private   int confno;
    private   String vtname;
    private   String dlicense;
    private   String fromDateTime;
    private   String toDateTime;

    public void setConfno(int confno) {
        this.confno = confno;
    }

    public void setVtname(String vtname) {
        this.vtname = vtname;
    }

    public void setDlicense(String dlicense) {
        this.dlicense = dlicense;
    }

    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public void setToDateTime(String toDateTime) {
        this.toDateTime = toDateTime;
    }

    public ReservationModel(int confno, String vtname, String dlicense, String fromDateTime, String toDateTime) {
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

    public String getDlicense() {
        return dlicense;
    }

    public String getFromDateTime() {
        return fromDateTime;
    }

    public String getToDateTime() {
        return toDateTime;
    }


}
