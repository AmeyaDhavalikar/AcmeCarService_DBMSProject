import java.sql.*;
import java.io.*;

public class Manager {
	
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
		String q = "SELECT employee_id,name,phone_number,city,street,state,zipcode,email,salary,center_id from Managers where employee_id="+"'"+user_id+"'";
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
		    String works_in_center = rs.getString("CENTER_ID");
		    System.out.println("Employee id: " + emp_id);
		    System.out.println("Name: " + name);
		    System.out.println("Phone number: " + phone_number);
		    System.out.println("Address : " + street + ", "+ city + ", " + state + ", " + zipcode);
		    System.out.println("Email address : " + email);
		    System.out.println("Service Center :" + works_in_center);
		    System.out.println("Compensation :" + salary);
		    System.out.println("Salary frequency: Monthly" );
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
		    //float salary = rs.getFloat("SALARY");
		    //String works_in_center = rs.getString("CENTER_ID");
		    System.out.println("Customer id: " + customer_id);
		    System.out.println("Name: " + name);
		    System.out.println("Phone number: " + phone_number);
		    System.out.println("Address : " + street + ", "+ city + ", " + state + ", " + zipcode);
		    System.out.println("Email address : " + email);
		    //System.out.println("Service Center :" + works_in_center);
		    //System.out.println("Compensation :" + salary);
		    //System.out.println("Salary frequency: Monthly" );
		}//while
		}catch(Throwable oops)
		{
			oops.printStackTrace();
		}
		System.out.println("");

	}//view_customer_profile
	
	void add_new_employees()
	{}//add_new_employee method

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
	
}//Manager class
