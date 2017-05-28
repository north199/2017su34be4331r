////////////////////////////////////////////////////////////////////////////////
//CAROFFER CLASS - used for storing information for CarOffer////////////////////
//contains setters/getters

package assignment1carseek;

import java.util.Date;

public class CarOffer {

    int carOfferID;
    Double quota;
    Double price;
    String currentPostCode;
    String startingPostCode;
    String destinationPostCode;
    String travelTime;
    Date pickupTime;
    Date dropOffTime;
    Car car = null;

    public CarOffer(int carOfferID, Double quota, Double price, String currentPostCode, String startingPostCode, String destinationPostCode, String travelTime, Date pickupTime, Date dropOffTime, Car car) {
        this.carOfferID = carOfferID;
        this.quota = quota;

        this.price = price;
        this.currentPostCode = currentPostCode;
        this.startingPostCode = startingPostCode;
        this.destinationPostCode = destinationPostCode;
        this.travelTime = travelTime;
        this.pickupTime = pickupTime;
        this.dropOffTime = dropOffTime;
        this.car = car;
    }

    public int getCarOfferID() {
        return carOfferID;
    }

    public void setCarOfferID(int carOfferID) {
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

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;

    }
}
