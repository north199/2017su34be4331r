/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend;


import java.sql.*;

/**
 * 
 * @author Andrew
 */
public class SuberDB {
    ServerConfig config;
    Connection con;
    Statement stmt;
    
    public SuberDB() {
        config = new ServerConfig();
        try {
            // Import MySQL driver and attempt to connect to DB
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + config.getHost() + ":3306/" + config.getDB(), config.getUser(), config.getPass());
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet executeQuery(String query) {
        try {
            // Execute query
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
