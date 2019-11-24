package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.*;

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
	public ArrayList<Model[]> DReturnB(String location, String city, String day);
    public ArrayList<VehicleModel> checkforDetail (String vtname, String fromDateTime, String toDateTime, String location);
	public ArrayList<BranchModel> findAllBranch();
	public boolean ifExistB(String Location, String City);
//    public void  makeReservation(int confno, String vtname, int dlicense, String fromDateTime, String toDateTime);
    public void  makeReservation(ReservationModel rm);
    public boolean isValidReservation(String location, String vtname, String fromDateTime, String toDateTime);
	}
