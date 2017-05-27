/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.member.registration;

import suber.backend.SuberDB;
import suber.backend.security.Crypto;
import suber.backend.member.session.Session;
import java.util.Date;

/**
 * This class handles all registration
 *
 * @author Andrew
 */
public class RegisterUser {

    SuberDB db = new SuberDB();
    Session session = new Session();
    public RegisterUser() {

    }

    //Runs a SQL Query to insert a new record into the user_list table
    public void RegisterNewUser(String firstName, String lastName, int homeStreetNum, String homeStreetAddress, String homeSuburb,
            int homePostCode, int workStreetNum, String workStreetAddress, String workSuburb, int workPostCode, String phoneNum, 
            String email, String password, String gender, Date dateOfBirth) throws Exception {
        // Assumes input has been sanitized and validated
        // Check email first if it already exists!
        /**
         * INSERT INTO table (cols) VALUES (values);
         */
        session.setEmail(email);
        String query = "INSERT INTO " + db.getDatabaseName() + ".user_list ";
        query += "(fname, lname, home_address_number, home_address_street, home_address_suburb, home_address_postcode, work_address_number, work_address_street, ";
        query += "work_address_suburb, work_address_postcode, phone_number, email, password) ";
        query += "VALUES ('" + firstName + "', '" + lastName + "', " + homeStreetNum + ", '" + homeStreetAddress + "', '" + homeSuburb + "'";
        query += ", " + homePostCode + ", " + workStreetNum +", '" + workStreetAddress +"', '" + workSuburb + "', " + workPostCode + ", ";
        query += phoneNum + "', '" + email + "', '" + Crypto.encryptString(password) + "', " + gender +"', '" + dateOfBirth + "');";
        db.executeUpdate(query);
        String returnID = "SELECT user_id FROM user_list WHERE email = '" + session.getEmail() +"';";
        int setSessionId = Integer.parseInt(returnID);
        session.setUserId(setSessionId);
    }
}
