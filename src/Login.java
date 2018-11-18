import java.sql.*;

public class Login {
	 static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	private String user_id, password;

	public void set_user_id(String uid)
	{
		this.user_id = uid;
	}
	
	public void set_password(String pwd)
	{
		this.password = pwd;
	}
	
	public String get_user_id()
	{
		return user_id;
	}
	
	public String get_password()
	{
		return password;
	}
	
	public String validate_login()
	{
		//write code to query mgr, recep, cust table to find mayching user id and pwd
		String role = "";
		String uid, pwd;
		uid = get_user_id();
		pwd = get_password();
		try
        {
	     Class.forName("oracle.jdbc.driver.OracleDriver");
        String user = "adhaval";	// For example, "jsmith"
        String passwd = "200263183";	// Your 9 digit student ID number or password
		 
		Connection conn, conn2 = null;
        Statement stmt1,stmt2 = null;
        //PreparedStatement p_st = null;
        ResultSet rs1,rs2 = null;
        try 
        {
        	if(uid.contains("@gmail.com"))
        	{
        	    
     			conn2 = DriverManager.getConnection(jdbcURL, user, passwd);
     			stmt2 = conn2.createStatement();
     			String q1 = "SELECT EMAIL FROM CUSTOMERS WHERE EMAIL = "+"'"+uid +"'"+ " AND PASSWORD = " + pwd;
            	rs2 = stmt2.executeQuery(q1);
           		while (rs2.next()) 
           		{
         			role = rs2.getString("EMAIL");
            	}//while
           		
        	}
        	else
        	{
        		conn = DriverManager.getConnection(jdbcURL, user, passwd);
         		stmt1 = conn.createStatement();
         		String q = "SELECT ROLE FROM EMPLOYEES WHERE EMPLOYEE_ID = "+uid + "AND PASSWORD = " + pwd;
         		rs1 = stmt1.executeQuery(q);
         		
         		
         			while (rs1.next()) {
         				role = rs1.getString("ROLE");
         			}//while
         			close(rs1);
                    close(stmt1);
                    close(conn);
         		
         	 
         		}//else      								
           } //try 
           finally {
               close(rs2);
               close(stmt2);
               close(conn2);
           }
       } catch(Throwable oops) {
           oops.printStackTrace();}
		
		return role;
		}//method validate_login
	
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
