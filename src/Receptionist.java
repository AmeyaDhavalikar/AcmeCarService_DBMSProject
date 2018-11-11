
public class Receptionist 
{
	
	private String user_id;
	
	Receptionist(String uid)
	{
		user_id = uid;
	}
	public void show(int emp_id)
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
	}

	void view_profile()
	{}
	
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
