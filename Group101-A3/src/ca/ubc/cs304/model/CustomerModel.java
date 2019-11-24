package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class CustomerModel {
	private final int cellphone;
	private final String name;
	private final String address;
	private final int dlicense;

	
	public CustomerModel(int cellphone, String name, String address, int dlicense){
		this.cellphone = cellphone;
		this.name = name;
		this.address = address;
		this.dlicense = dlicense;
	}

	public String getAddress() {
		return address;
	}

	public int getCellphone() {
		return cellphone ;
	}

	public int getDlicense() {
		return dlicense;
	}

	public String getName() {
		return name;
	}

}
