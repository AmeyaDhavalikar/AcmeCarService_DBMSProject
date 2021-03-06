import java.util.ArrayList;
import java.io.*;
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
	public String[] slot_mapping = {"","8am","8:30am","9am","9:30am","10am","10:30am","11am","11:30am","12pm","12:30pm","1pm","1:30pm","2pm","2:30pm","3pm","3:30pm","4pm","4:30pm","5pm","5:30pm","6pm","6:30pm",};
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
			    int mileage;
			    String dateLastService="";
			    if (rs.getDate("DATE_OF_LAST_SERVICE")!=null) {
			    	dateLastService = rs.getDate("DATE_OF_LAST_SERVICE").toString();
			    }
			    mileage = rs.getInt("MILEAGE_OF_LAST_SERVICE");
			    String typeOfService = rs.getString("TYPE_OF_LAST_SERVICE");
			    String repairedProblem = rs.getString("REPAIRED_PROBLEM");
			    System.out.println("Licence Plate no: " + licence_plate_no);
			    System.out.println("Make: " + make);
			    System.out.println("Model: " + model);
			    System.out.println("Year: " + year);
			    System.out.println("Date of purchase: "+datePurchase);
			    System.out.print("Latest Service info: ");
			    if(dateLastService!="") {
			    	System.out.print(mileage+" miles ");
			    	if(typeOfService=="R") {
			    		System.out.print(repairedProblem+" on ");
			    	}
			    	else {
			    		System.out.print("Service "+ typeOfService+" on ");
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

	
	void update_profile() throws NumberFormatException, IOException
	{
		int profile_choice;
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		do
		{
			System.out.println("1. Name");
			System.out.println("2. Address");
			System.out.println("3. Phone Number");
			System.out.println("4. Password");
			System.out.println("5. Go Back");
			System.out.println("Enter your choice: ");
			profile_choice = Integer.parseInt(buf.readLine());
			UpdateQueries update = new UpdateQueries();
			switch(profile_choice)
			{
				case 1 : System.out.println("Enter name");
						 String name = buf.readLine();
						 update.update_db("update customers set name = "+"'"+name+"'"+" where email="+"'"+email+"'");
						 break;
				case 2 : System.out.println("Address details :");
						 System.out.println("Enter street :");
				         String street = buf.readLine();
				         System.out.println("Enter city :");
				         String city = buf.readLine();
				         System.out.println("Enter state :");
				         String state = buf.readLine();
				         System.out.println("Enter zipcode :");
				         int zipcode = Integer.parseInt(buf.readLine());
				         update.update_db("update customers set city = "+"'"+city+"'"+",state = "+"'"+state+"'"+",street = "+"'"+street+"'"+",zipcode = "+zipcode+" where email="+"'"+email+"'");
						 break;
				case 3 : System.out.println("Enter Phone number");
	 					 long phonenumber = Long.parseLong(buf.readLine());
	                     update.update_db("update customers set PHONE_NUMBER = "+phonenumber+" where email="+"'"+email+"'");
						 break;
				case 4 : System.out.println("Enter new password");
						 String password = buf.readLine();
				         update.update_db("update customers set PASSWORD = "+"'"+password+"'"+" where email="+"'"+email+"'");
					     break;
				case 5 : break;
				default : System.out.println("Invalid choice entered");
			}//switch
		}while(profile_choice!=5);
		
	 }
		
		
	
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
	{
		int customer_id = get_customer_id(email);
		String q = "SELECT service_history_id,licence_plate_no,service_type,slot_number from service_history where customer_id = '"+customer_id+"'";
		ResultSet rs;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
		int i =0;
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
		}catch(Throwable oops)
		{
			oops.printStackTrace();
		}
		
	}//view_service_history
	
	public void schedule_service()throws IOException
	{
		int cnt =0;
		int profile_choice;
		String model = null, manufacturer = null;	
		String center_id = "";
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		Connection con =null;
		Statement stm = null;
		ResultSet r1 = null;
    	try
    	{
    	Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(jdbcURL, user, passwd);
      	stm = con.createStatement();
      	String query = "SELECT CENTER_ID FROM CUSTOMERS WHERE EMAIL = '"+email+"'"; 
      	r1 = stm.executeQuery(query);
      	while(r1.next())
      	{
      		center_id = r1.getString("CENTER_ID");
      	}//while	
      	close(r1);
      	close(con);
      	close(stm);
    	System.out.println("Are we getting center id here? "+ center_id);
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
				String q2 = "SELECT MODEL, MANUFACTURER FROM VEHICLES WHERE LICENCE_PLATE_NO = '"+license_plate_no+"' AND CUSTOMER_ID = '"+customer_id+"'"; 
				r2 = stmm.executeQuery(q2);
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
          
			String q = "SELECT name from Employees where role = 'Mechanic' and center_id ='"+center_id+"'";
			ResultSet rs;
			ReadQueries obj = new ReadQueries();
			rs = obj.read_db(q);
			int count=0;
			while (rs.next()) {
				count++;
				System.out.println(count +". " +rs.getString("NAME"));
			 	}//while
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
				case 1 : schedule_maintenance(licence_plate_no,model,manufacturer,center_id,current_mileage,performing_mechanic);
						 break;
				case 2 : schedule_repair(model, manufacturer,center_id);
						 break;
				case 3 : break;			
				default : System.out.println("Invalid choice entered");
			}//switch
		}while(profile_choice!=3);
    	}catch(Throwable oops)
    	{
    		oops.printStackTrace();
    	}
	}//schedule service
	
public void previous_service(String licence_plate,int current_mileage,String model,String manufacturer) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		LocalDate localDate = LocalDate.now();
		String date=dtf.format(localDate).toString();
		 
		String q="select service_type from service_history where service_date <='"+date+"' and LICENCE_PLATE_NO='"+licence_plate+"' and service_type='A|B|C' and ROWNUM = 1";
		ResultSet rs;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
		String service_type="";
		try {
		while (rs.next()) {
		    service_type = rs.getString("service_type");
		}
		//while
		}
		catch(Throwable oops)
		{
			oops.printStackTrace();
		}
		close(rs);
		int firsttime=0;
		if (service_type=="") {
			firsttime=1;
		}
		String query="select miles,service_type from MAINTENANCE_MANUAL where upper(model)='"+model.toUpperCase()+"' and upper(manufacturer)='"+manufacturer.toUpperCase()+"'";
		ResultSet rs1;;
		ReadQueries obj1 = new ReadQueries();
		rs = obj1.read_db(query);
		ArrayList<Integer>  miles= new ArrayList<Integer>();
		ArrayList<String> serviceTypes = new ArrayList<String>();
		String service_type1="";
		int previous_mileage=0;
		int max_mileage=0;
		int m=0;
		try {
			while(rs.next()) {
				m=m+rs.getInt("miles");
				miles.add(m);
				String s=rs.getString("service_type");
				serviceTypes.add(s);
				System.out.println(s);
				if (s.equals("C")) {			
					max_mileage=m;
				}
		}
		}
		catch(Throwable oops)
		{
			oops.printStackTrace();
		}
		close(rs);
		int presentrange=0;
		if(current_mileage >max_mileage) {
			presentrange=current_mileage%max_mileage;
		} 
		else {
			presentrange=current_mileage;
		}
		String presentServiceType="";
		System.out.println(presentrange);
		
		if(0<= presentrange & presentrange<= miles.get(0)) {
				presentServiceType="A";
		}
		else if(miles.get(0) < presentrange & presentrange<=miles.get(1)) {
					presentServiceType ="B";
		}
		else if(miles.get(1)< presentrange & presentrange<=miles.get(2)) {
					presentServiceType="C";
		}
		System.out.println("The service type is"+presentServiceType);
		return ;
		
	}
	



	public void schedule_maintenance()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int menu_choice;
		{			
			System.out.println("2. Find service date :");
			System.out.println("2. Go back");			
			System.out.println("Enter your choice: ");
			menu_choice = Integer.parseInt(buf.readLine());
			switch(menu_choice)
			{
				case 1 : previous_service(licence_plate_no,current_mileage,model,manufacturer);
						 break;
				case 2 : 
						 break;		
				default : System.out.println("Invalid choice entered");
			}//switch
		}while(menu_choice!=2);
	}
	
	public void schedule_repair(String model, String manufacturer, String center_id)throws IOException
	{
		RepairService obj = new RepairService(model,manufacturer,center_id);
		obj.schedule_repair();
		System.out.println("Scheduling your service!!......");
	}//schedule_repair 
	
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
