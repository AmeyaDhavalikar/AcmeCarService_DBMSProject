import java.sql.*;

public class ReadQueries {

	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";

    public  ResultSet read_db(String q) {
    	 ResultSet rs = null;
        try {

       // Load the driver. This creates an instance of the driver
	   // and calls the registerDriver method to make Oracle Thin
	   // driver available to clients.

       Class.forName("oracle.jdbc.driver.OracleDriver");
            
       String user = "adhaval";	// For example, "jsmith"
       String passwd = "200263183";	// Your 9 digit student ID number or password

       Connection conn = null;
       Statement stmt = null;
      
       try {
                // Get a connection from the first driver in the
            	// DriverManager list that recognizes the URL jdbcURL

            	conn = DriverManager.getConnection(jdbcURL, user, passwd);

            	// Create a statement object that will be sending your
            	// SQL statements to the DBMS

            	stmt = conn.createStatement();
            	
            	rs = stmt.executeQuery(q);
            	//rs = stmt.executeQuery("SELECT CENTER_ID, ZIPCODE FROM CENTERS");
            	/*while (rs.next()) {
        		    String s = rs.getString("COF_NAME");
        		    float n = rs.getFloat("PRICE");
        		    System.out.println(s + "   " + n);
        		}*/

            	 
        } finally {
        //close(rs);
        //close(stmt);
       // close(conn);
        } } catch(Throwable oops) {
        oops.printStackTrace();
    }
      return rs;
}//read_db method

    static void close(Connection conn) {
    	if(conn != null) {
    		try { conn.close(); } catch(Throwable whatever) {}
    	}
    }//close connection

	static void close(Statement st) {
		if(st != null) {
			try { st.close(); } catch(Throwable whatever) {}
		}
	}//close statement

	static void close(ResultSet rs) {
		if(rs != null) {
			try { rs.close(); } catch(Throwable whatever) {}
		}
	}//close resultset
	
}//class
