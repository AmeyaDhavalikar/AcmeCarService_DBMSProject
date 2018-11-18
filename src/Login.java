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
		 
		Connection conn = null;
        Statement stmt = null;
        //PreparedStatement p_st = null;
        ResultSet rs = null;
        try 
        {
         		conn = DriverManager.getConnection(jdbcURL, user, passwd);
         		stmt = conn.createStatement();
         		String q = "SELECT ROLE FROM EMPLOYEES WHERE EMPLOYEE_ID = "+uid + "AND PASSWORD = " + pwd;
         		rs = stmt.executeQuery(q);
       		while (rs.next()) {
       			role = rs.getString("ROLE");
       		} 
       								
           } //try 
           finally {
               close(rs);
               close(stmt);
               close(conn);
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
