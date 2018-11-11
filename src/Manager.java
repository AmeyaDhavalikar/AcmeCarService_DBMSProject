import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class Manager {
	
	private String user_id;
	
	Manager(String uid)
	{
		user_id = uid;
	}
	
	public void show()
	{
		int menu_choice;
		Scanner scan = new Scanner(System.in);
		
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
		menu_choice = scan.nextInt();
		switch(menu_choice)
		{
			case 1 : profile();
					break;
			case 2 : 
			default:
				
		
		}//switch
	}
	
	void profile()
	{
		int profile_choice;
		Scanner scan = new Scanner(System.in);
		System.out.println("1. View Profile");
		System.out.println("2. Update Profile");
		System.out.println("3. Go Back");
		System.out.println("Enter your choice: ");
		profile_choice = scan.nextInt();
		switch(profile_choice)
		{
			case 1 : view_profile();
		}//switch
	}//profile method
	
	void view_profile()
	{
		String q = "SELECT * from MANAGERS";
		ResultSet rs;
		ConnectionToDatabase obj = new ConnectionToDatabase();
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
	
	void view_customer_profile()
	{}//view_customer_profile
	
	void add_new_employees()
	{}//add_new_employee method

	void payroll()
	{}//payroll_method
	
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
