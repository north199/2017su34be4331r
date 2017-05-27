package suber.backend.member.staff;

import suber.backend.SuberDB;

public class RegisterCorporateEntity {

    public RegisterCorporateEntity() {
    }

    public void RegisterNewUser(String corporateCode, String name, String email, int addressNumber, String addressStreet, String addressSuburb, int addressPostcode) throws Exception {
        // Assumes input has been sanitized and validated
        // Check email first if it already exists!
        /**
         * INSERT INTO table (columns) VALUES (values);
         */
        
        SuberDB db = new SuberDB();
        String query = "INSERT INTO " + db.getDatabaseName() + ".corporate_entiy ";
        query += "(corporate_code, corporate_name, corporate_email, address_number, address_street, address_suburb, address_postcode ";
        query += "VALUES ('" + corporateCode + "', '" + name + "', " + email + ", '" + addressNumber + "', '" + addressStreet + "'";
        query += ", " + addressSuburb + ", '" + addressPostcode + "';";
        db.executeUpdate(query);
    }
}