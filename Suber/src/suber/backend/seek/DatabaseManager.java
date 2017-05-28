////////////////////////////////////////////////////////////////////////////////
//DATABASE MANAGER Class - contains functions used in SQL database
//ignore testcode

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
    
//set up connection to database
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

    //closing the connection
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

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////FUNCTIONS///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////
    //insert Car object into database
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
            if (rsPrimaryKeys.next()) {
                primaryKeyInserted = rsPrimaryKeys.getInt(1);
            }
            System.out.println("Finish insertCar");
            return primaryKeyInserted;

        } catch (Exception e) {
        }
        closeConnection(newConnection, rsPrimaryKeys, insertStatement);
        return 0;

    }

    ////////////////////////////////////////////////////////////////////////////
    //insert carOffer into database
    public void insertCarOffer(int carOfferID, int carID, int driverID, Date sqlDate, String pickupTime, int startPostCode, int endPostCode, int carCapacity) {
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
            //insertStatement.setTimestamp(4, dropOffTime);
            insertStatement.setString(4, pickupTime);
            insertStatement.setDouble(5, startPostCode);
            insertStatement.setDouble(6, endPostCode);
            insertStatement.setInt(7, carCapacity);
            // insertStatement.

            insertStatement.executeUpdate();
            System.out.println("Finish CarOffer");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }

    ////////////////////////////////////////////////////////////////////////////
    //displays resultset showing all carOffers from the database
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
    ////////////////////////////////////////////////////////////////////////////
    //edit car details function
    public void editCar(int carID, String licencePlate, int userID, String carBrand, int carYear, int carCapacity) {

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

    ////////////////////////////////////////////////////////////////////////////
    //edit carOffer details function
    public void editCarOffer(int carOfferID, int carID, int driverID, Date sqlDate, String pickupTime, int startPostCode, int endPostCode, int carCapacity) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start editCarOffer");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            insertStatement = newConnection.prepareStatement("UPDATE trip_offering SET car_id = ?, driver_id = ?, offer_date = ?, start_time = ?, start_postcode = ?, end_postcode = ?, number_of_spaces = ?   WHERE trip_offer_id = ?");
            //insertStatement.setInt(1, carOfferID);
            insertStatement.setInt(1, carID);
            insertStatement.setInt(2, driverID);
            //riderID
            insertStatement.setDate(3, sqlDate);
            insertStatement.setString(4, pickupTime);
            //insertStatement.setString(4, time);
            //insertStatement.setTimestamp(5, dropOffTime);
            insertStatement.setDouble(5, startPostCode);
            insertStatement.setDouble(6, endPostCode);
            insertStatement.setInt(7, carCapacity);
            insertStatement.setInt(8, carOfferID);
            // insertStatement.

            insertStatement.executeUpdate();
            System.out.println("Finish editCarOffer");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }

    ////////////////////////////////////////////////////////////////////////////
    //remove carOffer function
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

    ////////////////////////////////////////////////////////////////////////////
    //Search carOffers using filters 
    public ResultSet searchByColumnOfCarOffer(int columnNum, String value) {

        System.out.println("Start searchByColumnOfCarOffer");
        //String SQLquery = "";

        String SQLquery = "Select * from trip_offering inner join car on trip_offering.car_id = car.car_id ";
        //String SQLquery = "Select * from trip_offering ";
        if (columnNum == 1) {
            //SQLquery = "Select * from trip_offering";
            SQLquery = SQLquery + "where car_capacity = '" + value + "'";
            //SQLquery = "Select * from ab66986_suber.trip_offering inner join ab66986_suber.car on trip_offering.car_id = car.car_id where  car_capacity = '4'";
        } else if (columnNum == 2) {
            // SQLquery = SQLquery + "where car_brand LIKE '%" + value + "%'";
            SQLquery = SQLquery + "where car_brand ='" + value + "'";
        } else if (columnNum == 3) {
            SQLquery = SQLquery + "where start_postcode	='" + value + "'";
        } else if (columnNum == 4) {
            SQLquery = SQLquery + "where end_postcode ='" + value + "'";
        } else if (columnNum == 5) {
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

    ////////////////////////////////////////////////////////////////////////////
    //insert carSeek to database 
    public void insertCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, String startTime, int numberOfPeople, int startPostCode, int endPostCode) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start insertCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            insertStatement = newConnection.prepareStatement("INSERT INTO trip_seeking values(0,?,?,?,?,?,?)");
            //insertStatement.setInt(1, null);
            //insertStatement.setInt(1, carSeekID);
            insertStatement.setInt(1, riderId);
            insertStatement.setDate(2, sqlDateSeeking);
            insertStatement.setString(3, startTime);
            //insertStatement.setTimestamp(4, endTime);
            insertStatement.setDouble(4, numberOfPeople);
            insertStatement.setDouble(5, startPostCode);
            insertStatement.setDouble(6, endPostCode);

            // insertStatement.
            insertStatement.executeUpdate();
            System.out.println("Finish insertCarSeek");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }
    
    public void insertCarSeek(int riderId, String sqlDateSeeking, String startTime, int numberOfPeople, int startPostCode, int endPostCode) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start insertCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            insertStatement = newConnection.prepareStatement("INSERT INTO ab66986_suber.trip_seeking values(0,?,?,?,?,?,?)");
            //insertStatement.setInt(1, null);
            //insertStatement.setInt(1, carSeekID);
            insertStatement.setInt(1, riderId);
            insertStatement.setString(2, sqlDateSeeking);
            insertStatement.setString(3, startTime);
            //insertStatement.setTimestamp(4, endTime);
            insertStatement.setDouble(4, numberOfPeople);
            insertStatement.setDouble(5, startPostCode);
            insertStatement.setDouble(6, endPostCode);

            // insertStatement.
            insertStatement.executeUpdate();
            System.out.println("Finish insertCarSeek");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }

    ////////////////////////////////////////////////////////////////////////////
    //remove carSeek from database 
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

    ////////////////////////////////////////////////////////////////////////////
    //displays resultset showing all carSeek from database
    public ResultSet displayAllCarSeek() {

        System.out.println("Start displayAllCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement selectStatement = null;
        ResultSet newResultSet = null;

        try {
            selectStatement = newConnection.prepareStatement("Select * from trip_seeking");

            newResultSet = selectStatement.executeQuery();
            /* while (newResultSet.next()) {
                String trip_seek_id = newResultSet.getString("trip_seek_id");
                String rider_id = newResultSet.getString("rider_id");
                System.out.println("trip_seek_id: " + trip_seek_id);
                System.out.println("rider_id: " + rider_id);
             }*/
            System.out.println("Finish displayAllCarSeek");
            //closeConnection(newConnection, newResultSet, selectStatement);
            return newResultSet;

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, selectStatement);
        return null;

    }

    ////////////////////////////////////////////////////////////////////////////
    //edit carSeek details function
    public void editCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, String startTime, int numberOfPeople, int startPostCode, int endPostCode) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start editCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            //insertStatement = newConnection.prepareStatement("INSERT INTO trip_seeking values(0,?,?,?,?,?,?,?)");
            insertStatement = newConnection.prepareStatement("UPDATE trip_seeking SET rider_id = ?, date_seeking = ?, start_time = ?, number_of_people = ?, start_postcode = ?, end_postcode = ? WHERE trip_seek_id = ?");
            //insertStatement.setInt(1, null);
            //insertStatement.setInt(1, carSeekID);
            insertStatement.setInt(1, riderId);
            insertStatement.setDate(2, sqlDateSeeking);
            insertStatement.setString(3, startTime);
            //insertStatement.setTimestamp(4, endTime);
            insertStatement.setDouble(4, numberOfPeople);
            insertStatement.setDouble(5, startPostCode);
            insertStatement.setDouble(6, endPostCode);
            insertStatement.setInt(7, carSeekID);
            insertStatement.executeUpdate();
            System.out.println("Finish editCarSeek");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    }

    ////////////////////////////////////////////////////////////////////////////
    //search carSeek using filters
    public ResultSet searchByColumnOfCarSeek(int columnNum, String value) {

        System.out.println("Start searchByColumnOfCarSeek");

        String SQLquery = "Select * from trip_seeking ";
        if (columnNum == 1) {
            SQLquery = SQLquery + "where number_of_people = '" + value + "'";
        } else if (columnNum == 2) {
            SQLquery = SQLquery + "where start_postcode	='" + value + "'";
        } else if (columnNum == 3) {
            SQLquery = SQLquery + "where end_postcode ='" + value + "'";
        } else if (columnNum == 4) {
            SQLquery = SQLquery + "where date_seeking ='" + value + "'";
        }

        Connection newConnection = getConnection();
        PreparedStatement selectStatement = null;
        ResultSet newResultSet = null;

        try {
            selectStatement = newConnection.prepareStatement(SQLquery);

            newResultSet = selectStatement.executeQuery();

            System.out.println("Finish searchByColumnOfCarSeek");
            //closeConnection(newConnection, newResultSet, selectStatement);
            return newResultSet;

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, selectStatement);
        return null;

    }

}
