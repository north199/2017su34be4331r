/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suber.backend.seek;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.*;

/**
 *
 * @author adimt460a
 */
public class SeekOfferManager {

    ArrayList<CarOffer> CarOfferList = new ArrayList<CarOffer>();
    ArrayList<CarSeek> CarSeekList = new ArrayList<CarSeek>();
    DatabaseManager databaseManager;
    
    public SeekOfferManager()
    {
        try{
       databaseManager = new DatabaseManager();
    }catch (Exception e){
        
    }
    }

    
      public ResultSet DisplayAllCarOffer() {
        ResultSet  newResultSet = databaseManager.displayAllCarOffer();
         
       
        return newResultSet;
    }
    
    public void addCarOffer(int carOfferID, Car car, JourneyDetails jDetails, Date sqlDate,Timestamp pickupTime, Timestamp dropOffTime){
        
         int primaryKeyInserted = 0;
         primaryKeyInserted = databaseManager.insertCar(car.carID, car.licencePlate, car.userID, car.carBrand, car.carYear, car.carCapacity);
         System.out.println("GENERATED PRIMARY KEY = " + primaryKeyInserted);
         databaseManager.insertCarOffer(carOfferID, primaryKeyInserted, car.userID, sqlDate, pickupTime, dropOffTime, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota());
         //databaseManager.insertCarOffer(carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), 1);
          //insertCarOffer(int carOfferID, int carID, int driverID, String time, String status, int startPostCode, int endPostCode, int carCapacity)
        //CarOfferList.add(new CarOffer(carOfferID, jDetails.getQuota(), jDetails.getPrice(), jDetails.getCurrentPostCode(), jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getTravelTime(), pickupTime, dropOffTime, null));
        
    }
    
    public void editCarOffer(int carOfferID, Car car, JourneyDetails jDetails, Date sqlDate, Timestamp pickupTime, Timestamp dropOffTime){
        
         //databaseManager.editCar(car.carID, car.getCarLicencePlate(), car.userID, car.carBrand, car.carYear, car.carCapacity);
         databaseManager.editCarOffer(carOfferID, car.carID, car.userID, sqlDate, pickupTime, dropOffTime, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota());
        
        
    }
    
     public void removeCarOffer(int carOfferID) {
        databaseManager.removeCarOffer(carOfferID); 
    }
    
    
    

    public ResultSet searchCarOffers(int columnNum, String value) {
        return databaseManager.searchByColumnOfCarOffer(columnNum, value);
    }
    
    public void addCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, Timestamp startTime, Timestamp endTime, int numberOfPeople, int startPostCode, int endPostCode) {
        
        databaseManager.insertCarSeek(carSeekID, riderId, sqlDateSeeking, startTime, endTime, numberOfPeople, startPostCode, endPostCode);
    }
    
    public void removeCarSeek(int carSeekID)
    {
        databaseManager.removeCarSeek(carSeekID); 
    }
    
     public ResultSet DisplayAllCarSeek() {
        ResultSet  newResultSet = databaseManager.displayAllCarSeek();
         
       
        return newResultSet;
    }
     
       public void editCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, Timestamp startTime, Timestamp endTime, int numberOfPeople, int startPostCode, int endPostCode){
        
         //databaseManager.editCar(car.carID, car.getCarLicencePlate(), car.userID, car.carBrand, car.carYear, car.carCapacity);
         databaseManager.editCarSeek(carSeekID, riderId, sqlDateSeeking, startTime, endTime, numberOfPeople, startPostCode, endPostCode);
        
        
    }
    

}