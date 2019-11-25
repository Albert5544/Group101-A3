package ca.ubc.cs304.model;

public class RentModel implements Model {
    Integer rid;
    String vlicense;
    String dlicense;
    String fromDateTime;
    String toDateTime ;
    Integer odometer;
    String cardName;
    Integer cardNo;
    String expDate;
    Integer confNo;


    public RentModel(Integer rid ,
                     String vlicense,
                     String dlicense,
                     String fromDateTime,
                     String toDateTime ,
                     Integer odometer,
                     String cardName, Integer cardNo,
                     String ExpDate, Integer confNo){
        this.rid = rid;
        this.vlicense = vlicense;
        this.dlicense = dlicense;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.odometer=odometer;
        this.cardName=cardName;
        this.cardNo=cardNo;
        this.expDate=ExpDate;
        this.confNo=confNo;
    }

    public Integer getRid() {
        return rid;
    }

    public String getVlicense() {
        return vlicense;
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

    public Integer getOdometer() {
        return odometer;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
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

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardNo(Integer cardNo) {
        this.cardNo = cardNo;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setConfNo(Integer confNo) {
        this.confNo = confNo;
    }

    public String getCardName() {
        return cardName;
    }

    public Integer getCardNo() {
        return cardNo;
    }

    public String getExpDate() {
        return expDate;
    }

    public Integer getConfNo() {
        return confNo;
    }
}
