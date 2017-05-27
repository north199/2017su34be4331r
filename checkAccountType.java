package suber.backend.member.login;

import java.sql.ResultSet;
import suber.backend.SuberDB;
import suber.backend.member.session.Session;

public class checkAccountType {

    checkAccountType() {
    }

    public static void main(String[] args) {
        checkAccountType("michael@suberadmin.com");
    }

    public static void checkAccountType(String email) {
        SuberDB db = new SuberDB();
        Session session = new Session();
        String checkStaff = "SELECT staff_id from staff_list s JOIN user_list u WHERE (s.staff_id = u.user_id) AND u.email = '" + email + "';";
        String checkDriver = "SELECT user_id from driver_list WHERE email = '" + email + "';";
        try {
            ResultSet results = db.executeQuery(checkStaff);
            while (results.next()) {
                int id = results.getInt(1);
                session.setAccountType("Staff");
            }
        } catch (Exception notStaff) {
            try {
                ResultSet results = db.executeQuery(checkDriver);
                while (results.next()) {
                    int id = results.getInt(1);
                    session.setAccountType("Driver");
                }
            }
            catch (Exception notDriver) {
                session.setAccountType("Rider");
            }
        }
    }

}
