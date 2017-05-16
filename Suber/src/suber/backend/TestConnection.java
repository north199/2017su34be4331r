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
 * This class tests the MySQL db connection
 * @author Andrew
 */
public class TestConnection {
    
    public static void main(String[] args) throws Exception {
        // Initialize server config
        SuberDB db = new SuberDB();
        boolean loggedIn = false;
        Scanner scanner = new Scanner(System.in);

        while (!loggedIn) {
            System.out.print("Enter your username: ");
            String user = scanner.next();
            System.out.print("Enter your password: ");
            String pass = scanner.next();
            String query = "SELECT * FROM ab66986_suber.test_login "
                    + "WHERE username = \"" + user + "\" "
                    + "AND password = \"" + pass +"\"";
            try {
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
        }
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

