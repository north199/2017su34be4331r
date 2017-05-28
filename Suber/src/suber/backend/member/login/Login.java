/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.member.login;

import java.sql.ResultSet;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.LoginSession;
import suber.backend.security.Crypto;

/**
 *
 * @author Andrew
 */
public class Login {

    SuberDB db;
    LoginSession session;

    public Login() {
        db = new SuberDB();
        session = Suber.session;
    }

    /**
     * Attempts to log into database with provided credentials
     *
     * @param email Email as a string input
     * @param password Password as a string input
     * @return boolean depending on login success
     */
    public boolean tryLogin(String email, String password) throws Exception {
        email = email.toLowerCase();
        // If true check accounttype based on 
        System.out.println(email);
        String query = "SELECT `user_ID`, `email`, `password` FROM `" + db.getDatabaseName() + "`.`user_list` ";
        query += "WHERE `email` = '" + email + "' ";
        query += "AND `password` = '" + Crypto.encryptString(password) + "';";

        ResultSet results = db.executeQuery(query);
        while (results.next()) {
            String retrievedID = results.getString(1);
            String retrievedUser = results.getString(2);
            String retrievedPass = results.getString(3);
            System.out.println(retrievedID + " " + retrievedUser + " " + retrievedPass);
            if (retrievedUser.equalsIgnoreCase(email) && retrievedPass.equals(Crypto.encryptString(password))) {
                System.out.println("Successfully logged in!");
                session.setEmail(email);
                session.setUserId(retrievedID);
                checkAccountType(email);
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * This function checks the account type and sets it for the session
     *
     * @param email Input email as string
     */
    public void checkAccountType(String email) {
        String checkStaff = "SELECT staff_id FROM staff_list s JOIN user_list u ON s.staff_id = u.user_id WHERE u.email  = '" + email + "';";
        String checkDriver = "SELECT driver_id FROM driver_list d JOIN user_list u ON d.driver_id = u.user_id WHERE u.email = '" + email + "';";
        try {
            ResultSet results = db.executeQuery(checkStaff);
            while (results.next()) {
                int id = results.getInt(1);
                if ((id + "").length() > 0) {
                    session.setAccountType("Staff");
                    System.out.println(session.getAccountType());

                    return;
                }
            }
        } catch (Exception notStaff) {
            System.out.println(notStaff);
        }
        try {
            ResultSet results = db.executeQuery(checkDriver);
            while (results.next()) {
                int id = results.getInt(1);
                if ((id + "").length() > 0) {
                    session.setAccountType("Drive");
                    System.out.println(session.getAccountType());
                    return;
                }
            }
        } catch (Exception notDriver) {
            System.out.println(notDriver);
        }
        session.setAccountType("Ride");
        System.out.println(session.getAccountType());
    }

}
