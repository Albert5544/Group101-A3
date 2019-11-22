package ca.ubc.cs304.model;

public class RentalModel {
    private final int rid;
    private final int cellphone;
    private final int fromdatetime;
    private final int todatetime;
    private final int odometer;
    private final String cardname;
    private final int cardno;
    private final int expdate;
    private final int confno;
    private final String vlicense;

    public RentalModel(int rid, int cellphone, int fromdatetime, int todatetime, int odometer, String cardname, int cardno, int expdate, int confno, String vlicense) {
        this.rid = rid;
        this.cellphone = cellphone;
        this.fromdatetime = fromdatetime;
        this.todatetime = todatetime;
        this.odometer = odometer;
        this.cardname = cardname;
        this.cardno = cardno;
        this.expdate = expdate;
        this.confno = confno;
        this.vlicense = vlicense;
    }

    public int getRid() {
        return rid;
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

    public int getOdometer() {
        return odometer;
    }

    public String getCardname() {
        return cardname;
    }

    public int getCardno() {
        return cardno;
    }

    public int getExpdate() {
        return expdate;
    }

    public int getConfno() {
        return confno;
    }

    public String getVlicense() {
        return vlicense;
    }



}
