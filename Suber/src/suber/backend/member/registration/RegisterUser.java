/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.member.registration;

import suber.backend.SuberDB;
import suber.backend.security.Crypto;

/**
 * This class handles all registration
 * @author Andrew
 */
public class RegisterUser {
    
    SuberDB db = new SuberDB();
    
    
    public RegisterUser() {
        
    }
    
    public void RegisterNewUser(String firstName, String lastName, int streetNum, String streetAddress, String suburb, 
            String postCode, String phoneNum, String email, String password) throws Exception {
        // Assumes input has been sanitized and validated
        // Check email first if it already exists!
        /**
         * INSERT INTO table (cols) VALUES (values);
         */
        String query = "INSERT INTO " + db.getDatabaseName() + ".user_list ";
        query += "(fname, lname, address_number, address_street, address_suburb, address_postcode, phone_number, ";
        query += "email, password, account_name, account_num, bsb_num, card_name, card_num, card_cvv, card_expiry) ";
        query += "VALUES (" + firstName + ", " + lastName + ", " + streetNum + ", " + streetAddress + ", " + suburb;
        query += ", " + postCode + ", " + phoneNum + ", " + email + ", " + Crypto.encryptString(password) + ");";
        db.executeUpdate(query);
    }
}
