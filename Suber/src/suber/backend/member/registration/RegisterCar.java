package suber.backend.member.registration;

// @author Harry Friedberg
import java.sql.ResultSet;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.RegisterUserSession;

/**
 * This class handles all registration
 *
 * @author Andrew
 */
public class RegisterCar extends RegisterUser {

    SuberDB db;
    RegisterUserSession session;

    public RegisterCar() {
        db = new SuberDB();
        session = Suber.registerSession;
    }

    //Runs a SQL Query to insert a new record into the user_list table
    public void RegisterCar(String licencePlate, String carBrand, int carYear, int carCapacity) throws Exception {
        // Assumes input has been sanitized and validated
        /**
         * INSERT INTO table (cols) VALUES (values);
         */
        String query = "INSERT INTO `" + db.getDatabaseName() + "`.`car` ";
        query += "(licence_plate, user_id, car_brand, car_year, car_capacity )";
        query += " VALUES ('" + licencePlate + "', '" + session.getUserId() + "', '" + carBrand + "', '" + carYear + "', '" + carCapacity + "');";
        try {
            db.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
