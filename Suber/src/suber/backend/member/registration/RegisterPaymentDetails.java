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

    public void RegisterPaymentDetails(String accountName, String accountNumber, String bsbNumber, String cardName, String cardCVV, String cardExpiry) throws Exception {
        String query = "INSERT INTO payment_details VALUES (" + session.getUserId() + ", '" + Crypto.encryptString(accountName) + "', '" + Crypto.encryptString(accountNumber);
        query += "', " + Crypto.encryptString(bsbNumber) + "', '" + Crypto.encryptString(cardName) + "', '" + Crypto.encryptString(cardCVV) + "', '";
        query += Crypto.encryptString(cardExpiry) + "');";
        db.executeQuery(query);
    }
}