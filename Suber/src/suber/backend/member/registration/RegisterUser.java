/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.member.registration;

import java.sql.ResultSet;
import java.util.Date;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.LoginSession;
import suber.backend.member.session.RegisterUserSession;

/**
 * This class handles all registration
 *
 * @author Andrew
 */
public class RegisterUser {

    SuberDB db;
    RegisterUserSession session;
    LoginSession loginSession;

    public RegisterUser() {
        db = new SuberDB();
        session = Suber.registerSession;
        loginSession = Suber.session;
    }
    
    /**
     * register new driver
     * @param userId 
     * @param accType
     */
    public void RegisterNewAccountType(int userId, String accType) {
        String query = "INSERT INTO `" + db.getDatabaseName() + "`.`";
        if (accType.equalsIgnoreCase("Ride")) {
            query += "rider_list` (rider_id)";
        } else if (accType.equalsIgnoreCase("Driver")) {
            query += "driver_list` (driver_id)";
        }
        query += " VALUES ('" + userId +"');";
        
        try {
            db.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("failed somehow to determine driver/rider type");
        }
    }
    
    /**
     * LOL CEEBS COMMENT THIS BUT IT PRETTY MUCH REGISTERS A NEW USER
     * @param firstName
     * @param lastName
     * @param homeStreetNum
     * @param homeStreetAddress
     * @param homeSuburb
     * @param homePostCode
     * @param workStreetNum
     * @param workStreetAddress
     * @param workSuburb
     * @param workPostCode
     * @param phoneNum
     * @param email
     * @param password
     * @param isCorporate
     * @param gender
     * @param dateOfBirth
     * @throws Exception 
     */
    public void RegisterNewUser(String firstName, String lastName, int homeStreetNum, String homeStreetAddress, String homeSuburb,
            int homePostCode, int workStreetNum, String workStreetAddress, String workSuburb, int workPostCode, String phoneNum,
            String email, String password, boolean isCorporate, String gender, String dateOfBirth) throws Exception {
        // Assumes input has been sanitized and validated
        /**
         * INSERT INTO table (cols) VALUES (values);
         */
        session.setEmail(email);
        String query = "INSERT INTO `" + db.getDatabaseName() + "`.`user_list` ";
        query += "(fname, lname, home_address_number, home_address_street, home_address_suburb, home_address_postcode, work_address_number, work_address_street, ";
        query += "work_address_suburb, work_address_postcode, phone_number, email, password, is_corporate, gender, date_of_birth) ";
        query += "VALUES ('" + firstName + "', '" + lastName + "', '" + homeStreetNum + "', '" + homeStreetAddress + "', '" + homeSuburb + "'";
        query += ", '" + homePostCode + "', '" + workStreetNum + "', '" + workStreetAddress + "', '" + workSuburb + "', '" + workPostCode + "', '";
        query += phoneNum + "', '" + email + "', '" + password + "', '" + (isCorporate ? 1 : 0) + "', '" + gender + "', '" + dateOfBirth + "');";

        try {
            db.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println("failed to insert new mem");
            System.out.println(ex);
        }
        
        try {

            String returnID = "SELECT `user_id` FROM " + db.getDatabaseName() + ".`user_list` WHERE `email` = '" + email + "';";

            ResultSet results = db.executeQuery(returnID);
            while(results.next()) {
                String sessionID = results.getString(1);
                session.setUserId(Integer.parseInt(sessionID));
                loginSession.setUserId("" + session.getUserId());
                System.out.println("success!! new user " + session.getUserId());
            }
        } catch (Exception ex) {
            System.out.println("failed to register user");
            System.out.println(ex);
        }
    }
}
