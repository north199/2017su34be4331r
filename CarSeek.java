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
public class CarSeek {
    
    int carSeekID;
    Member ownerMember = null;
    String pickupTime;
    
    String startingPostCode;
    String destinationPostCode;
    String currentPostCode;
    int numOfPassengers = 0;
    
    public CarSeek(int carSeekID, Member ownerMember, String pickupTime, String destinationPostCode, String currentPostCode, int numOfPassengers){
        this.carSeekID = carSeekID;
        this.ownerMember = ownerMember;
        this.pickupTime = pickupTime;
        this.startingPostCode = startingPostCode;
        this.destinationPostCode = destinationPostCode;
        this.currentPostCode = currentPostCode;
        this.numOfPassengers = numOfPassengers;
        
    }
    
    public int getCarSeekID()
    {
        return carSeekID;
    }
    
    public void setCarSeekID(int carSeekID)
    {
        this.carSeekID = carSeekID;
    }
    
    public String getPickupTime()
    {
        return pickupTime;
    }
    
    public void setPickupTime(String pickupTime)
    {
        this.pickupTime = pickupTime;
    }
    
    
    public String getCurrentPostCode()
    {
        return currentPostCode;
    }
    
    public void setCurrentPostCode(String currentPostCode)
    {
        this.currentPostCode = currentPostCode;
    }
    
    public String getStartingPostCode()
    {
        return startingPostCode;
    }
    
    public void setStartingPostCode(String startingPostCode)
    {
        this.startingPostCode = startingPostCode;
    }
    
    public String getDestinationPostCode()
    {
        return destinationPostCode;
    }
    
    public void setDestinationPostCode(String destinationPostCode)
    {
        this.destinationPostCode = destinationPostCode;
    }
    
    public int getNumOfPassengers()
    {
        return numOfPassengers;
    }
    
    public void setNumOfPassengers(int numOfPassengers)
    {
        this.numOfPassengers = numOfPassengers;
    }
    
    
}
