package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.CustomerModel;
import ca.ubc.cs304.model.RentModel;
import ca.ubc.cs304.model.ReturnModel;
import ca.ubc.cs304.model.VehicleModel;

import java.util.ArrayList;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {

	public void insertBranch(CustomerModel model);
	public void showBranch();
	public void updateBranch(int branchId, String name);
	public int vicenteNumbAvailableVehicle(String vtname, String fromDateTime, String toDateTime, String location);


	public RentModel findRevInfo(Integer cofNo);
	public void terminalTransactionsFinished();
	public RentModel returnV(ReturnModel rm);

	public void RentingAVehicle(RentModel rm);
	public ArrayList<VehicleModel> DRentalB(String location,String city,String day);
    public ArrayList<VehicleModel> checkforDetail (String vtname, String fromDateTime, String toDateTime, String location);
}
