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
        SuberDB db = new SuberDB();
        try {
            ResultSet results = db.executeQuery("SELECT * FROM test;");
            while (results.next()) {
                System.out.println(results.getInt(1) + "  " + results.getString(2) + "  " + results.getString(3) + "  " + results.getString(4));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

