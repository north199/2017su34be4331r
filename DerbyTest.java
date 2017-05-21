import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DerbyTest {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DerbyTest() throws Exception {
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            connect = DriverManager
                    .getConnection("jdbc:derby://localhost/dbname");
            PreparedStatement statement = connect
                    .prepareStatement("SELECT * from caroffer");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("ID");
                String number = resultSet.getString("Name");
                System.out.println("ID: " + user);
                System.out.println("Name: " + number);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        DerbyTest dao = new DerbyTest();
    }

}