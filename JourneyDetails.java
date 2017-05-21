/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1carseek;

/**
 *
 * @author adimt460a
 */
public class JourneyDetails {
    public double quota;
    public double price; 
    public String currentPostCode; 
    public String startingPostCode; 
    public String destinationPostCode; 
    public String travelTime;
    
    public JourneyDetails(double quota, double price, String currentPostCode, String startingPostCode, String destinationPostCode, String travelTime){
        this.quota = quota;
        this.price = price;
        this.currentPostCode = currentPostCode;
        this.startingPostCode = startingPostCode;
        this.destinationPostCode = destinationPostCode;
        this.travelTime = travelTime;
    }

    public Double getQuota() {
        return quota;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrentPostCode() {
        return currentPostCode;
    }

    public String getStartingPostCode() {
        return startingPostCode;
    }

    public String getDestinationPostCode() {
        return destinationPostCode;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCurrentPostCode(String currentPostCode) {
        this.currentPostCode = currentPostCode;
    }

    public void setStartingPostCode(String startingPostCode) {
        this.startingPostCode = startingPostCode;
    }

    public void setDestinationPostCode(String destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }


    
}
