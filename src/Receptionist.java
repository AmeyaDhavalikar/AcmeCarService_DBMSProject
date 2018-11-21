import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class Receptionist 
{
	public String user = "adhaval";	// For example, "jsmith"
    public String passwd = "200263183";	// Your 9 digit student ID number or password
     
	private String user_id;
	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	Receptionist(String uid)
	{
		user_id = uid;
	}
	public void show()throws IOException
	{
		int menu_choice;
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		do
		{
			System.out.println("Welcome!!");
			System.out.println("1. Profile");
			System.out.println("2. View Customer Profile");
			System.out.println("3. Register Car");
			System.out.println("4. Service history");
			System.out.println("5. Schedule service");
			System.out.println("6. Reschedule service");
			System.out.println("7. Invoices");
			System.out.println("8. Daily task - update inventory");
			System.out.println("9. Daily task - record deliveries");
			System.out.println("10. Logout");
			System.out.println("Enter your choice: ");
			menu_choice = Integer.parseInt(buf.readLine());
			switch(menu_choice)
			{
				case 1 : profile();
						break;
				case 2 :
						break;
				case 3 : register_car();
						break;
				case 4 :
						break;
				case 5 :schedule_service();
						break;
				case 6 : 
						break;
				case 7 :
						break;
				case 8 : 
						break;
				case 9 :
						break;
				case 10 :
						break;
			    default :
			}//switch
		}while(menu_choice!=10);
	}//show method
	
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
			case 3 : 
			default : 
		}//switch
		}while(profile_choice!=3);
	}//profile method

	void view_profile()
	{
		String q = "SELECT employee_id,name,phone_number,city,street,state,zipcode,email,salary,start_date, role,center_id from EMPLOYEES where employee_id="+"'"+user_id+"'";
		ResultSet rs;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
		try {
		while (rs.next()) {
		    long emp_id = rs.getLong("EMPLOYEE_ID");
		    String name = rs.getString("NAME");
		    long phone_number = rs.getLong("PHONE_NUMBER");
		    String city = rs.getString("CITY");
		    String street = rs.getString("STREET");
		    String state = rs.getString("STATE");
		    long zipcode = rs.getLong("ZIPCODE");
		    String email = rs.getString("EMAIL");
		    float salary = rs.getFloat("SALARY");
		    String role = rs.getString("ROLE");
		    Date date = rs.getDate("START_DATE");
		    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		    String start_date = DATE_FORMAT.format(date);
		    String works_in_center = rs.getString("CENTER_ID");
		    System.out.println("Employee id: " + emp_id);
		    System.out.println("Name: " + name);
		    System.out.println("Address : " + street + ", "+ city + ", " + state + ", " + zipcode);
		    System.out.println("Email address : " + email);
		    System.out.println("Phone number : " + phone_number);
		    System.out.println("Role : " + role);
		    System.out.println("Start date : " + start_date);
		    System.out.println("Service Center : " + works_in_center);
		    System.out.println("Compensation : " + salary);
		    System.out.println("Salary frequency : Monthly" );
		}//while
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
						 update.update_db("update customers set name = "+"'"+name+"'"+" where email="+"'"+user_id+"'");
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
				         update.update_db("update customers set city = "+"'"+city+"'"+",state = "+"'"+state+"'"+",street = "+"'"+street+"'"+",zipcode = "+zipcode+" where email="+"'"+user_id+"'");
						 break;
				case 3 : System.out.println("Enter Phone number");
	 					 long phonenumber = Long.parseLong(buf.readLine());
	                     update.update_db("update customers set PHONE_NUMBER = "+phonenumber+" where email="+"'"+user_id+"'");
						 break;
				case 4 : System.out.println("Enter new password");
						 String password = buf.readLine();
				         update.update_db("update customers set PASSWORD = "+"'"+password+"'"+" where email="+"'"+user_id+"'");
					     break;
				case 5 : break;
				default : System.out.println("Invalid choice entered");
			}//switch
		}while(profile_choice!=5);
		
	 }
	
	public void view_customer_profile()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter email address of the customer");
		String email_address = buf.readLine();
		String q = "SELECT customer_id,name,city,street,state,zipcode,email,phone_number from Customers where customer_id="+"'"+email_address+"'";
		ResultSet rs;
		int customer_id=0;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
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
		Customer ob = new Customer(email_address);
		ob.get_cars(customer_id);
		}catch(Throwable oops)
		{
			oops.printStackTrace();
		}
		System.out.println("");

	}//view_customer_profile
	
	void register_customer_profile()
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
					case 1 :	System.out.println("Enter email address");
								String email = buf.readLine();
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
								int customer_id = new Customer(email).get_customer_id(email);
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
	
	void view_service_history()
	{}
	
	void schedule_service()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter email address of the custome for whom you want to schedule the service :");
		String email_address = buf.readLine();
		Customer obj = new Customer(email_address);
		obj.service();
	}
	
	void reschedule_service()
	{}
	
	void view_invoices()
	{}
	
	void update_inventory()
	{}
	
	void record_deliverables()
	{}
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
}//class
