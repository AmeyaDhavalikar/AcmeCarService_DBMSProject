import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.Date;
import java.time.*;

public class Manager {
	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	private String user_id;
	
	Manager(String uid)
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
			System.out.println("3. Add new employees");
			System.out.println("4. Payroll");
			System.out.println("5. Inventory");
			System.out.println("6. Orders");
			System.out.println("7. Notifications");
			System.out.println("8. New Car model");
			System.out.println("9. Car Service details");
			System.out.println("10. Service History");
			System.out.println("11. Invoices");
			System.out.println("12. Logout");
			System.out.println("Enter your choice: ");
			menu_choice = Integer.parseInt(buf.readLine());
			switch(menu_choice)
			{
				case 1 : profile();
						 break;
				case 2 : view_customer_profile();
						 break;
				case 3 : add_new_employees();
						 break;
				case 4 : payroll();
						 break;
				case 5 : view_inventory();
						 break;
				case 6 : orders();
						 break;
				case 7 : notifications();
						 break;
				case 8 : add_new_car();
						 break;
				case 9 : view_car_service_details();
						 break;
				case 10 : service_history();
						 break;
				case 11 : invoices();
						 break;
				case 12: System.out.println("Logging out...");
						 break;
				default: System.out.println("Invalid choice entered");
			}//switch
		}while(menu_choice!=12);
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
				case 3 : break;
				default : System.out.println("Invalid choice entered");
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
	
	void update_profile()
	{
		
	}//update_profile
	
	void view_customer_profile()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter email address of the customer");
		String email_address = buf.readLine();
		
		String q = "SELECT customer_id,name,city,street,state,zipcode,email,phone_number from Customers where email="+"'"+email_address+"'";
		ResultSet rs;
		ReadQueries obj = new ReadQueries();
		rs = obj.read_db(q);
		try {
		while (rs.next()) {
		    int customer_id = rs.getInt("CUSTOMER_ID");
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
		System.out.println("");

	}//view_customer_profile
	
	void add_new_employees()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int menu_choice,choice;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String role = "";
		String center_id = "";
    	String user = "adhaval";	// For example, "jsmith"
    	String passwd = "200263183";	
		do
		{
			System.out.println("\n 1. Add new employee\n 2. Go Back");
			System.out.println("Enter your choice : ");
			menu_choice = Integer.parseInt(buf.readLine());
			switch(menu_choice)
			{
				case 1 :		
						
						int employee_id=0;
						String password = "12345678";
						do {			
								System.out.println("Enter role :\n 1. Mechanic \n 2. Receptionist");
								choice = Integer.parseInt(buf.readLine());
								if(choice==1)
								{
									role = "Mechanic";		
									System.out.println("Enter name :");
									String name = buf.readLine();
									System.out.println("Address details :");
									System.out.println("Enter street :");
									String street = buf.readLine();
									System.out.println("Enter city :");
									String city = buf.readLine();
									System.out.println("Enter state :");
									String state = buf.readLine();
									System.out.println("Enter zipcode :");
									int zipcode = Integer.parseInt(buf.readLine());
									System.out.println("Enter email address : ");
									String email_id = buf.readLine();		
									System.out.println("Enter phone number :");
									long phone_number = Long.parseLong(buf.readLine());	
									System.out.println("Enter start date(in dd-MM-yyyy format) : ");
									String start_date = buf.readLine();
									System.out.println("Enter compensation (hourly_rate) :");
									float hourly_rate = Float.parseFloat(buf.readLine()); 
									 try
						             {			            	 
										 Class.forName("oracle.jdbc.driver.OracleDriver");
										 Connection conn=null,conn1 = null;
						            	 Statement stmt=null,stmt1 = null;
						            	 PreparedStatement p_st = null;
						            	 ResultSet rs = null,rs1 = null;
						            	 try 
						            	 {
							        	 	
							          		conn = DriverManager.getConnection(jdbcURL, user, passwd);
							          		stmt = conn.createStatement();
							          		rs = stmt.executeQuery("SELECT MAX(EMPLOYEE_ID) AS E_ID FROM EMPLOYEES");
						            		
						            		while (rs.next()) {
						            			employee_id = rs.getInt("E_ID");
						            		}
						            			employee_id = employee_id+1;
						            		conn1 = DriverManager.getConnection(jdbcURL, user, passwd);
								          	stmt1 = conn1.createStatement();
								          	String q1 = "SELECT CENTER_ID AS FROM EMPLOYEES WHERE EMPLOYEE_ID = '"+user_id+"'"; 
								          	rs1 = stmt1.executeQuery(q1);
								          	while(rs1.next())
								          	{
								          		center_id = rs1.getString("CENTER_ID");
								          	}	
								          	close(rs1);
								          	close(conn1);
								          	close(stmt1);
							          		String q = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, NAME, PHONE_NUMBER, CITY, STATE, STREET, ZIPCODE, EMAIL, PASSWORD, START_DATE, ROLE, HOURLY_RATE, CENTER_ID)"+
							          		            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
							          		p_st = conn.prepareStatement(q);
						            		p_st.setInt(1,employee_id);
						            		p_st.setString(2,name);
						            		p_st.setLong(3, phone_number);
						            		p_st.setString(4, city);
						            		p_st.setString(5, state);
						            		p_st.setString(6, street);
						            		p_st.setInt(7, zipcode);
						            		p_st.setString(8, email_id);
						            		p_st.setString(9, password);
						            		LocalDate sqlDate = LocalDate.parse(start_date, formatter);
						            		p_st.setDate(10, java.sql.Date.valueOf(sqlDate));
						            		p_st.setString(11, role);
						            		p_st.setFloat(12, hourly_rate);
						            		p_st.setString(13, center_id);
						            		p_st.executeUpdate();						
							            } //try 
							            finally {
							                close(rs);
							                close(stmt);
							                close(conn);
							            }
							        } catch(Throwable oops) {
							            oops.printStackTrace();}
						             System.out.println("Account successfully created!! \n New Employee ID is "+ employee_id+ "and password is " + password + " Please login again to proceed.");
						             break;
								}
								else if(choice == 2)
								{
									try
									{
									Class.forName("oracle.jdbc.driver.OracleDriver");
									Connection con =null, c = null;
									Statement stm = null, s = null;
									ResultSet r1 = null, r = null;
									con = DriverManager.getConnection(jdbcURL, user, passwd);
						          	stm = con.createStatement();
						          	String query = "SELECT CENTER_ID AS FROM EMPLOYEES WHERE EMPLOYEE_ID = '"+user_id+"'"; 
						          	r1 = stm.executeQuery(query);
						          	while(r1.next())
						          	{
						          		center_id = r1.getString("CENTER_ID");
						          	}//while	
						          	close(r1);
						          	close(con);
						          	close(stm);
									c = DriverManager.getConnection(jdbcURL, user, passwd);
					          		s = c.createStatement();
					          		String query1 = "SELECT EMAIL FROM EMPLOYEES WHERE CENTER_ID = '"+center_id+" ' AND ROLE = 'Receptionist'";
					          		r = s.executeQuery(query1);
				            		String em ="";
				            		while (r.next()) {
				            			em = r.getString("EMAIL");
				            		}//while
				            		close(r);
				            		close(s);
				            		close(c);
				            		if(em != null)
				            		{
				            			System.out.println("A receptionist already exists in this center. Cannot add employee with this role");
				            		}//if
				            		else
				            		{
				            			role = "Receptionist";			
				            			System.out.println("Enter name :");
										String name = buf.readLine();
										System.out.println("Address details :");
										System.out.println("Enter street :");
										String street = buf.readLine();
										System.out.println("Enter city :");
										String city = buf.readLine();
										System.out.println("Enter state :");
										String state = buf.readLine();
										System.out.println("Enter zipcode :");
										int zipcode = Integer.parseInt(buf.readLine());
										System.out.println("Enter email address : ");
										String email_id = buf.readLine();		
										System.out.println("Enter phone number :");
										long phone_number = Long.parseLong(buf.readLine());	
										System.out.println("Enter start date(in dd-MM-yyyy format) : ");
										String start_date = buf.readLine();
				            			System.out.println("Enter salary :");
				            			float salary = Float.parseFloat(buf.readLine()); 
									 try
						             {			            	 
						            	 Connection conn=null;
						            	 Statement stmt=null;
						            	 PreparedStatement p_st = null;
						            	 ResultSet rs = null;
						            	 try 
						            	 {
							        	 	conn = DriverManager.getConnection(jdbcURL, user, passwd);
							          		stmt = conn.createStatement();
							          		rs = stmt.executeQuery("SELECT MAX(EMPLOYEE_ID) AS E_ID FROM EMPLOYEES");
						            		
						            		while (rs.next()) {
						            			employee_id = rs.getInt("E_ID");
						            		}
						            			employee_id = employee_id+1;
						            		String q = "INSERT INTO EMPLOYEES(EMPLOYEE_ID, NAME, PHONE_NUMBER, CITY, STATE, STREET, ZIPCODE, EMAIL, PASSWORD, START_DATE, ROLE, SALARY, CENTER_ID)"+
							          		            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
							          		p_st = conn.prepareStatement(q);
						            		p_st.setInt(1,employee_id);
						            		p_st.setString(2,name);
						            		p_st.setLong(3, phone_number);
						            		p_st.setString(4, city);
						            		p_st.setString(5, state);
						            		p_st.setString(6, street);
						            		p_st.setInt(7, zipcode);
						            		p_st.setString(8, email_id);
						            		p_st.setString(9, password);
						            		LocalDate sqlDate = LocalDate.parse(start_date, formatter);
						            		p_st.setDate(10, java.sql.Date.valueOf(sqlDate));
						            		p_st.setString(11, role);
						            		p_st.setFloat(12, salary);
						            		p_st.setString(13, center_id);
						            		p_st.executeUpdate();						
							            } //try 
							            finally {
							                close(rs);
							                close(stmt);
							                close(conn);
							            }
							        } catch(Throwable oops) {
							            oops.printStackTrace();}
						             System.out.println("Account successfully created!! \n New Employee ID is "+ employee_id+ "and password is " + password + " Please login again to proceed.");
				            			break;
				            		}//else
									} catch(Throwable oops) {
							            oops.printStackTrace();}
								}//else if
								else 
									System.out.println("Invalid choice entered. Try again!");
						}while(choice!=1 && choice!=2);				              
			             break;
				case 2 : break;
				default : System.out.println("Invalid choice entered. Please try again!");
			}//switch
		}while(menu_choice!=2);	
	}//add_new_employee method

	void payroll()throws IOException
	{/*
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int menu_choice;
		
		do
		{
			System.out.println("1. View Payroll");
			System.out.println("2. Go Back");
			System.out.println("Enter your choice: ");
			menu_choice = Integer.parseInt(buf.readLine());
			switch(menu_choice)
			{
				case 1 : System.out.println("Enter employee id :");
				 		 String emp_id = buf.readLine();
				 		 String q1 = "SELECT NAME, SALARY FROM MANAGERS WHERE EMPLOYEE_ID = " + emp_id;
				 		 ResultSet rs;
				 		 ReadQueries obj = new ReadQueries();
				 		 rs = obj.read_db(q);
						try {
						while (rs.next()) {
						    int customer_id = rs.getInt("CUSTOMER_ID");
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
				 		 
				 		String q = "SELECT paycheck_date,paid_from,paid_to, from Customers where email="+"'"+email_address+"'";
						ResultSet rs;
						ReadQueries obj = new ReadQueries();
						rs = obj.read_db(q);
						try {
						while (rs.next()) {
						    int customer_id = rs.getInt("CUSTOMER_ID");
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
				 		 break;
				case 2 : break;
				default : System.out.println("Invalid choice entered");
			}//switch
		}while(menu_choice!=2); */
	}//payroll_method
	
	void view_inventory()
	{}//inventory method
	
	void orders()
	{}//orders method
	
	void notifications()
	{}//notifications
	
	void add_new_car()
	{}//add new car method
	
	void view_car_service_details()
	{}//view service history
	
	void service_history()
	{}//service_history
	
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
}//Manager class
