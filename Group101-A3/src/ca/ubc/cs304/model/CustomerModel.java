package ca.ubc.cs304.model;

public class CustomerModel {
    private final Integer cellphone;
    private final String name;
    private final String address;
    private final String dlicense;

    public CustomerModel(Integer cellphone, String name, String address, String dlicense) {
        this.cellphone = cellphone;
        this.name = name;
        this.address = address;
        this.dlicense = dlicense;
    }

    public Integer getCellphone() {
        return cellphone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDlicense() {
        return dlicense;
    }

}
