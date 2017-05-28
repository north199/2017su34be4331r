/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend;

import java.sql.*;

/**
 * This class will provide easy access to the database of the app
 *
 * @author Andrew
 */
public class SuberDB {

    ServerConfig config;
    Connection con;
    Statement stmt;

    /**
     * Initialises MySQL db connection
     */
    public SuberDB() {
        try {
            config = new ServerConfig();
            // Import MySQL jdbc driver and attempt to connect to database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + config.getHost() + ":3306/" + config.getDB(), config.getUser(), config.getPass());
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Provides an easy function for executing MySQL queries
     *
     * @param query The MySQL query to execute
     * @return Returns results of query as ResultSet
     */
    public ResultSet executeQuery(String query) {
        try {
            // Execute query
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Provides an easy function for executing updating MySQL queries
     * 
     * @param query The MySQL query to execute
     */
    public void executeUpdate(String query) {
        try {
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        //closeConnection();
    }
    /**
     * Function to get current database
     *
     * @return The current database as a string
     */
    public String getDatabaseName() {
        return config.getDB();
    }

    /**
     * Terminates current database connection
     */
    public void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
