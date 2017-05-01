/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend;

import java.sql.*;

/**
 * This class tests the MySQL db connection
 * @author Andrew
 */
public class TestConnection {
    
    public static void main(String[] args) {
        // Initialize server config
        ServerConfig config = new ServerConfig();

        try {
            // Import MySQL driver and attempt to connect to DB
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://" + config.getHost() + ":3306/" + config.getDB(), config.getUser(), config.getPass());

            Statement stmt = con.createStatement();
            
            // Execute a test statement
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + config.getDB() + ".test;");
            // Output results
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

