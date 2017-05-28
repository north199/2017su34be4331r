////////////////////////////////////////////////////////////////////////////
//SEEKOFFERMANAGER CLASS - contains series of functions
//uses code from other classes
package assignment1carseek;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;

public class SeekOfferManager {

    ArrayList<CarOffer> CarOfferList = new ArrayList<CarOffer>();
    ArrayList<CarSeek> CarSeekList = new ArrayList<CarSeek>();
    DatabaseManager databaseManager;

    public SeekOfferManager() {
        try {
            databaseManager = new DatabaseManager();
        } catch (Exception e) {

        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //displays all carOffers
    public ResultSet DisplayAllCarOffer() {
        ResultSet newResultSet = databaseManager.displayAllCarOffer();

        return newResultSet;
    }

    ////////////////////////////////////////////////////////////////////////////
    //adds a carOffer
    public void addCarOffer(int carOfferID, Car car, JourneyDetails jDetails, Date sqlDate, String pickupTime) {

        int primaryKeyInserted = 0;
        primaryKeyInserted = databaseManager.insertCar(car.carID, car.licencePlate, car.userID, car.carBrand, car.carYear, car.carCapacity);
        System.out.println("GENERATED PRIMARY KEY = " + primaryKeyInserted);
        databaseManager.insertCarOffer(carOfferID, primaryKeyInserted, car.userID, sqlDate, pickupTime, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota());
        //databaseManager.insertCarOffer(carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), 1);
        //insertCarOffer(int carOfferID, int carID, int driverID, String time, String status, int startPostCode, int endPostCode, int carCapacity)
        //CarOfferList.add(new CarOffer(carOfferID, jDetails.getQuota(), jDetails.getPrice(), jDetails.getCurrentPostCode(), jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getTravelTime(), pickupTime, dropOffTime, null));

    }

    ////////////////////////////////////////////////////////////////////////////
    //edits carOffer
    public void editCarOffer(int carOfferID, Car car, JourneyDetails jDetails, Date sqlDate, String pickupTime) {

        //databaseManager.editCar(car.carID, car.getCarLicencePlate(), car.userID, car.carBrand, car.carYear, car.carCapacity);
        databaseManager.editCarOffer(carOfferID, car.carID, car.userID, sqlDate, pickupTime, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota());
    }

    ////////////////////////////////////////////////////////////////////////////
    //removes carOffer
    public void removeCarOffer(int carOfferID) {
        databaseManager.removeCarOffer(carOfferID);
    }

    ////////////////////////////////////////////////////////////////////////////
    //search carOffers list and returns resultset with specifications
    public ResultSet searchCarOffers(int columnNum, String value) {
        return databaseManager.searchByColumnOfCarOffer(columnNum, value);
    }

    ////////////////////////////////////////////////////////////////////////////
    //adds carSeek
    public void addCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, String startTime, int numberOfPeople, int startPostCode, int endPostCode) {

        databaseManager.insertCarSeek(carSeekID, riderId, sqlDateSeeking, startTime, numberOfPeople, startPostCode, endPostCode);
    }

    ////////////////////////////////////////////////////////////////////////////
    //removes carSeek
    public void removeCarSeek(int carSeekID) {
        databaseManager.removeCarSeek(carSeekID);
    }

    ////////////////////////////////////////////////////////////////////////////
    //displays all carSeeks
    public ResultSet DisplayAllCarSeek() {
        ResultSet newResultSet = databaseManager.displayAllCarSeek();
        /* try{
         while (newResultSet.next()) {
                String trip_seek_id = newResultSet.getString("trip_seek_id");
                String rider_id = newResultSet.getString("rider_id");
                System.out.println("trip_seek_id: " + trip_seek_id);
                System.out.println("rider_id: " + rider_id);
             }
        } catch (Exception e){
            
        }*/
        return newResultSet;
    }

    ////////////////////////////////////////////////////////////////////////////
    //edits carSeek
    public void editCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, String startTime, int numberOfPeople, int startPostCode, int endPostCode) {

        //databaseManager.editCar(car.carID, car.getCarLicencePlate(), car.userID, car.carBrand, car.carYear, car.carCapacity);
        databaseManager.editCarSeek(carSeekID, riderId, sqlDateSeeking, startTime, numberOfPeople, startPostCode, endPostCode);
    }

    ////////////////////////////////////////////////////////////////////////////
    //search carSeek list and returns resultset with specifications 
    public ResultSet searchCarSeeks(int columnNum, String value) {
        return databaseManager.searchByColumnOfCarSeek(columnNum, value);
    }

}
