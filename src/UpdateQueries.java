import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateQueries {
	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	 public String user = "adhaval";
     public String passwd = "200263183";
    public  void update_db(String sql) {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		

    	    try (Connection conn = DriverManager.getConnection(jdbcURL, user, passwd); 
    	        Statement stmt = conn.createStatement();) {
    	      
    	      stmt.executeUpdate(sql);
    	      System.out.println("Database updated successfully ");
    	    } 
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	
    }
}
