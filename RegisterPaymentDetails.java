package suber.backend.member.registration;

import suber.backend.security.Crypto;

/**
 *
 * @author Harry
 */
public class RegisterPaymentDetails extends RegisterUser {

    RegisterPaymentDetails() {

    }

    RegisterPaymentDetails(String accountName, String accountNumber, String bsbNumber, String cardName, String cardCVV, String cardExpiry) throws Exception {
        {
            String query = "INSERT INTO payment_details VALUES (" + session.getUserId() + ", '" + Crypto.encryptString(accountName) + "', '" + Crypto.encryptString(accountNumber);
            query += "', " + Crypto.encryptString(bsbNumber) + "', '" + Crypto.encryptString(cardName) + "', '" + Crypto.encryptString(cardCVV) + "', '";
            query += Crypto.encryptString(cardExpiry) + "');";
            db.executeQuery(query);
        }
    }
}
