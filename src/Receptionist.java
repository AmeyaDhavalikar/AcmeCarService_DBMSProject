import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.Scanner;
import java.io.*;

public class Receptionist 
{
	
	private String user_id;
	
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
				case 1 :
						break;
				case 2 :
						break;
				case 3 :
						break;
				case 4 :
						break;
				case 5 :
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
		String q = "SELECT employee_id,name,phone_number,city,street,state,zipcode,email,salary,center_id from Receptionists where employee_id="+"'"+user_id+"'";
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
	{}
	
	public void view_customer_profile()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter email address of the customer");
		String email_address = buf.readLine();
		String q = "SELECT customer_id,name,city,street,state,zipcode,email,phone_number from Customers where customer_id="+"'"+email_address+"'";
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
	
	void register_customer_profile()
	{}
	
	void register_car()
	{}
	
	void view_service_history()
	{}
	
	void schedule_service()
	{}
	
	void reschedule_service()
	{}
	
	void view_invoices()
	{}
	
	void update_inventory()
	{}
	
	void record_deliverables()
	{}
	
}//class
