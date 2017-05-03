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
                String firstName = results.getString(2);
                String lastName = results.getString(3);
                String email = results.getString(4);
                System.out.println("Firstname:" + firstName);
                System.out.println("Lastname:" + lastName);
                System.out.println("Email:" + email);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

