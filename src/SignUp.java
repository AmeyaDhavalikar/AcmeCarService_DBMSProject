import java.sql.*;
import java.io.*;

public class SignUp {
	 static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	void signup_customer()throws IOException
	{
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int menu_choice;
		System.out.println("Enter email address : ");
		String email_id = buf.readLine();
		System.out.println("Enter password :");
		String password = buf.readLine();
		System.out.println("Enter name :");
		String name = buf.readLine();
		System.out.println("Enter address :");
		System.out.println("Enter street :");
		String street = buf.readLine();
		System.out.println("Enter city :");
		String city = buf.readLine();
		System.out.println("Enter state :");
		String state = buf.readLine();
		System.out.println("Enter zipcode :");
		int zipcode = Integer.parseInt(buf.readLine());
		System.out.println("Enter phone number :");
		long phone_number = Long.parseLong(buf.readLine());
		String center_id = "";
		int service_center =0;
		do {
			System.out.println("Enter the service center where you want to service this car\n1. Downtown Auto Care\n2.Express Auto Shop");
			service_center = Integer.parseInt(buf.readLine());
			switch(service_center)
			{
			    case 1: center_id = "S0001";
			    		break;
			    case 2 : center_id = "S0002";
			    		break;
			    default : System.out.println("Wrong choice entered, please try again");
			}//switch
		}while(service_center > 2);	
		do
		{
			System.out.println("\n 1. Signup\n 2. Go Back");
			System.out.println("Enter your choice : ");
			menu_choice = Integer.parseInt(buf.readLine());
			switch(menu_choice)
			{
				case 1 :					    
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
				          		rs = stmt.executeQuery("SELECT MAX(CUSTOMER_ID) AS C_ID FROM CUSTOMERS");
			            		int customer_id=0;
			            		while (rs.next()) {
			            			customer_id = rs.getInt("C_ID");
			            		}
			            		customer_id = customer_id+1;
				          		String q = "INSERT INTO CUSTOMERS(CUSTOMER_ID, EMAIL, PASSWORD, NAME, PHONE_NUMBER, CITY, STATE, STREET, ZIPCODE, CENTER_ID)"+
				          		            "VALUES (?,?,?,?,?,?,?,?,?,?)";
				          		p_st = conn.prepareStatement(q);
			            		p_st.setInt(1,customer_id);
			            		p_st.setString(2,email_id);
			            		p_st.setString(3, password);
			            		p_st.setString(4, name);
			            		p_st.setLong(5, phone_number);
			            		p_st.setString(6, city);
			            		p_st.setString(7,state);
			            		p_st.setString(8, street);
			            		p_st.setInt(9, zipcode);
			            		p_st.setString(10, center_id);
			            		p_st.executeUpdate();						
				            } //try 
				            finally {
				                close(rs);
				                close(stmt);
				                close(conn);
				            }
				        } catch(Throwable oops) {
				            oops.printStackTrace();}
			             System.out.println("Account successfully created!! Please login again to proceed.");
			             break;
				case 2 : break;
				default : System.out.println("Invalid choice entered. Please try again!");
			}//switch
		}while(menu_choice!=2);
		
	}//method signup
	

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

}//class Signup
