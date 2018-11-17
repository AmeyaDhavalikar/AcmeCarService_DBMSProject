import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.Scanner;

public class Customer
{
	private int user_id;
	
	Customer(String uid)
	{
		user_id = Integer.parseInt(uid);
	}
	
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
	
	void view_profile()
	{
		String q = "SELECT customer_id,name,city,street,state,zipcode,email,phone_number from Customers where customer_id="+"'"+user_id+"'";
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
		
	}//view_profile

	void update_profile()
	{}
	
	void register_car()
	{}//register_car
	
	void service()
	{}//service
	
	void invoices()
	{}//invoices
	
}//Customer class
