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
public class SeekOfferManager {

    ArrayList<CarOffer> CarOfferList = new ArrayList<CarOffer>();
    ArrayList<CarSeek> CarSeekList = new ArrayList<CarSeek>();
  

    public void LoadListFromDataBase() {
        //Dummy Variables for CarOfferList (Quota/Price/CurrentPC/StartingPC/DestinationPC/TravelTime/PickupTime/DropOffTime)
        CarOfferList.add(new CarOffer(1, 200.0, 100.0, "2033", "2033", "2056", "2", null, null));
        CarOfferList.add(new CarOffer(2, 300.0, 100.0, "2033", "2033", "2088", "2", null, null));
        CarOfferList.add(new CarOffer(3, 400.0, 100.0, "2044", "2044", "2056", "2", null, null));
        CarOfferList.add(new CarOffer(4, 500.0, 100.0, "2033", "2033", "2222", "2", null, null));
        CarOfferList.add(new CarOffer(5, 600.0, 100.0, "2055", "2055", "2088", "2", null, null));
        CarOfferList.add(new CarOffer(6, 700.0, 100.0, "2033", "2033", "2056", "2", null, null));
        //Dummy Variables for CarSeekList (Member/PickupTime/DestinationPC/CurrentPC)
        CarSeekList.add(new CarSeek(1, null, null, "2204", "2210"));
        CarSeekList.add(new CarSeek(2, null, null, "2056", "2215"));
        CarSeekList.add(new CarSeek(3, null, null, "2304", "2415"));
        CarSeekList.add(new CarSeek(4, null, null, "2222", "2232"));
        CarSeekList.add(new CarSeek(5, null, null, "2044", "2620"));

    }

    private void SaveListToDataBase() {

    }

    public  ArrayList<String> DisplayAllCarSeek() {
        ArrayList<String> list = new  ArrayList<String>();
        for (int i = 0; i < CarSeekList.size(); i++) {
            CarSeek carSeekTemp = CarSeekList.get(i);
            list.add(carSeekTemp.getCarSeekID() + ", " + carSeekTemp.getPickupTime() + ",  " + carSeekTemp.getDestinationPostCode() + ",  " + carSeekTemp.getCurrentPostCode());
            //System.out.println(carSeekTemp.getPickupTime() + carSeekTemp.getDestinationPostCode() + carSeekTemp.getCurrentPostCode());
        }
        return list;
    }

    public ArrayList<String> DisplayAllCarOffer() {
        ArrayList<String> list = new  ArrayList<String>();
        for (CarOffer i : CarOfferList) {
             list.add(i.getCarOfferID() + ", " + i.getQuota() + ", " + i.getPrice() + ", " + i.getCurrentPostCode() + ", " + i.getStartingPostCode() + ", " + i.getDestinationPostCode() + ", " + i.getTravelTime() + ", " + i.getPickupTime() + ", " + i.getDropOffTime());
            //System.out.println(i.getQuota() + i.getPrice() + i.getCurrentPostCode() + i.getStartingPostCode() + i.getDestinationPostCode() + i.getTravelTime() + i.getPickupTime() + i.getDropOffTime());
        }
        return list;
    }

    public ArrayList<String> findMatchByPostCode() {
        ArrayList<String> matchesFoundCarOffer = new ArrayList<String>();
       
        
        for (CarSeek x : CarSeekList) {
            String postCodeTemp = x.getDestinationPostCode();
            for (CarOffer y : CarOfferList) {
                if(postCodeTemp == y.getDestinationPostCode())
                {
                    matchesFoundCarOffer.add(y.getPrice() + ", " + y.getQuota() + ", " + y.getTravelTime() + ", " + y.getCurrentPostCode() + ", " + y.getStartingPostCode() + ", " + y.getDestinationPostCode());
                    //System.out.println(y.getPrice() + y.getQuota() + y.getTravelTime() + y.getCurrentPostCode() + y.getStartingPostCode() + y.getDestinationPostCode());
                }
            }
        }
        return matchesFoundCarOffer;
    }
    
    public ArrayList<String> findMatchBySinglePostCode(String postCode) {
             ArrayList<String> matchesFoundCarOffer = new ArrayList<String>();
             
            
            for (CarOffer y : CarOfferList) {
                if(postCode == y.getDestinationPostCode())
                {
                    
                    matchesFoundCarOffer.add(y.getPrice() + ", " + y.getQuota() + ", " + y.getTravelTime() + ", " + y.getCurrentPostCode() + ", " + y.getStartingPostCode() + ", " + y.getDestinationPostCode());
                    //System.out.println(y.getPrice() + y.getQuota() + y.getTravelTime() + y.getCurrentPostCode() + y.getStartingPostCode() + y.getDestinationPostCode());
                }
            }
            return matchesFoundCarOffer;
        
    }
    
    public void addCarOffer(int carOfferID, JourneyDetails jDetails, Date pickupTime, Date dropOffTime){
        
        
         CarOfferList.add(new CarOffer(carOfferID, jDetails.getQuota(), jDetails.getPrice(), jDetails.getCurrentPostCode(), jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getTravelTime(), pickupTime, dropOffTime));
        
    }
    
      public void addCarSeek( int carSeekID, Member ownerMember, Date pickupTime, String destinationPostCode, String currentPostCode){
          
                   
         CarSeekList.add(new CarSeek(  carSeekID,  ownerMember,  pickupTime,  destinationPostCode,  currentPostCode));
        
    }
      
    public Date[] addCarSeekDates(DestinationTime pickupTime, DestinationTime dropOfftime)
    {
       /* Date[] date = new Date[1];
        //date[0] = pickupTime
        GeneralFunctions.setTime(date[0], pickupTime.getYear(), 1, 1, 1, 1);
        date[0] = GeneralFunctions.getTime();
        //date[1] = dropOffTime
        GeneralFunctions.setTime(date[1], dropOfftime.getYear(), 1, 1, 1, 1);
        date[1] = GeneralFunctions.getTime();*/
        return null;
    }
      
    public boolean addCarSeekDetails()
    {
        /*
        if(GeneralFunctions.checkDatesAreEqual(pickupTime, dropOffTime) == true){
            addCarSeek("102", null, pickupTime, 2022, 2044);
        }else{
            return false;
        }*/
        return true;
    }
      
     
    private void chooseOffer()
    {
    
    }
    
    private void displayOffersAvailable()
    {
    
    }
   

    private void EditCarSeek() {

    }

    private void RemoveCarSeek() {
    }

    private void searchCarOffers() {

    }

}