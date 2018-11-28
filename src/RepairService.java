import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
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

public class RepairService {
	
	public String model, manufacturer, center_id;
	
	RepairService(String model, String manufacturer, String center_id)
	{
		this.model = model;
		this.manufacturer = manufacturer;
		this.center_id = center_id;
	}//constructor
	
	void schedule_repair()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int menu_choice;
		String part_name="";
		float total_cost=0,hrs_to_complete=0;
		int required_quantity=0;
		String specific_problem="", diagnostic="";
		String basic_services = "";
		float diagnostic_fee= 0;
		int remaining_quantity =0;
		int min_order_threshold, min_quantity_threshold = 0, current_quantity = 0;
		System.out.println("Center id inside repair: " + center_id + " "+model+" "+ manufacturer);
		do
		{
			System.out.println("Select which problem you are facing :");
			System.out.println("1. Engine Knock");
			System.out.println("2. Car drifts in a particular direction");
			System.out.println("3. Battery does not hold charge");
			System.out.println("4. Black/unclean exhaust");
			System.out.println("5. A/C-Heater not working");
			System.out.println("6. Headlamps/Tail lamps");
			System.out.println("7. Check engine light");
			System.out.println("8. Go back");
			System.out.println("Enter your choice:");
		    menu_choice = Integer.parseInt(buf.readLine());
			if(menu_choice>0 && menu_choice<8)
			{				
				String q = "SELECT specific_problem,diagnostic,diagnostic_fee,basic_service_name from Repair_manual where manual_id="+"'"+menu_choice+"'";
				ResultSet rs;
				ReadQueries obj = new ReadQueries();
				rs = obj.read_db(q);
				try {
					
				while (rs.next()) {
				    specific_problem = rs.getString("SPECIFIC_PROBLEM");
				    diagnostic = rs.getString("DIAGNOSTIC");
				    diagnostic_fee = rs.getFloat("DIAGNOSTIC_FEE");
				    basic_services = rs.getString("BASIC_SERVICE_NAME");
				}//while
				}catch(Throwable oops)
				{
					oops.printStackTrace();
				}
				close(rs);
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("----------------------------DIAGNOSTIC REPORT----------------------------");
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Problem faced : "+specific_problem);
				System.out.println("Diagnostic : "+diagnostic);
				System.out.println("Diagnostic fee : "+diagnostic_fee);
				System.out.println("Basic services needed : "+basic_services);
				
				String basic_serv[] = basic_services.split(", ");
				for(int i=0;i<basic_serv.length;i++)
					basic_serv[i] = basic_serv[i].trim();
				System.out.println("Basic service is : "+basic_serv[0]);
				ResultSet rs1;
				ReadQueries obj1 = new ReadQueries();
				String q1 = "SELECT part_name, part_quantity,hours_to_complete,total_cost from basic_services where UPPER(name) = '"+basic_serv[0].toUpperCase()+"' and model = '"+model+"' and manufacturer = '"+manufacturer+"'";
				rs1 = obj1.read_db(q1);
				
				try {
				while (rs1.next()) {
				    part_name = rs1.getString("part_name");
				    total_cost = rs1.getFloat("TOTAL_COST");
				    hrs_to_complete = rs1.getFloat("HOURS_TO_COMPLETE");
				    required_quantity = rs1.getInt("part_quantity");
				    System.out.println(part_name +" "+total_cost+" "+hrs_to_complete+" "+required_quantity);
				}//while
				}catch(Throwable oops)
				{
					oops.printStackTrace();
				}
				close(rs1);	
				
				String part_id = "";
				ResultSet rs3;
				ReadQueries obj3 = new ReadQueries();
				String q3 ="select part_id from parts where UPPER(name) = '"+part_name.toUpperCase()+"' and manufacturer = '"+manufacturer+"'";
				rs3 = obj3.read_db(q3);
				try
				{
				while(rs3.next())
				{
					part_id = rs3.getString("PART_ID");
				}//while
				}catch(Throwable oops)
				{
					oops.printStackTrace();
				}
				close(rs3);
				
				ResultSet rs2;
				System.out.println("Here!!!");
				ReadQueries obj2 = new ReadQueries();
				System.out.println("Center "+center_id + " Manf " +manufacturer + " Part "+part_id);
				String q2 = "SELECT CURRENT_QUANTITY, MINIMUM_ORDER_THRESHOLD, MINIMUM_QUANTITY_THRESHOLD FROM HAS_STOCKS WHERE CENTER_ID = '"+center_id+"' AND MANUFACTURER = '"+manufacturer+"' AND PART_ID = '"+part_id+"'";
				rs2 = obj2.read_db(q2);

				try {
				while (rs2.next()) {				   
				    current_quantity = rs2.getInt("current_quantity");
				    min_order_threshold = rs2.getInt("MINIMUM_ORDER_THRESHOLD");
				    min_quantity_threshold = rs2.getInt("MINIMUM_QUANTITY_THRESHOLD");
				    System.out.println(current_quantity+" "+min_order_threshold+" "+min_quantity_threshold);
				}//while
				}catch(Throwable oops)
				{
					oops.printStackTrace();
				}
				close(rs2);
				System.out.println("Here again!!");
				remaining_quantity = current_quantity - required_quantity;			
				if(remaining_quantity>min_quantity_threshold)
				{
					//mark the required quantity as "reserved" and save the quantity value in reserved_quantity of has_stocks
					//update the current quantity to remaining quantity
					//run scheduling scheduling starting from (current_date+1, preferred mechanic)
				}//if
				else
				{
					//check how many parts are there
					//if parts<min_order threshold, then order the min_order_threshold, otherwise order the parts
					//update the status as "ordered" and save the ordered_quantity
					//run scheduling starting from -> (expected delivery date +1,preferred mechanic)
				}//else
				//display the two dates after the scheduling algorithm runs
				//ask the customer to choose one date
				break;
			}//if
			else if(menu_choice==8)
				break;
			else
			{
				System.out.println("Invalid problem entered. Please try again!!");
			}
		}while(menu_choice!=8);
	}//method
	
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
