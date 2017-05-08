/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1carseek;
import java.util.*;
/**
 *
 * @author adimt460a
 */
public class CarOffer {

    int carOfferID;
    int quota;
    double price;
    String currentPostCode;
    String startingPostCode;
    String destinationPostCode;
    String travelTime;
    Date pickupTime;
    Date dropOffTime;
    int numOfPassengers;
    String carType;

    public CarOffer(int carOfferID, int quota, double price, String currentPostCode, String startingPostCode, String destinationPostCode, String travelTime, Date pickupTime, Date dropOffTime, int numOfPassengers, String carType) {
        this.carOfferID = carOfferID;
        this.quota = quota;
        this.price = price;
        this.currentPostCode = currentPostCode;
        this.startingPostCode = startingPostCode;
        this.destinationPostCode = destinationPostCode;
        this.travelTime = travelTime;
        this.pickupTime = pickupTime;
        this.dropOffTime = dropOffTime;
        this.numOfPassengers = numOfPassengers;
        this.carType = carType;
    }
    
    public int getCarOfferID()
    {
        return carOfferID;
    }
    
    public void setCarOfferID(int carOfferID)
    {
        this.carOfferID = carOfferID;
    }
    
    
    public String getCurrentPostCode() {
        return currentPostCode;
    }

    public void setCurrentPostCode(String currentPostCode) {
        this.currentPostCode = currentPostCode;
    }

    public String getStartingPostCode() {
        return startingPostCode;
    }

    public void setStartingPostCode(String startingPostCode) {
        this.startingPostCode = startingPostCode;
    }

    public String getDestinationPostCode() {
        return destinationPostCode;
    }

    public void setDestinationPostCode(String destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Date getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(Date dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;

    }
    
        public int getNumOfPassengers()
    {
        return numOfPassengers;
    }
    
    public void setNumOfPassengers(int numOfPassengers)
    {
        this.numOfPassengers = numOfPassengers;
    }
    
    public String getCarType()
    {
        return carType;
    }
    
    public void setCarType(String carType)
    {
        this.carType = carType;
    }
}
