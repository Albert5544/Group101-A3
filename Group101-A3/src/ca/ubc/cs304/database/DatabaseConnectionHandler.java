package ca.ubc.cs304.database;

import ca.ubc.cs304.model.BranchModel;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	//private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";

	private Connection connection = null;
	
	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void deleteBranch(int branchId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM branch WHERE branch_id = ?");
			ps.setInt(1, branchId);
			
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Branch " + branchId + " does not exist!");
			}
			
			connection.commit();
	
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	public void viewtheNumofAvaliableVehicle(String vtname, String fromDateTime, String toDateTime, String location) {
		try {


//			PreparedStatement psV=connection.prepareStatement(" CREATE VIEW comDT AS(" +
//					" SELECT r1.vtname AS vtname, TO_DATE(CONCAT(TO_CHAR(r1.fromDate,'DD-MON-YYYY'),r1.fromTime),'DD-MON-YYYY HH24:MI')AS fromDT, TO_DATE(CONCAT(TO_CHAR(r1.toDate,'DD-MON-YYYY'),r1.toTime),'DD-MON-YYYY HH24:MI')AS toDT "+
//					" FROM Reservations r1) " );
//			connection.commit();
//			psV.close();
			PreparedStatement ps = connection.prepareStatement(//"WITH" +

																			"WITH bad(revn) AS( " +
																			"SELECT r1.vtname" +
																			" FROM Reservations r1 " +
																			"WHERE  ((TO_DATE(NVL(?,'01-JAN-9999 00:00'),'DD-MON-YYYY HH24:MI') BETWEEN r1.fromDateTime AND r1.toDateTime) " +
																			"OR (TO_DATE(NVL(?,'01-JAN-9999 00:00'),'DD-MON-YYYY HH24:MI') BETWEEN r1.fromDateTime AND r1.toDateTime))) " +

																			//"r1.fromTime>? OR <TO_DATE(?,'DD-MON-YYYY') OR r1.toTime<?) "); //AS temp" +
//					                                                        "SELECT temp.c-cb.revn " +
//																					"FROM COUNT(*)," +
																					"SELECT COUNT(*)AS c FROM Vehicles v WHERE (v.vtname=? OR ? is null ) AND (v.location=? OR ? is null ) AND v.vtname NOT IN (SELECT revn FROM bad b)");
			if(vtname==null){
			    ps.setNull(3, Types.VARCHAR);
                ps.setNull(4, Types.VARCHAR);
//                ps.setNull(5, Types.VARCHAR);
//				ps.setNull(6, Types.VARCHAR);
            }else{ps.setString(3, vtname);
				ps.setString(4, vtname);}
//				ps.setString(5, vtname);
//				ps.setString(6, vtname);}


            if(fromDateTime==null){
                ps.setNull(1, Types.VARCHAR);
            }else ps.setString(1,fromDateTime);


            if(toDateTime==null){
                ps.setNull(2, Types.VARCHAR);
            }else ps.setString(2, toDateTime);

			if(location==null){
				ps.setNull(5, Types.VARCHAR);
				ps.setNull(6, Types.VARCHAR);
			}else {ps.setString(5, location);
				ps.setString(6, location);
			}




			//ps.setInt(3,fromTime);

		//	ps.setInt(5,toTime);

			ResultSet rs= ps.executeQuery();
			rs.next();
			System.out.println("There are" +rs.getInt(1)+ "vehicles are available");

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	
	public void insertBranch(BranchModel model) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO branch VALUES (?,?,?,?,?)");
			ps.setInt(1, model.getId());
			ps.setString(2, model.getName());
			ps.setString(3, model.getAddress());
			ps.setString(4, model.getCity());
			if (model.getPhoneNumber() == 0) {
				ps.setNull(5, java.sql.Types.INTEGER);
			} else {
				ps.setInt(5, model.getPhoneNumber());
			}

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	
	public BranchModel[] getBranchInfo() {
		ArrayList<BranchModel> result = new ArrayList<BranchModel>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM branch");
		
//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
			
			while(rs.next()) {
				BranchModel model = new BranchModel(rs.getString("branch_addr"),
													rs.getString("branch_city"),
													rs.getInt("branch_id"),
													rs.getString("branch_name"),
													rs.getInt("branch_phone"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}	
		
		return result.toArray(new BranchModel[result.size()]);
	}
	
	public void updateBranch(int id, String name) {
		try {
		  PreparedStatement ps = connection.prepareStatement("UPDATE branch SET branch_name = ? WHERE branch_id = ?");
		  ps.setString(1, name);
		  ps.setInt(2, id);
		
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println(WARNING_TAG + " Branch " + id + " does not exist!");
		  }
	
		  connection.commit();
		  
		  ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}	
	}

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, "ora_cui10", "a32968299");
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }


	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
