package suber.backend.member.registration;

public class RegisterCorporateUser extends RegisterUser {

    public RegisterCorporateUser() {

    }
    //If the users indicates 
    public RegisterCorporateUser(String corporateCode) {
        String query = "UPDATE user_list set is_corporate = TRUE WHERE email = '" + session.getEmail() +"';";
        db.executeUpdate(query);
    }

}
