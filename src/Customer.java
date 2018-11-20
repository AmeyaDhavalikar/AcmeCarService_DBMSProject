import java.util.ArrayList;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Customer
{
	private String email;
	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	
	 public String user = "adhaval";	// For example, "jsmith"
     public String passwd = "200263183";	// Your 9 digit student ID number or password
	
	Customer(String uid)
	{
		email = uid;
		
	}
	
	public int get_customer_id(String uid)
	{
		int customer_id =0;
		try
        {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = null;
			Statement stmt = null;
            ResultSet rs = null;
            try 
            {
            	conn = DriverManager.getConnection(jdbcURL, user, passwd);
            	stmt = conn.createStatement();
			    String q = "SELECT CUSTOMER_ID FROM CUSTOMERS WHERE EMAIL = "+"'"+uid +"'";
			    rs = stmt.executeQuery(q);
			    while (rs.next()) 
			    {
			    	customer_id = rs.getInt("CUSTOMER_ID");
			    }//while
            
        }finally {
            close(rs);
            close(stmt);
            close(conn);
        }
    } catch(Throwable oops) {
        oops.printStackTrace();}
		return customer_id;
	}//get customer id
	
	public void show()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int menu_choice;
		do
		{
			System.out.println("Welcome!!");
			System.out.println("1. Profile");
			System.out.println("2. Register Car");
			System.out.println("3. Service");
			System.out.println("4. Invoices");
			System.out.println("5. Logout");
			System.out.println("Enter your choice:");
		    menu_choice = Integer.parseInt(buf.readLine());
			switch(menu_choice)
			{
				case 1 : profile();
						 break;
				case 2 : register_car();
						 break;
				case 3 : service();
						 break;
				case 4 : invoices();
						 break;
				default:
				
			}//switch	
		}while(menu_choice!=5);
		
	}//show function
	
	void profile()throws IOException
	{
		int profile_choice;
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		do
		{
			System.out.println("1. View Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. Go Back");
			System.out.println("Enter your choice: ");
			profile_choice = Integer.parseInt(buf.readLine());
			switch(profile_choice)
			{
				case 1 : view_profile();
						 break;
				case 2 : update_profile();
						 break;
			}//switch
		}while(profile_choice!=3);
	}//profile method
	
	public void get_cars(int customer_id) {
		String q="select LICENCE_PLATE_NO, MANUFACTURER, MODEL, YEAR, DATE_OF_PURCHASE,DATE_OF_LAST_SERVICE,MILEAGE_OF_LAST_SERVICE,TYPE_OF_LAST_SERVICE,REPAIRED_PROBLEM from vehicles where customer_id="+customer_id;
		ResultSet rs;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
		try {
			while (rs.next()) {
				System.out.println("--------------------------------------------------------------------------------");
			    String licence_plate_no = rs.getString("LICENCE_PLATE_NO");
			    String make = rs.getString("MANUFACTURER");
			    String model = rs.getString("MODEL");
			    int year = rs.getInt("YEAR");
			    String datePurchase = rs.getDate("DATE_OF_PURCHASE").toString();
			    String mileage="";
			    String dateLastService="";
			    if (rs.getDate("DATE_OF_LAST_SERVICE")!=null) {
			    	dateLastService = rs.getDate("DATE_OF_LAST_SERVICE").toString();
			    }
			    if (rs.getDate("MILEAGE_OF_LAST_SERVICE")!=null) {
			    	mileage = rs.getDate("MILEAGE_OF_LAST_SERVICE").toString();
			    }
			    String typeOfService = rs.getString("TYPE_OF_LAST_SERVICE");
			    String repairedProblem = rs.getString("REPAIRED_PROBLEM");
			    //long zipcode = rs.getLong("DATE_OF_PURCHASE");
			    //String email = rs.getString("EMAIL");
			    System.out.println("Licence Plate no: " + licence_plate_no);
			    System.out.println("Make: " + make);
			    System.out.println("Model: " + model);
			    System.out.println("Year: " + year);
			    System.out.println("Date of purchase: "+datePurchase);
			    System.out.print("Latest Service info: ");
			    if(dateLastService!="") {
			    	System.out.print(mileage+" miles");
			    	if(typeOfService=="R") {
			    		System.out.print(repairedProblem+" on ");
			    	}
			    	else {
			    		System.out.print("Serivce "+ typeOfService+"on ");
			    	}
			    	System.out.println(dateLastService);
			    }
			    else {
			    	System.out.println("NULL");
			    }
			    
			    
			}//while
			}catch(Throwable oops)
			{
				oops.printStackTrace();
			}
			System.out.println("");
	}
	
	void view_profile()
	{
		String q = "SELECT customer_id,name,city,street,state,zipcode,email,phone_number from Customers where email="+"'"+email+"'";
		ResultSet rs;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
		int customer_id=0;
		try {
		while (rs.next()) {
		    customer_id = rs.getInt("CUSTOMER_ID");
		    String name = rs.getString("NAME");
		    long phone_number = rs.getLong("PHONE_NUMBER");
		    String city = rs.getString("CITY");
		    String street = rs.getString("STREET");
		    String state = rs.getString("STATE");
		    long zipcode = rs.getLong("ZIPCODE");
		    String email = rs.getString("EMAIL");
		    System.out.println("Customer id: " + customer_id);
		    System.out.println("Name: " + name);
		    System.out.println("Phone number: " + phone_number);
		    System.out.println("Address : " + street + ", "+ city + ", " + state + ", " + zipcode);
		    System.out.println("Email address : " + email);
		}//while
		get_cars(customer_id);
		}catch(Throwable oops)
		{
			oops.printStackTrace();
		}
		System.out.println("");
		
	}//view_profile

	
	void update_profile()
	{}
	
	void register_car() throws IOException
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			int menu_choice;
			
			do
			{
				System.out.println("\n 1. Register\n 2. Cancel");
				System.out.println("Enter your choice : ");
				menu_choice = Integer.parseInt(buf.readLine());
				switch(menu_choice)
				{
					case 1 :	
							System.out.println("Enter Licence plate:");
							String licencePlate = buf.readLine();
							System.out.println("Make:");
							String make = buf.readLine();
							System.out.println("Model:");
							String model = buf.readLine();
							System.out.println("Year:");
							String year = buf.readLine();
							System.out.println("Purchase date:");
							String purchaseDate = buf.readLine();
							int customer_id = get_customer_id(email);
							int hadResults=0;
				             try
				             {
						     Class.forName("oracle.jdbc.driver.OracleDriver");
				         						 
							 Connection conn = null;
					         Statement stmt = null;
					         PreparedStatement p_st = null;
					         ResultSet rs = null;
					         try 
					         {
					          		conn = DriverManager.getConnection(jdbcURL, user, passwd);
					          		stmt = conn.createStatement();
					          		String q = "INSERT INTO VEHICLES(CUSTOMER_ID, LICENCE_PLATE_NO, MANUFACTURER, MODEL, YEAR, DATE_OF_PURCHASE)"+
					          		            "VALUES (?,?,?,?,?,?)";
					          		p_st = conn.prepareStatement(q);
				            		p_st.setInt(1,customer_id);
				            		p_st.setString(2,licencePlate);
				            		p_st.setString(3, make.toUpperCase());
				            		p_st.setString(4, model);
				            		p_st.setInt(5, Integer.parseInt(year));
				            		LocalDate sqlDate = LocalDate.parse(purchaseDate, formatter);
				            		p_st.setDate(6, java.sql.Date.valueOf(sqlDate));
				            		hadResults = p_st.executeUpdate();
					            } //try 
					            finally {
					                close(rs);
					                close(stmt);
					                close(conn);
					            }
					        } catch(Throwable oops) {
					            oops.printStackTrace();}
				             if (hadResults==1) {
				            	 System.out.println("Car is successfully registered!!!!");
				             }
				             break;
					case 2 : break;
					default : System.out.println("Invalid choice entered. Please try again!");
				}//switch
			}while(menu_choice!=2);
			
	}//register_car
	
	public void service()throws IOException
	{
		int profile_choice;
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		do
		{
			System.out.println("1. View Service History");
			System.out.println("2. Schedule service");
			System.out.println("3. Reschedule service");
			System.out.println("4. Go back");
			System.out.println("Enter your choice: ");
			profile_choice = Integer.parseInt(buf.readLine());
			switch(profile_choice)
			{
				case 1 : view_service_history();
						 break;
				case 2 : schedule_service();
						 break;
				case 3 : reschedule_service();
						 break;
				case 4 : break;
				default : System.out.println("Invalid choice entered");
			}//switch
		}while(profile_choice!=4);
	}//service
	
	public void view_service_history()
	{}//view_service_history
	
	public void schedule_service()throws IOException
	{
		int cnt =0;
		int profile_choice;
		String model = null, manufacturer = null;		
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("To begin scheduling, enter your car details as follows :");
		System.out.println("---------------------------------------------------------");
		do
		{
			System.out.println("Enter license plate no : ");		
			String license_plate_no = buf.readLine();
			Connection conn =null;
			Statement stmm = null;
			ResultSet r2 = null;
			try
			{
				conn = DriverManager.getConnection(jdbcURL, user, passwd);
				stmm = conn.createStatement();
				int customer_id = get_customer_id(email);   		
				String query = "SELECT MODEL, MANUFACTURER FROM VEHICLES WHERE LICENCE_PLATE_NO = '"+license_plate_no+"' AND CUSTOMER_ID = '"+customer_id+"'"; 
				r2 = stmm.executeQuery(query);
				while(r2.next())
				{
					model = r2.getString("MODEL");
					manufacturer = r2.getString("MANUFACTURER");
				}//while	
				close(r2);
				close(conn);
				close(stmm);
			}catch(Throwable oops)
			{
				oops.printStackTrace();
			}
			if(model==null && manufacturer ==null)
				System.out.println("The license plate number entered is incorrect. Please try again!");
		}while(model==null && manufacturer ==null);
		System.out.println("Enter current mileage : ");
		int current_mileage = Integer.parseInt(buf.readLine());
		System.out.println("Do you want a specific mechanic? Enter Y/y or N/n");
		String m_choice = buf.readLine();
		if(m_choice.equalsIgnoreCase("y"))
		{
			Connection con =null;
			Statement stm = null;
			ResultSet r1 = null;
	    	try
	    	{
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(jdbcURL, user, passwd);
          	stm = con.createStatement();
          	String center_id = "";
          	String query = "SELECT CENTER_ID FROM CUSTOMERS WHERE EMAIL = '"+email+"'"; 
          	r1 = stm.executeQuery(query);
          	while(r1.next())
          	{
          		center_id = r1.getString("CENTER_ID");
          	}//while	
          	close(r1);
          	close(con);
          	close(stm);
			String q = "SELECT name from Employees where role = 'Mechanic' and center_id ='"+center_id+"'";
			ResultSet rs;
			ReadQueries obj = new ReadQueries();
			rs = obj.read_db(q);
			int count=0;
			while (rs.next()) {
				count++;
				System.out.println(count +". " +rs.getString("NAME"));
			 	}//while
	    	}catch(Throwable oops)
			{
				oops.printStackTrace();
			}
			System.out.println("Enter mechanic's name :");
			String performing_mechanic = buf.readLine();
		}//if
		do
		{
			System.out.println("1. Schedule Maintenance Service");
			System.out.println("2. Schedule Repair service");
			System.out.println("3. Go back");			
			System.out.println("Enter your choice: ");
			profile_choice = Integer.parseInt(buf.readLine());
			switch(profile_choice)
			{
				case 1 : schedule_maintenance();
						 break;
				case 2 : schedule_repair(model, manufacturer);
						 break;
				case 3 : break;			
				default : System.out.println("Invalid choice entered");
			}//switch
		}while(profile_choice!=3);
	}//schedule service
	
	public void schedule_maintenance()
	{}
	
	public void schedule_repair(String model, String manufacturer)throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int menu_choice;
		String basic_services_needed;
		do
		{
			System.out.println("Select which problem you are facing :");
			System.out.println("1. Engine Knock");
			System.out.println("2. Car drifts in a particular direction");
			System.out.println("3. Battery does not hold charge");
			System.out.println("4. Black/unclean exhaust");
			System.out.println("5. A/C-Heater not working");
			System.out.println("6. Headlamps/Tail lamps");
			System.out.println("7. Check engine light");
			System.out.println("8. Go back");
			System.out.println("Enter your choice:");
		    menu_choice = Integer.parseInt(buf.readLine());
			if(menu_choice>0 && menu_choice<8)
			{
				basic_services_needed = diagnostic_report(menu_choice,model,manufacturer);
				break;
			}//if
			else if(menu_choice==8)
				break;
			else
			{
				System.out.println("Invalid problem entered. Please try again!!");
			}
		}while(menu_choice!=8);
	}//schedule_repair 
	
	public String diagnostic_report(int prob_num, String model, String manufacturer)
	{
		String specific_problem="", diagnostic="";
		String basic_services = "";
		float diagnostic_fee= 0;
		String q = "SELECT specific_problem,diagnostic,diagnostic_fee,basic_service_name from Repair_manual where manual_id="+"'"+prob_num+"'";
		ResultSet rs;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
		int customer_id=0;
		try {
			int i=0;
		while (rs.next()) {
		    specific_problem = rs.getString("SPECIFIC_PROBLEM");
		    diagnostic = rs.getString("DIAGNOSTIC");
		    diagnostic_fee = rs.getFloat("DIAGNOSTIC_FEE");
		    basic_services = rs.getString("BASIC_SERVICE_NAME");
		}//while
		}catch(Throwable oops)
		{
			oops.printStackTrace();
		}
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("----------------------------DIAGNOSTIC REPORT----------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Problem faced : "+specific_problem);
		System.out.println("Diagnostic : "+diagnostic);
		System.out.println("Diagnostic fee : "+diagnostic_fee);
		System.out.println("Basic services needed : "+basic_services);
		return basic_services;
	}//diagnostic_report
	
	public void reschedule_service()
	{}//reschedule service
	
	
	public void invoices()
	{}//invoices
	
	static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}//Customer class
