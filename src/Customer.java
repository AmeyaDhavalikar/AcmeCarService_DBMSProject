
public class Customer
{
	private String user_id;
	
	Customer(String uid)
	{
		user_id = uid;
	}
	
	public void show()
	{
		System.out.println("Welcome!!");
		System.out.println("1. Profile");
		System.out.println("2. Register Car");
		System.out.println("3. Service");
		System.out.println("4. Invoices");
		System.out.println("5. Logout");
		System.out.println("Enter your choice:");
		
		
	}//show function
	
	void view_profile()
	{}
	
	void register_car()
	{}
	
	void service()
	{}
	
	void invoices()
	{}
	
	
}//Customer class
