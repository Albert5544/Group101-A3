package ca.ubc.cs304.database;

import ca.ubc.cs304.model.*;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
			rollbackConnection();
		}
	}
	public int viewtheNumofAvaliableVehicle(String vtname, String fromDateTime, String toDateTime, String location) {
		int num=0;
		try {
			PreparedStatement ps = connection.prepareStatement("WITH bad(revn) AS( " +
																			"SELECT r1.vtname " +
																			"FROM RESERVATIONS r1 " +
																			"WHERE  ((TO_DATE(NVL(?,'01-JAN-9999 00:00'),'DD-MON-YYYY HH24:MI') BETWEEN r1.fromDateTime AND r1.toDateTime) " +
																			"OR (TO_DATE(NVL(?,'01-JAN-9999 00:00'),'DD-MON-YYYY HH24:MI') BETWEEN r1.fromDateTime AND r1.toDateTime))) " +
					                                                        "SELECT COUNT(*) FROM Vehicle v WHERE (v.vtname=? OR ? is null ) AND (v.location=? OR ? is null ) AND v.vtname NOT IN (SELECT b.revn FROM bad b)");
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

			ResultSet rs= ps.executeQuery();
			rs.next();
			num=rs.getInt(1);
		//	System.out.println("There are" +rs.getInt(1)+ "vehicles are available");

			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
			rollbackConnection();
		}
		return num;
	}


	public ArrayList<VehicleModel> checkforDetail (String vtname, String fromDateTime, String toDateTime, String location){
		ArrayList<VehicleModel> vs=new ArrayList<>();
		try {

			PreparedStatement ps = connection.prepareStatement("WITH bad(revn) AS( " +
					"SELECT r1.vtname" +
					" FROM Reservations r1 " +
					"WHERE  ((TO_DATE(NVL(?,'01-JAN-9999 00:00'),'DD-MON-YYYY HH24:MI') BETWEEN r1.fromDateTime AND r1.toDateTime) " +
					"OR (TO_DATE(NVL(?,'01-JAN-9999 00:00'),'DD-MON-YYYY HH24:MI') BETWEEN r1.fromDateTime AND r1.toDateTime))) " +
					"SELECT * FROM VEHICLE v WHERE (v.vtname=? OR ? is null ) AND (v.location=? OR ? is null ) AND v.vtname NOT IN (SELECT revn FROM bad b) ORDER by v.VTNAME");
			if(vtname==null){
				ps.setNull(3, Types.VARCHAR);
				ps.setNull(4, Types.VARCHAR);
			}else{ps.setString(3, vtname);
				ps.setString(4, vtname);}

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

			ResultSet rs= ps.executeQuery();
			System.out.println(rs.getRow());
			while(rs.next()){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				VehicleModel v=new VehicleModel(rs.getString(1),rs.getInt(2),
					rs.getString(3),rs.getString(4), formatter.format(rs.getDate(5)),
					rs.getString(6),rs.getInt(7),rs.getString(8),
					rs.getString(9),rs.getString(10));
			vs.add(v);}
			connection.commit();
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
			rollbackConnection();
		}
		return vs;
	}

	public void RentingAVehicle(RentModel rm) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Rent VALUES (?,?,?,TO_DATE(?,'DD-MON-YYYY HH24:MI'),TO_DATE(?,'DD-MON-YYYY HH24:MI'),?,?,?,TO_DATE(?,'DD-MON-YYYY'),?)");
			ps.setInt(1, rm.getRid());
			ps.setString(2, rm.getVlicense());
			ps.setString(3, rm.getDlicense());
			ps.setString(4, rm.getFromDateTime());
            ps.setString(5, rm.getToDateTime());
            ps.setInt(6, rm.getOdometer());
            ps.setString(7, rm.getCardName());
            ps.setInt(8, rm.getCardNo());
            ps.setString(9,rm.getExpDate());
            if(rm.getConfNo()==null){ps.setNull(10, Types.INTEGER);}
            else ps.setInt(10, rm.getConfNo());

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
			rollbackConnection();
		}
	}
    public void InsertCustomer(CustomerModel model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?)");
            ps.setInt(1, model.getCellphone());
            ps.setString(2, model.getName());
            ps.setString(3, model.getAddress());
            ps.setInt(4, model.getDlicense());


            ps.executeUpdate();
            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
            rollbackConnection();
        }
    }

    public RentModel findResInfo(Integer confNo){
        RentModel r=null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESERVATIONS r1 WHERE r1.CONFNO=?");
            ps.setInt(1, confNo);

            ps.executeUpdate();
            ResultSet rs= ps.executeQuery();
            if(!rs.next())
            	return null;
            connection.commit();



            String dlicense=rs.getString("dlicense");

            Date fromDateTimeD=rs.getDate("fromDateTime");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
            String fromDateTime = formatter.format(fromDateTimeD);

            Date toDateTimeD=rs.getDate("toDateTime");
            String toDateTime = formatter.format(toDateTimeD);
            ps.close();
            r=new RentModel(null,null,dlicense,fromDateTime,toDateTime,null,null,null,null,confNo);

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
        return r;

    }
	
	public CustomerModel[] getBranchInfo() {
		ArrayList<CustomerModel> result = new ArrayList<CustomerModel>();
		
		try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RESERVATIONS r1 WHERE r1.CONFNO=?");
//            ps.setInt(1, confNo);

            ps.executeUpdate();
            ResultSet rs= ps.executeQuery();
            if(!rs.next())
                return null;

            connection.commit();
            ps.close();



		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);

        }
		
		return result.toArray(new CustomerModel[result.size()]);
	}


	public RentModel returnV(ReturnModel rm){
        RentModel r=null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM RENT r WHERE r.RID=?");
            ps.setInt(1, rm.getRid());
            ps.executeUpdate();
            ResultSet rs= ps.executeQuery();
            if(!rs.next())
                return null;
            PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM RETURN re WHERE re.RID=?");
            ps2.setInt(1, rm.getRid());
            ps2.executeUpdate();
            ResultSet rs2= ps.executeQuery();
            if(rs2.next()){
                return null;
            }




            String dlicense=rs.getString("dlicense");

            Date fromDateTimeD=rs.getDate("fromDateTime");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
            String fromDateTime = formatter.format(fromDateTimeD);


            Date toDateTimeD=rs.getDate("toDateTime");
            String toDateTime = formatter.format(toDateTimeD);
            Integer confNo=rs.getInt("CONFNO");
            String vlicense=rs.getString("VLICENSE");
            ps.close();
            r=new RentModel(rm.getRid(),vlicense,dlicense,fromDateTime,toDateTime,null,null,null,null,confNo);

            PreparedStatement psD = connection.prepareStatement("INSERT INTO RETURN r VALUES (?,TO_DATE(?,'DD-MON-YYYY HH24:MI'),?,?,?)");
            psD.setInt(1, rm.getRid());
            psD.setString(2, rm.getDatetime());
            psD.setInt(3, rm.getOdometer());
            psD.setInt(4, rm.getFullTank());

            psD.setInt(5, rm.getValue());
            psD.executeUpdate();
            psD.close();
            




            connection.commit();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
        }

        return r;


    }

    public ArrayList<VehicleModel> DRentalB(String location,String city,String day){
	    ArrayList<VehicleModel> vms=new ArrayList<VehicleModel>();
        System.out.println(day);
        try{PreparedStatement ps = connection.prepareStatement("SELECT * FROM VEHICLE v " +
                                                                "WHERE v.LOCATION=? AND v.city=?" +
                                                                " AND v.VLICENSE IN (SELECT r.VLICENSE FROM RENT r WHERE TO_DATE(?,'DD-MON-YYYY')=trunc(FROMDATETIME, 'DD'))" +
                                                                "ORDER BY v.VTNAME");
        ps.setString(1, location);
        ps.setString(2, city);
        ps.setString(3, day);
        ps.executeUpdate();
        ResultSet rs= ps.executeQuery();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        while(rs.next()){
            System.out.println(rs.getString(1));
            VehicleModel vm=new VehicleModel(rs.getString(1),rs.getInt(2),rs.getString(3),
                                            rs.getString(4),formatter.format(rs.getDate(5)),rs.getString(6),
                                            rs.getInt(7),rs.getString(8),rs.getString(9),
                                            rs.getString(10));
            vms.add(vm);

        }
            connection.commit();
        ps.close();
        }
        catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return vms;

    }


    public ArrayList<Model[]> DReturnB(String location, String city, String day){
        ArrayList<Model[]> vms=new ArrayList<Model[]>();
        System.out.println(day);
        try{PreparedStatement ps = connection.prepareStatement("SELECT * FROM VEHICLE v,RETURN re" +
                " WHERE v.LOCATION=? AND v.city=?" +
                " AND v.VLICENSE IN (SELECT r.VLICENSE FROM RENT r WHERE re.rid=r.rid AND TO_DATE(?,'DD-MON-YYYY')=trunc(RDATETIME, 'DD'))" +
                "ORDER BY v.VTNAME");
            ps.setString(1, location);
            ps.setString(2, city);
            ps.setString(3, day);
            ps.executeUpdate();
            ResultSet rs= ps.executeQuery();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
            while(rs.next()){
                System.out.println(rs.getString(1));
                VehicleModel vm=new VehicleModel(rs.getString(1),rs.getInt(2),rs.getString(3),
                        rs.getString(4),formatter.format(rs.getDate(5)),rs.getString(6),
                        rs.getInt(7),rs.getString(8),rs.getString(9),
                        rs.getString(10));
                ReturnModel rm=new ReturnModel(null,null,null,null,rs.getInt("value"));
                Model rvm[] ={vm,rm};
                vms.add(rvm);

            }ps.close();
            connection.commit();
        }
        catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
        }
        return vms;
    }

public boolean ifExistB(String Location, String City){
boolean ret=false;
    try {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Vehicle WHERE LOCATION=? AND CITY=?");
        ps.setString(1, Location);
        ps.setString(2, City);

        ps.executeUpdate();
        ResultSet rs= ps.executeQuery();
        ret=rs.next();

        connection.commit();
        ps.close();



    } catch (SQLException e) {
        System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        rollbackConnection();
    }

    return ret;
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
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
			rollbackConnection();
		}	
	}

	public ArrayList<BranchModel> findAllBranch(){
	    ArrayList<BranchModel> bms=new ArrayList<>();

        try {
        PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT v.city,v.location FROM VEHICLE v");
            ps.executeUpdate();

            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                BranchModel bm=new BranchModel(rs.getString(1),rs.getString(2));
                bms.add(bm);
            }

            connection.commit();

            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            JOptionPane.showMessageDialog(new JPanel(), e.getMessage(),"ERROR", JOptionPane.WARNING_MESSAGE);
            rollbackConnection();
        }
        return bms;
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
