package ca.ubc.cs304.model;

public class ReservationModel {
    private final int confno;
    private final String vtname;
    private final int cellphone;
    private final int fromdatetime;
    private final int todatetime;

    public ReservationModel(int confno, String vtname, int cellphone, int fromdatetime, int todatetime) {
        this.confno = confno;
        this.vtname = vtname;
        this.cellphone = cellphone;
        this.fromdatetime = fromdatetime;
        this.todatetime = todatetime;
    }

    public int getConfno() {
        return confno;
    }

    public String getVtname() {
        return vtname;
    }

    public int getCellphone() {
        return cellphone;
    }

    public int getFromdatetime() {
        return fromdatetime;
    }

    public int getTodatetime() {
        return todatetime;
    }
}
