package suber.backend.member.registration;

import suber.Suber;
import suber.backend.member.session.LoginSession;
import suber.backend.security.Crypto;

/**
 *
 * @author Harry
 */
public class RegisterPaymentDetails extends RegisterUser {
    LoginSession session;
    
    public RegisterPaymentDetails() {
        session = Suber.session;
    }

    public void RegisterPaymentDetails(String accountName, String accountNumber, String bsbNumber, String cardName, String cardCVV, String cardExpiry, String cardNum) throws Exception {
        String query = "INSERT INTO `" + db.getDatabaseName() + "`.`payment_details` (user_id, account_name, account_num, bsb_num, card_name, card_num, card_cvv, card_expiry)";
        query += " VALUES ('" + session.getUserId() + "', '" + Crypto.encryptString(accountName) + "', '" + Crypto.encryptString(accountNumber);
        query += "', '" + Crypto.encryptString(bsbNumber) + "', '" + Crypto.encryptString(cardName) + "', '" + Crypto.encryptString(cardCVV) + "', '";
        query += Crypto.encryptString(cardExpiry) + "', '" + Crypto.encryptString(cardNum) + "');";
        try {
            db.executeUpdate(query);
            System.out.println("Yeah boy encrypted payment details inserted");
        } catch (Exception ex){ 
            System.out.println("failed to register payment deets brah");
        }
    }
}