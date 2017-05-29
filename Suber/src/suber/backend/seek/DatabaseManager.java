package assignment1carseek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
//import java.util.Date;
import java.sql.Date;

public class DatabaseManager {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DatabaseManager() throws Exception {
        //String url = "jdbc:mysql://216.172.184.208/ab66986_suber";
        //String username = "ab66986_suber";
        //String password = "(VCm*Bu6dl*3";

        try {

            // Class.forName("com.mysql.jdbc.Driver").newInstance();
            // System.out.println("Driver loaded!");
            //connect = DriverManager.getConnection(url, username, password);
            /* PreparedStatement statement = connect.prepareStatement("SELECT * from test");
            //PreparedStatement insertStatement 

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("firstname");
                String number = resultSet.getString("lastname");
                System.out.println("firstname: " + user);
                System.out.println("lastname: " + number);
            }*/
        } catch (Exception e) {
            throw e;
        } finally {
            //close();
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

    private Connection getConnection() {
        String url = "jdbc:mysql://216.172.184.208/ab66986_suber";
        String username = "ab66986_suber";
        String password = "(VCm*Bu6dl*3";

        Connection newConnection = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            System.out.println("Driver loaded!");

            newConnection = DriverManager.getConnection(url, username, password);

            //System.out.println("New Connection Made");

            return newConnection;
        } catch (Exception e) {

        }

        return null;

    }

    private void closeConnection(Connection oldConnection, ResultSet oldResultSet, Statement oldStatement) {
        try {
            if (oldResultSet != null) {
                oldResultSet.close();
            }
            if (oldStatement != null) {
                oldStatement.close();
            }
            if (oldConnection != null) {
                oldConnection.close();
                //System.out.println("Connection Closed");
            }
        } catch (Exception e) {

        }
    }

    public int insertCar(int carID, String licencePlate, int userID, String carBrand, int carYear, int carCapacity) {

        System.out.println("Start insertCar");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet rsPrimaryKeys = null;
        int primaryKeyInserted = 0;

        try {
            insertStatement = newConnection.prepareStatement("INSERT INTO car values(DEFAULT,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //insertStatement.setInt(1, carID);
             insertStatement.setString(1, licencePlate);
            insertStatement.setInt(2, userID);

            insertStatement.setString(3, carBrand);
            insertStatement.setDouble(4, carYear);
            insertStatement.setDouble(5, carCapacity);
            
            int numero = insertStatement.executeUpdate();
            rsPrimaryKeys = insertStatement.getGeneratedKeys();
                if (rsPrimaryKeys.next()){
                primaryKeyInserted=rsPrimaryKeys.getInt(1);
            }
            System.out.println("Finish insertCar");
            return primaryKeyInserted;

        } catch (Exception e) {
        }
        closeConnection(newConnection, rsPrimaryKeys, insertStatement);
        return 0;

    }

    public void insertCarOffer(int carOfferID, int carID, int driverID, Date sqlDate, Timestamp pickupTime, Timestamp dropOffTime, int startPostCode, int endPostCode, int carCapacity) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start CarOffer");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            insertStatement = newConnection.prepareStatement("INSERT INTO trip_offering values(0,?,?,?,?,?,?,?,?)");
            //insertStatement.setInt(1, null);
            insertStatement.setInt(1, carID);
            insertStatement.setInt(2, driverID);
            insertStatement.setDate(3, sqlDate);
            insertStatement.setTimestamp(4, dropOffTime);
            insertStatement.setTimestamp(5, pickupTime);
            insertStatement.setDouble(6, startPostCode);
            insertStatement.setDouble(7, endPostCode);
            insertStatement.setInt(8, carCapacity);
            // insertStatement.
            

            insertStatement.executeUpdate();
            System.out.println("Finish CarOffer");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }

    public ResultSet displayAllCarOffer() {

        System.out.println("Start displayAllCarOffer");

        Connection newConnection = getConnection();
        PreparedStatement selectStatement = null;
        ResultSet newResultSet = null;

        try {
           // selectStatement = newConnection.prepareStatement("Select * from trip_offering");
           selectStatement = newConnection.prepareStatement("Select * from trip_offering inner join car on trip_offering.car_id = car.car_id ");

            newResultSet = selectStatement.executeQuery();

            System.out.println("Finish displayAllCarOffer");
            //closeConnection(newConnection, newResultSet, selectStatement);
            return newResultSet;

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, selectStatement);
        return null;

    }

    public void editCar(int carID, String licencePlate,int userID, String carBrand, int carYear, int carCapacity) {

        System.out.println("Start editCar");

        Connection newConnection = getConnection();
        PreparedStatement updateStatement = null;
        ResultSet newResultSet = null;

        try {
            updateStatement = newConnection.prepareStatement("UPDATE car SET licence_plate = ?, user_id = ?, car_brand = ?, car_year = ?, car_capacity = ? WHERE car_id = ?");
            //updateStatement.setInt(1, carID);
             updateStatement.setString(1, licencePlate);
            updateStatement.setInt(2, userID);
            
            updateStatement.setString(3, carBrand);
            updateStatement.setDouble(4, carYear);
            updateStatement.setDouble(5, carCapacity);

            updateStatement.setInt(5, carID);

            updateStatement.executeUpdate();
            System.out.println("Finish editCar");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, updateStatement);

    }

    public void editCarOffer(int carOfferID, int carID, int driverID, Date sqlDate, Timestamp pickupTime, Timestamp dropOffTime, int startPostCode, int endPostCode, int carCapacity){
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start editCarOffer");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            insertStatement = newConnection.prepareStatement("UPDATE trip_offering SET car_id = ?, driver_id = ?, offer_date = ?, start_time = ?, end_time = ?, start_postcode = ?, end_postcode = ?, number_of_spaces = ?   WHERE trip_offer_id = ?");
            //insertStatement.setInt(1, carOfferID);
            insertStatement.setInt(1, carID);
            insertStatement.setInt(2, driverID);
            //riderID
            insertStatement.setDate(3, sqlDate);
            insertStatement.setTimestamp(4, pickupTime);
            //insertStatement.setString(4, time);
            insertStatement.setTimestamp(5, dropOffTime);
            insertStatement.setDouble(6, startPostCode);
            insertStatement.setDouble(7, endPostCode);
            insertStatement.setInt(8, carCapacity);
            insertStatement.setInt(9, carOfferID);
            // insertStatement.

            insertStatement.executeUpdate();
            System.out.println("Finish editCarOffer");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }

    public void removeCarOffer(int carOfferID) {

        System.out.println("Start removeCarOffer");

        Connection newConnection = getConnection();
        PreparedStatement deleteStatement = null;
        ResultSet newResultSet = null;

        try {
            deleteStatement = newConnection.prepareStatement("delete from trip_offering where trip_offer_id = ?");
            deleteStatement.setInt(1, carOfferID);
            deleteStatement.executeUpdate();

            System.out.println("Finish removeCarOffer");
            closeConnection(newConnection, newResultSet, deleteStatement);

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, deleteStatement);

    }

    public ResultSet searchByColumnOfCarOffer(int columnNum, String value) {

        System.out.println("Start searchByColumnOfCarOffer");
        //String SQLquery = "";
        
        String SQLquery = "Select * from trip_offering inner join car on trip_offering.car_id = car.car_id ";
        //String SQLquery = "Select * from trip_offering ";
        if (columnNum == 1) {
            //SQLquery = "Select * from trip_offering";
            SQLquery = SQLquery + "where car_capacity = '" + value +"'";
            //SQLquery = "Select * from ab66986_suber.trip_offering inner join ab66986_suber.car on trip_offering.car_id = car.car_id where  car_capacity = '4'";
        } else if (columnNum == 2) {
           // SQLquery = SQLquery + "where car_brand LIKE '%" + value + "%'";
            SQLquery = SQLquery + "where car_brand ='" + value + "'";
        } else if (columnNum == 3) {
            SQLquery = SQLquery + "where start_postcode	='" + value + "'";
        } else if (columnNum == 4){
            SQLquery = SQLquery + "where end_postcode ='" + value + "'";
        } else if (columnNum == 5){
            SQLquery = SQLquery + "where offer_date ='" + value + "'";
        }

        Connection newConnection = getConnection();
        PreparedStatement selectStatement = null;
        ResultSet newResultSet = null;

        try {
            selectStatement = newConnection.prepareStatement(SQLquery);

            newResultSet = selectStatement.executeQuery();

            System.out.println("Finish searchByColumnOfCarOffer");
            //closeConnection(newConnection, newResultSet, selectStatement);
            return newResultSet;

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, selectStatement);
        return null;

    }
    
    
     public void insertCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, Timestamp startTime, Timestamp endTime, int numberOfPeople, int startPostCode, int endPostCode) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start insertCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            insertStatement = newConnection.prepareStatement("INSERT INTO trip_seeking values(0,?,?,?,?,?,?,?)");
            //insertStatement.setInt(1, null);
            //insertStatement.setInt(1, carSeekID);
            insertStatement.setInt(1, riderId);
            insertStatement.setDate(2, sqlDateSeeking);
            insertStatement.setTimestamp(3, startTime);
            insertStatement.setTimestamp(4, endTime);
             insertStatement.setDouble(5, numberOfPeople);
            insertStatement.setDouble(6, startPostCode);
            insertStatement.setDouble(7, endPostCode);
           
            // insertStatement.
            

            insertStatement.executeUpdate();
            System.out.println("Finish insertCarSeek");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }
     
    
    public void removeCarSeek(int carSeekID) {

        System.out.println("Start removeCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement deleteStatement = null;
        ResultSet newResultSet = null;

        try {
            deleteStatement = newConnection.prepareStatement("delete from trip_seeking where trip_seek_id = ?");
            deleteStatement.setInt(1, carSeekID);
            deleteStatement.executeUpdate();

            System.out.println("Finish removeCarSeek");
            closeConnection(newConnection, newResultSet, deleteStatement);

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, deleteStatement);

    }
    
    public ResultSet displayAllCarSeek() {

        System.out.println("Start displayAllCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement selectStatement = null;
        ResultSet newResultSet = null;

        try {
            selectStatement = newConnection.prepareStatement("Select * from trip_seeking");

            newResultSet = selectStatement.executeQuery();

            System.out.println("Finish displayAllCarSeek");
            //closeConnection(newConnection, newResultSet, selectStatement);
            return newResultSet;

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, selectStatement);
        return null;

    }
    
      public void editCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, Timestamp startTime, Timestamp endTime, int numberOfPeople, int startPostCode, int endPostCode) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start editCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            //insertStatement = newConnection.prepareStatement("INSERT INTO trip_seeking values(0,?,?,?,?,?,?,?)");
            insertStatement = newConnection.prepareStatement("UPDATE trip_seeking SET riderId = ?, sqlDateSeeking = ?, startTime = ?, endTime = ?, numberOfPeople = ?, startPostCode = ?, endPostCode = ? WHERE carSeekID = ?");
            //insertStatement.setInt(1, null);
            //insertStatement.setInt(1, carSeekID);
            insertStatement.setInt(1, riderId);
            insertStatement.setDate(2, sqlDateSeeking);
            insertStatement.setTimestamp(3, startTime);
            insertStatement.setTimestamp(4, endTime);
             insertStatement.setDouble(5, numberOfPeople);
            insertStatement.setDouble(6, startPostCode);
            insertStatement.setDouble(7, endPostCode);
           
            // insertStatement.
            

            insertStatement.executeUpdate();
            System.out.println("Finish editCarSeek");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }
     


}
