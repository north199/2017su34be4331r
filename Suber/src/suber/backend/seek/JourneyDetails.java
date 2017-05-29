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
    public int quota;
    public double price; 
    public String currentPostCode; 
    public int startingPostCode; 
    public int destinationPostCode; 
    public String travelTime;
    
    public JourneyDetails(int quota, double price, String currentPostCode, int startingPostCode, int destinationPostCode, String travelTime){
        this.quota = quota;
        this.price = price;
        this.currentPostCode = currentPostCode;
        this.startingPostCode = startingPostCode;
        this.destinationPostCode = destinationPostCode;
        this.travelTime = travelTime;
    }

    public int getQuota() {
        return quota;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrentPostCode() {
        return currentPostCode;
    }

    public int getStartingPostCode() {
        return startingPostCode;
    }

    public int getDestinationPostCode() {
        return destinationPostCode;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCurrentPostCode(String currentPostCode) {
        this.currentPostCode = currentPostCode;
    }

    public void setStartingPostCode(int startingPostCode) {
        this.startingPostCode = startingPostCode;
    }

    public void setDestinationPostCode(int destinationPostCode) {
        this.destinationPostCode = destinationPostCode;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }


    
}
