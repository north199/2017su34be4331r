/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1carseek;

import java.util.Date;

/**
 *
 * @author adimt460a
 */
public class CarSeek {
    
    int carSeekID;
   
    Member ownerMember = null;
    Date pickupTime;
    
    String startingPostCode;
    String destinationPostCode;
    String currentPostCode;
    
    public CarSeek( int carSeekID, Member ownerMember, Date pickupTime, String destinationPostCode, String currentPostCode){
        this.carSeekID = carSeekID;
        this.ownerMember = ownerMember;
        this.pickupTime = pickupTime;
        this.startingPostCode = startingPostCode;
        this.destinationPostCode = destinationPostCode;
        this.currentPostCode = currentPostCode;
    }
    
    public int getCarSeekID()
    {
        return carSeekID;
    }
    
    public void setCarSeekID(int carSeekID)
    {
        this.carSeekID = carSeekID;
    }
    
    public Date getPickupTime()
    {
        return pickupTime;
    }
    
    public void setPickupTime(Date pickupTime)
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
    
    
    
}