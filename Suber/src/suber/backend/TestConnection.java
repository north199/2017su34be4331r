/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend;

import java.sql.*;
import java.util.Scanner;

/**
 * This class is used for testing the database server connection
 * @author Andrew
 */
public class TestConnection {
    
    public static void main(String[] args) throws Exception {
        // Initialize server config
        
            //String query2 = "SELECT * FROM " + db.getDatabaseName() + ".test_login;";
            //try {
            //    ResultSet resultSet = db.executeQuery(query2);
      
            //} catch (Exception ex) {
            //    System.out.println(ex.toString());
            //}
            
            /**String query = "SELECT * FROM ab66986_suber.test_login "
                    + "WHERE username = \"" + user + "\" "
                    + "AND password = \"" + pass +"\"";
            /**try {
                ResultSet results = db.executeQuery(query);
                results.next();
                String retrievedUser = results.getString(1);
                String retrievedPass = results.getString(2);
                if (retrievedUser.equals(user) && retrievedPass.equals(pass)) {
                    System.out.println("Successfully logged in!");
                    loggedIn = true;
                } else {
                    System.out.println("Login failed!");
                }
            } catch (Exception e) {
                System.out.println("Login failed!");
            }
        }**/
        /**
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
        }**/
    }
}

