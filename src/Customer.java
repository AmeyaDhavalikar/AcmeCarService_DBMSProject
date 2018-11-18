
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
import java.util.Scanner;
import java.sql.*;

public class Customer
{
	private String email;
	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	
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
			String user = "adhaval";	// For example, "jsmith"
			String passwd = "200263183";	// Your 9 digit student ID number or password
			
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
			    
			    System.out.println("--------------------------------------------------------------------------------");
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
				             String user = "adhaval";	// For example, "jsmith"
				             String passwd = "200263183";	// Your 9 digit student ID number or password
							 
							 Connection conn = null;
					         Statement stmt = null;
					         PreparedStatement p_st = null;
					         ResultSet rs = null;
					         try 
					         {
					          		conn = DriverManager.getConnection(jdbcURL, user, passwd);
					          		stmt = conn.createStatement();
//					          		rs = stmt.executeQuery("SELECT MAX(CUSTOMER_ID) AS C_ID FROM CUSTOMERS");
//				            		int customer_id=0;
//				            		while (rs.next()) {
//				            			customer_id = rs.getInt("C_ID");
//				            		}
//				            		customer_id = customer_id+1;
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
	
	void service()
	{}//service
	
	void invoices()
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
