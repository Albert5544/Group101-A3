package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TerminalTransactions;

import java.util.ArrayList;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Bank implements LoginWindowDelegate, TerminalTransactionsDelegate {
	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;

	public Bank() {
		dbHandler = new DatabaseConnectionHandler();
	}
	
	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}
	
	/**
	 * LoginWindowDelegate Implementation
	 * 
     * connects to Oracle database with supplied username and password
     */ 
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			// Once connected, remove login window and start text transaction flow
			loginWindow.dispose();

			TerminalTransactions transaction = new TerminalTransactions();
			transaction.showMainMenu(this);
		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}

//	@Override
//	public void viewtheNumofAvaliableVehicle(String vtname, Date fromDate, int fromTime, Date toDate, int toTime) {
//		dbHandler.viewtheNumofAvaliableVehicle(vtname,fromDate,fromTime,toDate,toTime);
//	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Insert a branch with the given info
	 */
    public void insertBranch(CustomerModel model) {
//    	dbHandler.RentingAVehicle(model);
    }

    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Delete branch with given branch ID.
	 */ 
    public void deleteBranch(int branchId) {
    	dbHandler.deleteBranch(branchId);
    }
    
    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Update the branch name for a specific ID
	 */

    public void updateBranch(int branchId, String name) {
    	dbHandler.updateBranch(branchId, name);
    }

	@Override
	public int vicenteNumbAvailableVehicle(String vtname, String fromDateTime, String toDateTime, String location) {
		return dbHandler.viewtheNumofAvaliableVehicle(vtname, fromDateTime, toDateTime,location);

	}

	@Override
	public void RentingAVehicle(RentModel rm) {
		dbHandler.RentingAVehicle(rm);
	}

	@Override
	public ArrayList<VehicleModel> DRentalB(String location, String city, String day) {
		return dbHandler.DRentalB(location,city,day);
	}

	@Override
	public ArrayList<Model[]> DReturnB(String location, String city, String day) {
		return  dbHandler.DReturnB( location,city,day);
	}

	@Override
	public ArrayList<VehicleModel> checkforDetail(String vtname, String fromDateTime, String toDateTime, String location) {
		return dbHandler.checkforDetail (vtname,fromDateTime, toDateTime, location);
	}

	@Override
	public ArrayList<BranchModel> findAllBranch() {
		return dbHandler.findAllBranch();
	}

	@Override
	public boolean ifExistB(String Location, String City) {
		return dbHandler.ifExistB(Location,City);
	}

	@Override
	public RentModel findRevInfo(Integer confNo) {
		return dbHandler.findResInfo(confNo);
	}


	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Displays information about varies bank branches.
	 */
    public void showBranch() {
//    	CustomerModel[] models = dbHandler.getBranchInfo();
//
//    	for (int i = 0; i < models.length; i++) {
//    		CustomerModel model = models[i];
//
//    		// simplified output formatting; truncation may occur
//    		System.out.printf("%-10.10s", model.getId());
//    		System.out.printf("%-20.20s", model.getName());
//    		if (model.getAddress() == null) {
//    			System.out.printf("%-20.20s", " ");
//    		} else {
//    			System.out.printf("%-20.20s", model.getAddress());
//    		}
//    		System.out.printf("%-15.15s", model.getCity());
//    		if (model.getPhoneNumber() == 0) {
//    			System.out.printf("%-15.15s", " ");
//    		} else {
//    			System.out.printf("%-15.15s", model.getPhoneNumber());
//    		}
//
//    		System.out.println();
//    	}
    }
	
    /**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that it is done with what it's 
     * doing so we are cleaning up the connection since it's no longer needed.
     */ 
    public void terminalTransactionsFinished() {
    	dbHandler.close();
    	dbHandler = null;
    	
    	System.exit(0);
    }

	@Override
	public RentModel returnV(ReturnModel rm) {
		return dbHandler.returnV(rm);
	}

	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		Bank bank = new Bank();
		bank.start();
	}
}
