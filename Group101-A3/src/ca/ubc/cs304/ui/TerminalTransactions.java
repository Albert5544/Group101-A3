package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class TerminalTransactions extends JFrame implements ActionListener {
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	int choice = INVALID_INPUT;

	private BufferedReader bufferedReader = null;
	private TerminalTransactionsDelegate delegate = null;
	private JFrame f;
	JPanel contentPane;
	JTextField input;
	JButton homeButtom = new JButton("Back");

	public TerminalTransactions() {
		super("Super Rent");
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		setResizable(true);
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(new GridLayout(5, 1, 5, 5));

		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		homeButtom.setActionCommand("");
		homeButtom.addActionListener(this);
	}

	/**
	 * Displays simple text interface
	 */
	JRadioButton customer = new JRadioButton("Customer");// 初始化单选框
	//first.setFont(new Font("Arial",Font.PLAIN,16));// 设置字体
	JRadioButton clerk = new JRadioButton("Clerk");
	JRadioButton manager = new JRadioButton("Manager");

	public void showMainMenu(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		JLabel q = new JLabel("I am a");

		clerk.setSize(200, 50);
		//second.setFont(new Font("Arial",Font.PLAIN,16));

		ButtonGroup bg = new ButtonGroup();// 初始化按钮组
		bg.add(customer);// 加入按钮组
		bg.add(clerk);
		bg.add(manager);// 加入按钮组

		customer.setSelected(true);// 选择

		//customer.isSelected();// 获取radiobutton的选择状态
		q.setFont(new java.awt.Font("Segoe UI Light", Font.BOLD, 17));
		manager.setFont(new java.awt.Font("Segoe UI Light", Font.BOLD, 17));
		customer.setFont(new java.awt.Font("Segoe UI Light", Font.BOLD, 17));
		clerk.setFont(new java.awt.Font("Segoe UI Light", Font.BOLD, 17));

		JButton ok = new JButton("OK");
		ok.addActionListener(this);
		ok.setActionCommand("ok_who");


		contentPane.add(q);
		contentPane.add(customer);
		contentPane.add(clerk);
		contentPane.add(manager);
		contentPane.add(ok);

		this.pack();
		Dimension d = this.getToolkit().getScreenSize();
		Rectangle r = this.getBounds();
		this.setSize(600, 300);
		this.setLocation((d.width - r.width) / 2, (d.height - r.height) / 2);

		// make the window visible
		this.setVisible(true);

	}

	private void showMainMenu_clerk() {
		this.setSize(600, 300);
		contentPane.setLayout(new GridLayout(5, 1, 5, 5));
		contentPane.removeAll();
		//     JLabel options1=new JLabel("1. Search for the number of available vehicle");
		JLabel options1 = new JLabel("1. Rent a Vehicle");
		JLabel options2 = new JLabel("2. Return a Vehicle");
		input = new JTextField();
		contentPane.add(input);
		JButton ok_function = new JButton("OK");
		ok_function.addActionListener(this);
		ok_function.setActionCommand("ok_function");
		contentPane.add(options1);
		contentPane.add(options2);
//		contentPane.add(options3);
//		contentPane.add(options4);
		contentPane.add(input);
		contentPane.add(ok_function);
		this.setVisible(true);
		contentPane.repaint();

	}

	private void showMainMenu_manager() {
		this.setSize(600, 300);
		contentPane.setLayout(new GridLayout(5, 1, 5, 5));
		contentPane.removeAll();
		//JLabel options1=new JLabel("1. Rent a Vehicle");
		//JLabel options2=new JLabel("2. Return a Vehicle");
		JLabel options1 = new JLabel("1. Daily Rental Report");
		JLabel options2 = new JLabel("2. Daily Rental Report for a branch");
		JLabel options3 = new JLabel("3. Daily Return Report");
		JLabel options4 = new JLabel("4. Daily Return Report for a branch");
		input = new JTextField();
		contentPane.add(input);
		JButton ok_function = new JButton("OK");
		ok_function.addActionListener(this);
		ok_function.setActionCommand("ok_function");
		contentPane.add(options1);
		contentPane.add(options2);
		contentPane.add(options3);
		contentPane.add(options4);
		contentPane.add(input);
		contentPane.add(ok_function);
		this.setVisible(true);
		contentPane.repaint();

	}

	private void showMainMenu_customer() {
		this.setSize(600, 300);
		contentPane.setLayout(new GridLayout(5, 1, 5, 5));
		contentPane.removeAll();
		JLabel options1 = new JLabel("1. Search for the number of available vehicle and make a reservation");
		input = new JTextField();
		contentPane.add(input);
		JButton ok_function = new JButton("OK");
		ok_function.addActionListener(this);
		ok_function.setActionCommand("ok_function");
		contentPane.add(options1);
		contentPane.add(input);
		contentPane.add(ok_function);

		this.setVisible(true);
		contentPane.repaint();

	}


	public void showMainMenu_Respond(int choice, TerminalTransactionsDelegate delegate, int who) {
		this.delegate = delegate;
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		if (who == 1) {
			if (choice != INVALID_INPUT) {
				switch (choice) {
					case 1:
						checkforAva();
						break;
					case 2:
						handleQuitOption();
						break;
					default:
						System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
						break;
				}
			}
		}
		if (who == 2) {
			if (choice != INVALID_INPUT) {
				switch (choice) {
					case 1:
						rentVehicle();
						break;
					case 2:
						returnVehicle();
						break;
					case 3:
						handleQuitOption();
						break;
					default:
						System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
						break;

				}
			}
		}

		if (who == 3) {
			if (choice != INVALID_INPUT) {
				switch (choice) {
					case 1:
						dailyRentalReport();
						break;
					case 2:
						dailyRentalReportBranch();
						break;
					case 3:
						dailyReturnReport();
						break;
					case 4:
						dailyReturnReportBranch();
						break;
					case 5:
						handleQuitOption();
						break;
					default:
						System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
						break;

				}
			}
		}
	}

	JTextField confT = new JTextField();

	private void rentVehicle() {
		contentPane.removeAll();

		JLabel confNoL = new JLabel("Enter the confirmation number if the customer have one (if the customer has no reservation, leave it empty:");
		JButton okB = new JButton("OK");
		contentPane.add(confNoL);
		contentPane.add(confT);
		contentPane.add(okB);
		okB.addActionListener(this);
		okB.setActionCommand("conf_num");
		contentPane.repaint();
		validate();
	}
	Integer rid = null;
	String vlicense = null;
	String dlicense = null;
	String rv_fromDateTime = null;
	String rv_toDateTime = null;
	Integer odometer = null;
	String cardName = null;
	Integer cardNo = INVALID_INPUT;
	String expDate = null;
	RentModel rm=null;
	Integer confNo=INVALID_INPUT;

	JTextField rv_ridField=new JTextField();
	JTextField vlicenseField=new JTextField();
	JTextField rv_dlicenseField=new JTextField();
	JTextField rv_from_DT_Field=new JTextField();
	JTextField rv_to_DT_Field=new JTextField();
	JTextField rv_odometerField=new JTextField();
	JTextField cNameField=new JTextField();
	JTextField cNoField=new JTextField();
	JTextField expDField=new JTextField();
	private void rentVehicle_helper() {
		contentPane.removeAll();
		contentPane.setLayout(new GridLayout(10,1,5,5));
		if(!confT.getText().equals(""))
		confNo = Integer.valueOf(confT.getText());
		else confNo=null;
		rm = delegate.findRevInfo(confNo);
		if (rm == null &&(!confT.getText().equals(""))) {
			JOptionPane.showMessageDialog(new JPanel(), "There is no reservation corresponds to the input confirmation number", "ERROR", JOptionPane.WARNING_MESSAGE);
		}


			add(new JLabel("Please enter the rentID: "));
			add(rv_ridField);

			add(new JLabel("Please enter the vehicle License number: "));
			add(vlicenseField);

		if (confNo == null || rm == null) {

				add(new JLabel("Please enter the driverLicense number: "));
				add(rv_dlicenseField);


				add(new JLabel("Please enter the Begin Date and Time for this rent(In the format of DD-MON-YYYY HH24:MI)"));
				add(rv_from_DT_Field);


				add(new JLabel("Please enter the End Date and Time for this rent(In the format of DD-MON-YYYY HH24:MI)"));
				add(rv_to_DT_Field);


		}

			add(new JLabel("Please enter the Current value of odometer for vehicle"));
			add(rv_odometerField);


		add(new JLabel("Please enter the card holder Name:"));
			add(cNameField);


		add(new JLabel("Please enter the card number:"));
			add(cNoField);

		add(new JLabel("Please enter the expDate for the card (In the format of DD-MON-YYYY)"));
			add(expDField);
			JButton ok=new JButton("OK");
			ok.setActionCommand("ok_rent_info");
			ok.addActionListener(this);
			add(ok);
			add(homeButtom);
			contentPane.repaint();
			validate();

	}
	private void rentVehicle_helper_helper(){
		if (rm!= null) {dlicense=rm.getDlicense();rv_fromDateTime=rm.getFromDateTime();rv_toDateTime=rm.getToDateTime();}
		if(rid==null||vlicense==null||dlicense==null||
				rv_fromDateTime==null|| rv_toDateTime==null||
				odometer==null|| cardName==null||cardNo==null|| expDate==null){
			JOptionPane.showMessageDialog(new JPanel(), "The given information is not enough","ERROR", JOptionPane.WARNING_MESSAGE);
		return;}
		contentPane.removeAll();
		if (rm == null) {
			rm = new RentModel(rid,
					vlicense,
					dlicense,
					rv_fromDateTime,
					rv_toDateTime,
					odometer,
					cardName, cardNo,
					expDate, confNo);
		} else {
			rm.setCardName(cardName);
			rm.setCardNo(cardNo);
			rm.setExpDate(expDate);
			rm.setRid(rid);
			rm.setVlicense(vlicense);
			rm.setOdometer(odometer);
		}

		delegate.RentingAVehicle(rm);
		JLabel receipt = new JLabel("Receipt");
		receipt.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
		JLabel confNum = new JLabel("Confirmation Number:   " + String.valueOf(confNo));
		JLabel dateBegin = new JLabel("Begin at:   " + fromDateTime);
		JLabel dateEnd = new JLabel("Need to return at:   " + toDateTime);
		JLabel vlicenseR = new JLabel("Vehicle License Number:   " + vlicense);
		contentPane.removeAll();
		add(receipt);
		add(confNum);
		add(dateBegin);
		add(dateEnd);
		add(vlicenseR);
		add(homeButtom);
		repaint();
		validate();
	}




	String vtname = null;
	String fromDateTime=null;
	String toDateTime=null;
	String location=null;
	String city=null;
	JLabel type=new JLabel("Please enter the type you wish to check: ");
	JTextField aType=new JTextField();
	JLabel fromDT=new JLabel("Please enter the Date and Time you wish to start: ");
	JTextField aFromDT=new JTextField();
	JLabel toDT=new JLabel("Please enter the Date and Time you wish to return: ");
	JTextField aToDT=new JTextField();
	JLabel Llocation=new JLabel("Please enter the Location you wish to pick up the vehicle: ");
	JTextField aLocation=new JTextField();
	JLabel qCity=new JLabel("Please enter the City you wish to pick up the vehicle: ");
	JTextField aCity=new JTextField();



	private void checkforAva() {
		contentPane.setLayout(new GridLayout(7,1,5,5));

		contentPane.removeAll();
		JLabel empty=new JLabel();
		JButton okButton = new JButton("ok");
		okButton.setActionCommand("ok_check");
		okButton.addActionListener(this);
		contentPane.add(type);
		contentPane.add(aType);
		contentPane.add(toDT);
		contentPane.add(aToDT);
		contentPane.add(fromDT);
		contentPane.add(aFromDT);
		contentPane.add(Llocation);
		contentPane.add(aLocation);
		contentPane.add(qCity);
		contentPane.add(aCity);
		contentPane.add(okButton);
		contentPane.add(homeButtom);

		setVisible(true);
		repaint();
		validate();
		//contentPane.removeAll();
	}


		public void check_for_Ava_helper(){
		contentPane.setLayout(new GridLayout(5,1,5,5));
			this.setSize(600, 300);
			int numV=delegate.vicenteNumbAvailableVehicle(vtname,fromDateTime,toDateTime,location,city);

			contentPane.removeAll();
			JLabel showNum=new JLabel("The number of available vehicles:");
			add(showNum);
			JButton numButton=new JButton(String.valueOf(numV));
			numButton.addActionListener(this);
			numButton.setActionCommand("detailV");
			contentPane.add(numButton);

			JButton make_reservation=new JButton("MAKE RESERVATION NOW!");
			make_reservation.addActionListener(this);
			make_reservation.setActionCommand("ok_check_to_res");
			contentPane.add(make_reservation);
			contentPane.add(homeButtom);
			setVisible(true);
			contentPane.repaint();
		}
		public void detailedV(){
			contentPane.removeAll();
			contentPane.setLayout(new GridLayout(3,1,5,5));
			vBoxReport.removeAll();
			ArrayList<VehicleModel> vms=delegate.checkforDetail(vtname,fromDateTime,toDateTime,location);
			JLabel unSure=new JLabel("Here is the list of available vehicles, " +
					"please notice there may be more cars listed than the available number shown previously because these are all possible cars that can be rented. There is no guarantee which specific car you can rent since there may be other customers has already rented that car.");
			unSure.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
			add(unSure);

			tableforV(vms);
			add(vBoxReport);
			JButton backButton=new JButton("Back");
			backButton.addActionListener(this);
			backButton.setActionCommand("back_to_num");
			contentPane.add(backButton);
			setVisible(true);
			contentPane.repaint();
			validate();

		}


		public void tableforV(ArrayList<VehicleModel> vms){
			int size=vms.size();
			System.out.println(size);
			String[][] detailInfo=new String[size][10];
			for(int i=0; i<vms.size();i++){
				VehicleModel curr= vms.get(i);
				detailInfo[i]= new String[]{curr.getVlicense(),String.valueOf(curr.getVid()),
						curr.getMake(), curr.getModel(), curr.getYear(), curr.getColor(),
						String.valueOf(curr.getOdometer()), curr.getVtname(), curr.getLocation(), curr.getCity()};
			}

			String[] Names = { "Vehicle License","VehicleID",  "Make", "Model",
					"Year", "Color", "Odometer", "TypeName", "Location", "City"};
			JTable table = new JTable(detailInfo, Names);
			table.setPreferredScrollableViewportSize(new Dimension(550, 100));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setSize(1200,800);
			setSize(1200,700);
			vBoxReport.add(scrollPane);
			vBoxReport.repaint();
		}
	JTextField ridField=new JTextField("0");
	SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyy HH:mm");
	Date date = new Date(System.currentTimeMillis());
	JTextField RDTField=new JTextField(formatter.format(date));

	JTextField odometerField=new JTextField("0");
	JTextField fulltankField=new JTextField("0");
	JTextField valueField=new JTextField("0");
		public void returnVehicle(){
			contentPane.removeAll();
			contentPane.setLayout(new FlowLayout());

			JLabel rid=new JLabel("Please enter the rentID");
			JLabel RDT=new JLabel("Please enter the ReturnDate");
			JLabel odometer=new JLabel("Please enter the odometer value");
			JLabel fulltank=new JLabel("If the tank is full, enter 1, otherwise 0");
			JLabel value=new JLabel("Please enter the value");
			JButton ok=new JButton("OK");
			ok.setActionCommand("ok_rid");
			ok.addActionListener(this);

			Box vBox = Box.createVerticalBox();


			vBox.add(rid);
			vBox.add(ridField);

			vBox.add(RDT);
			vBox.add(RDTField);

			vBox.add(odometer);
			vBox.add(odometerField);

			vBox.add(fulltank);
			vBox.add(fulltankField);

			vBox.add(value);
			vBox.add(valueField);

			vBox.add(ok);
			vBox.setVisible(true);
			contentPane.add(vBox);
			contentPane.repaint();
			validate();

		}


		public void returnVehicle_helper(ReturnModel returnModel) {
			contentPane.setLayout(new GridLayout(6,1,5,5));
			RentModel rm = delegate.returnV(returnModel);
			if (rm == null) {
				JOptionPane.showMessageDialog(new JPanel(), "There is no rent record with the input rid or it has already been returned","ERROR", JOptionPane.WARNING_MESSAGE);
			return;} else {
				int confNo=rm.getConfNo();
				String fromDateTime=rm.getFromDateTime();
				String toDateTime=rm.getToDateTime();
				String vlicense=rm.getVlicense();
				JLabel receipt=new JLabel("Receipt");
				receipt.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
				JLabel confNum=new JLabel("Confirmation Number:   "+String.valueOf(confNo));
				JLabel dateBegin=new JLabel("Begin at:   "+fromDateTime);
				JLabel dateEnd=new JLabel("Need to return at:   "+toDateTime);
				JLabel AdateEnd=new JLabel("Actual return at:   "+returnModel.getDatetime());
				JLabel vlicenseR=new JLabel("Vehicle License Number:   "+vlicense);
				contentPane.removeAll();
				contentPane.setLayout(new GridLayout(5,1,5,5));
				add(receipt);
				add(confNum);
				add(dateBegin);
				add(dateEnd);
				add(AdateEnd);
				add(vlicenseR);
				repaint();
				validate();
			}
		}

	JTextField ansB=new JTextField(null);
	JTextField ansBC=new JTextField(null);
	SimpleDateFormat formatterDay= new SimpleDateFormat("dd-MMM-yyy");
	JTextField ansDay=new JTextField(formatterDay.format(date));


	public void dailyRentalReportBranch(){
			contentPane.removeAll();
		contentPane.setLayout(new FlowLayout());
			JLabel askB=new JLabel("The location of the branch:");

			JLabel askBC=new JLabel("The city of the branch:");

		JLabel askDay=new JLabel("Which day(In the format of dd-Mon-yyy):");
		Box vBox = Box.createVerticalBox();
		vBox.add(askB);
		vBox.add(ansB);
		vBox.add(askBC);
		vBox.add(ansBC);
		vBox.add(askDay);
		vBox.add(ansDay);


		JButton ok=new JButton("OK");
		ok.setActionCommand("ok_branch");
		ok.addActionListener(this);
		vBox.add(ok);
		vBox.setVisible(true);
		add(homeButtom);
		contentPane.add(vBox);
		contentPane.repaint();
		validate();

	}
	public void dailyReturnReportBranch(){
		contentPane.removeAll();
		contentPane.setLayout(new FlowLayout());
		JLabel askB=new JLabel("The location of the branch:");

		JLabel askBC=new JLabel("The city of the branch:");

		JLabel askDay=new JLabel("Which day(In the format of dd-Mon-yyy):");
		Box vBox = Box.createVerticalBox();
		vBox.add(askB);
		vBox.add(ansB);
		vBox.add(askBC);
		vBox.add(ansBC);
		vBox.add(askDay);
		vBox.add(ansDay);


		JButton ok=new JButton("OK");
		ok.setActionCommand("ok_branch_return");
		ok.addActionListener(this);
		vBox.add(ok);
		vBox.add(homeButtom);
		vBox.setVisible(true);
		contentPane.add(vBox);
		contentPane.repaint();
		validate();

	}
	JTextField day=new JTextField(formatterDay.format(new Date(System.currentTimeMillis())));

	public void dailyRentalReport(){
		contentPane.removeAll();
		add(new JLabel("Please enter the date (in the format of DD-MON-YYYY)"));
		contentPane.add(day);
		JButton ok=new JButton("OK");
		ok.setActionCommand("ok_rental_all");
		ok.addActionListener(this);
		contentPane.add(ok);
		contentPane.repaint();
		validate();

	}
	public void dailyReturnReport(){

		contentPane.removeAll();
		add(new JLabel("Please enter the date (in the format of DD-MON-YYYY)"));
		contentPane.add(day);
		JButton ok=new JButton("OK");
		ok.setActionCommand("ok_return_all");
		ok.addActionListener(this);
		contentPane.add(ok);
		contentPane.repaint();
		validate();
		//reportPanel.removeAll();

	}
	Box vBoxReport = Box.createVerticalBox();


	public void dailyReturnReport_helper(String day){
		ArrayList<BranchModel> bms=delegate.findAllBranch();
		setLayout(new FlowLayout());
		int totalALL[];
		int totalRev=0;
		int totalNum=0;
		for (BranchModel bm : bms){
			totalALL=dailyReturnReportBranch_Helper(bm.getLocation(),bm.getCity(),day);
			totalRev+=totalALL[0];
			totalNum+=totalALL[1];
		}
		JLabel grandTotalRev=new JLabel("The grand totals revenue for the day is "+totalRev+"    ");
		grandTotalRev.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 40));
		contentPane.add(grandTotalRev);

		JLabel grandTotalNum=new JLabel("The grand totals number of returns for the day is "+totalNum);
		grandTotalNum.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 40));
		contentPane.add(grandTotalRev);
		contentPane.add(grandTotalNum);

		JPanel ReportPane=new JPanel();
		this.setSize(1300,900);
		ReportPane.setSize(1200,90);
		vBoxReport.setSize(1200,9000);
		ScrollPane scrollPane=new ScrollPane();
		scrollPane.setSize(1300,900);
		scrollPane.setWheelScrollingEnabled(true);
		scrollPane.add(ReportPane);
		ReportPane.add(vBoxReport);
		contentPane.add(scrollPane);
		contentPane.repaint();
	}


	public int dailyRentalReportBranch_Helper(String location,String city,String day){
		int counttotal=0;
		if(!delegate.ifExistB(location,city)){
			JOptionPane.showMessageDialog(new JPanel(), "There is no Branch corresponds to the input location and City","ERROR", JOptionPane.WARNING_MESSAGE);
		}
			ArrayList<VehicleModel> vms=delegate.DRentalB(location,city,day);
		JLabel locCity=new JLabel("For the branch located at "+location+" in "+city);
		locCity.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 30));
		vBoxReport.add(locCity);
		    contentPane.removeAll();
			tableforV(vms);


			JLabel num=new JLabel("There were "+vms.size()+" Vehicle was rented from "+city+" "+location+" in total during this day, the following is detailed information:");
		    num.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 24));
			vBoxReport.add(num);

			ArrayList<String> typeName=new ArrayList<>(Arrays.asList("Economy","SUV","Compact","Mid-size","Full-size","Truck"));
			for (int i=0;i<typeName.size();i++){
				int count=0;
			for (int j=0;j<vms.size();j++) {
				if (typeName.get(i).equals(vms.get(j).getVtname())) {
					count++;
				}
				counttotal+=count;
			}
				JLabel showCount=new JLabel(count+" "+typeName.get(i)+" rentals");
				vBoxReport.add(showCount);
			}
			//contentPane.add(vBoxReport);
//			contentPane.add(reportPanel);
		contentPane.repaint();
		contentPane.setVisible(true);
		contentPane.add(vBoxReport);
		contentPane.setVisible(true);
		validate();
		return counttotal;

	}



	public int[] dailyReturnReportBranch_Helper(String location,String city,String day){
		if(!delegate.ifExistB(location,city)){
			JOptionPane.showMessageDialog(new JPanel(), "There is no Branch corresponds to the input location and City","ERROR", JOptionPane.WARNING_MESSAGE);
		}

		JPanel reportPanel=new JPanel();
		ArrayList<Model[]> vrms=delegate.DReturnB(location,city,day);
		ArrayList<VehicleModel> vms=new ArrayList<>();
		ArrayList<ReturnModel> rms=new ArrayList<>();
		for(int i=0;i<vrms.size();i++){
			vms.add((VehicleModel) vrms.get(i)[0]);
			rms.add((ReturnModel) vrms.get(i)[1]);
		}
		JLabel locCity=new JLabel("For the branch located at "+location+" in "+city);
		locCity.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 30));
		vBoxReport.add(locCity);
		contentPane.removeAll();
		tableforV(vms);
		//Box vBox = Box.createVerticalBox();

		JLabel num=new JLabel("There were "+vms.size()+" Vehicle was returned to "+city+" "+location+" in total  during this day, the following is detailed information:");
		num.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 24));

		vBoxReport.add(num);

		ArrayList<String> typeName=new ArrayList<>(Arrays.asList("Economy","SUV","Compact","Mid-size","Full-size","Truck"));
		int totalCount=0;
		for (int i=0;i<typeName.size();i++){
			int count=0;
			for (int j=0;j<vms.size();j++) {
				if (typeName.get(i).equals(vms.get(j).getVtname())) {
					count++;
					totalCount++;
				}
			}
			JLabel showCount=new JLabel(count+" "+typeName.get(i)+" vehicles was(were) returned");
			vBoxReport.add(showCount);
		}
		int totalRevenue=0;
		//ArrayList<Integer> typeRevenue=new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
		for (int i=0;i<typeName.size();i++) {
			int rev = 0;
			for (int j = 0; j < vrms.size(); j++) {
				if (typeName.get(i).equals(((VehicleModel) vrms.get(j)[0]).getVtname())) {
					int v=((ReturnModel) vrms.get(j)[1]).getValue();
					rev += v;
					totalRevenue +=v;
				}
			}
			JLabel showRev=new JLabel("The total revenue for "+typeName.get(i)+" type of vehicles is "+rev);
			vBoxReport.add(showRev);
		}
		JLabel totalShow=new JLabel("The total revenue for this day is "+totalRevenue);
		vBoxReport.add(totalShow);
		contentPane.add(vBoxReport);
		reportPanel.repaint();
		contentPane.add(reportPanel);
		contentPane.setVisible(true);
		contentPane.validate();
		return new int[]{totalRevenue, totalCount};


	}
	private void handleQuitOption() {
		System.out.println("Good Bye!");
		
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}
		
		delegate.terminalTransactionsFinished();
	}
	

	private int readInteger(boolean allowEmpty) {
		String line = null;
		int input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Integer.parseInt(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}
	
	private String readLine() {
		String result = null;
		try {
			result = bufferedReader.readLine();
			if(result.length()==0){
				result=null;
			}
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result;
	}
JTextField dlicenseField =new JTextField();
	ReservationModel reservationModel=null;

	public void makeReservation(){

        contentPane.removeAll();

        JButton okButton = new JButton("ok");
        okButton.setActionCommand("ok_reserve");
        okButton.addActionListener(this);
        //add(aType);
        add(dlicenseField);
		JLabel dlicenseL =new JLabel("Please enter you driver license number");
		add(dlicenseL);
//        add(fromDT);
//        add(aFromDT);
//        add(toDT);
//        add(aToDT);
        add(okButton);
        add(homeButtom);
        setVisible(true);
        repaint();
        validate();
//    }


	}


	public void makeReservation_helper(ReservationModel resm) {
		Boolean exist=delegate.checkforCustomer(resm.getDlicense());
		if (!exist) {
			JOptionPane.showMessageDialog(new JPanel(), "You are a new customer, please register first","ERROR", JOptionPane.WARNING_MESSAGE);
			createAccount(resm.getDlicense());
		} else {
			if(resm.getToDateTime().equals("")||resm.getFromDateTime().equals("")||resm.getVtname().equals("")) {
				JOptionPane.showMessageDialog(new JPanel(), "You need to provide information about 'fromDateTime', 'toDateTime' and 'vehicle Type' to make reservation, please go back and recheck", "ERROR", JOptionPane.WARNING_MESSAGE);
				return;
			}
			delegate.makeReservation(resm);
			int confNo=resm.getConfno();
			String fromDateTime=resm.getFromDateTime();
			String toDateTime=resm.getToDateTime();
			String vtname = resm.getVtname();
			String dlicense=resm.getDlicense();
			JLabel reservationReceipt=new JLabel("Reservation Receipt");
			reservationReceipt.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
			JLabel confNum=new JLabel("Confirmation Number:   "+String.valueOf(confNo));
			JLabel dlicenseL=new JLabel("Drive License Numver:   "+String.valueOf(dlicense));
			JLabel dateBegin=new JLabel("Begin at:   "+fromDateTime);
			JLabel dateEnd=new JLabel("Need to return at:   "+toDateTime);
			JLabel vtnameL=new JLabel("Vehicle Type name:   "+vtname);
			contentPane.removeAll();
			contentPane.setLayout(new GridLayout(5,1,5,5));
			add(confNum);
			add(dlicenseL);
			add(dateBegin);
			add(dateEnd);
			add(vtnameL);
			repaint();
			validate();
		}
	}
String dlicenseField_c=null;
	JTextField nameField_c=new JTextField();
	JTextField cellphoneField_c=new JTextField();
	  JTextField addressField_c=new JTextField();

	private void createAccount(String dlicense) {
		contentPane.removeAll();
		dlicenseField_c=dlicense;
//			add(new JLabel("Please enter your Driver's License number: "));
//			add(dlicenseField_c);
//			add(new JLabel("Licence number already exists. Please enter a different licence number: "));
//			dLicense = getLicense(false);

		contentPane.add(new JLabel("Please enter your name: "));
		contentPane.add(nameField_c);
		contentPane.add(new JLabel(" Please enter your cellphone number: "));
		contentPane.add(cellphoneField_c);
		contentPane.add(new JLabel("Please enter your address: "));
		contentPane.add(addressField_c);
		JButton ok=new JButton("OK");
		ok.setActionCommand("new_cust");
		ok.addActionListener(this);
		contentPane.add(ok);
		contentPane.repaint();
		setVisible(true);
//		delegate.insertCustomer(customer);
//		System.out.println();
//		System.out.print("Customer account created");
//		System.out.println();
	}
	 private void createAccount_helper(CustomerModel cm){
		delegate.insertCustomer(cm);
		JOptionPane.showMessageDialog(new JPanel(), "Customer Registered successfully", "message", JOptionPane.INFORMATION_MESSAGE);
		showMainMenu_customer();
	 }

	//	private CustomerModel createAccount(CustomerModel CustomerModel){
//		CustomerModel cm = delegate.insertCustomer(CustomerModel);
//		if (cm == null) {
//			JOptionPane.showMessageDialog(new JPanel(), "Cannot make the create the Account","ERROR", JOptionPane.WARNING_MESSAGE);
//		}
//
//		if(delegate.customerExist(dlicense)) {
//			System.out.print("Licence number already exists. Please enter a different licence number: ");
//			////should handle to return to the login in.
//		} else{
//			int dlicense=cm.getDlicense();
//			String cname=cm.getName();
//			String address=cm.getAddress();
//			int cellphone=cm.getCellphone();
//			JLabel accountReceipt=new JLabel("New Account Receipt");
//			accountReceipt.setFont(new Font("Segoe UI Light", Font.BOLD, 30));
//			JLabel name=new JLabel("Customer Name:   "+cname);
//			JLabel dlicenseL=new JLabel("Drive License Numver:   "+String.valueOf(dlicense));
//			JLabel addressL=new JLabel("Address:   "+address);
//			JLabel cellphoneL=new JLabel("cellphone number:   "+String.valueOf(cellphone));
//			contentPane.removeAll();
//			contentPane.setLayout(new GridLayout(5,1,5,5));
//			add(name);
//			add(dlicenseL);
//			add(addressL);
//			add(cellphoneL);
//			repaint();
//			validate();
//		}
//	}
//	private Date readDate() {
//		java.util.Date resultUtil;
//		java.sql.Date result = null;
//		try {
//			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
//			resultUtil = format1.parse(bufferedReader.readLine());
//			result=new java.sql.Date(resultUtil.getTime());
//		} catch (IOException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
@Override
public void actionPerformed(ActionEvent e) {
	    switch (e.getActionCommand()){
            default:
            if (clerk.isSelected())
            	showMainMenu_clerk();
            	else {if(manager.isSelected())
            		showMainMenu_manager();
            	else showMainMenu_customer();}

            break;
            case "ok_function":
            	if((input.getText().equals("1")||input.getText().equals("2")||input.getText().equals("3")||input.getText().equals("4")))
            	if (clerk.isSelected())
				showMainMenu_Respond(Integer.parseInt(input.getText()),delegate,2);
			else {if(manager.isSelected())
				showMainMenu_Respond(Integer.parseInt(input.getText()),delegate,3);
			else showMainMenu_Respond(Integer.parseInt(input.getText()),delegate,1);}
            break;
			case "detailV": detailedV();
			break;
			case "back_to_num": check_for_Ava_helper();
			break;
			case "ok_check":vtname=aType.getText();
			                fromDateTime=aFromDT.getText();
			                toDateTime=aToDT.getText();
			                location=aLocation.getText();
			                city=aCity.getText();
			                check_for_Ava_helper();
			                break;
			case "ok_rid":
				ReturnModel returnModel=new ReturnModel(Integer.valueOf(ridField.getText()),RDTField.getText(),Integer.valueOf(odometerField.getText()),
						Integer.valueOf(fulltankField.getText()),Integer.valueOf(valueField.getText()));
				returnVehicle_helper(returnModel);
			break;
			case "ok_branch": dailyRentalReportBranch_Helper(ansB.getText(),ansBC.getText(),ansDay.getText());
			break;
			case "ok_branch_return": vBoxReport.removeAll();
			dailyReturnReportBranch_Helper(ansB.getText(),ansBC.getText(),ansDay.getText());
			break;
			case "ok_rental_all":dailyRentalReport_helper(day.getText());
			break;
			case "ok_return_all":dailyReturnReport_helper(day.getText());
			break;
			case "ok_reserve":reservationModel=new ReservationModel(new Random().nextInt(9999),aType.getText(),dlicenseField.getText(),fromDT.getText(),toDT.getText());
			makeReservation_helper(reservationModel);
			break;
			case "ok_check_to_res": makeReservation();
			break;
			case "conf_num":rentVehicle_helper();
			break;
			case "ok_rent_info":rentVehicle_helper_helper();
			break;
			case "new_cust":
				CustomerModel customer = new CustomerModel(Long.parseLong(cellphoneField_c.getText()), nameField_c.getText(), addressField_c.getText(), dlicenseField_c);
				createAccount_helper(customer);
		}
}

	private void dailyRentalReport_helper(String day) {
		ArrayList<BranchModel> bms=delegate.findAllBranch();
		setLayout(new FlowLayout());
//		int totalALL[];
//		int totalRev=0;
		int totalNum=0;
		for (BranchModel bm : bms){
			int count=dailyRentalReportBranch_Helper(bm.getLocation(),bm.getCity(),day);
//			totalRev+=totalALL[0];
			totalNum+=count;
		}
//		JLabel grandTotalRev=new JLabel("The grand totals revenue for the day is "+totalRev+"    ");
//		grandTotalRev.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 40));
//		contentPane.add(grandTotalRev);

		JLabel grandTotalNum=new JLabel("The grand totals number of rent for the day is "+totalNum);
		grandTotalNum.setFont(new java.awt.Font("Segoe UI Light",Font.BOLD, 40));
//		contentPane.add(grandTotalRev);
		contentPane.add(grandTotalNum);

		JPanel ReportPane=new JPanel();

		ReportPane.setSize(1200,90);
		vBoxReport.setSize(1200,9000);
		ScrollPane scrollPane=new ScrollPane();
		scrollPane.setSize(1300,900);
		scrollPane.setWheelScrollingEnabled(true);
		vBoxReport.repaint();

		scrollPane.add(ReportPane);
		ReportPane.add(vBoxReport);
		contentPane.add(scrollPane);
		contentPane.repaint();
	}
}
