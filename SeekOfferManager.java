//Manager class for dummy data + edit/remove/etc... functions

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
        Date date = new Date();
        //Dummy Variables for CarOfferList (Quota/Price/CurrentPC/StartingPC/DestinationPC/TravelTime/PickupTime/DropOffTime/CarType)
        
        /*CarOfferList.add(new CarOffer(1, 200, 100, "2033", "2033", "2056", "2", date, "10", 2, "Four wheel drive"));
        CarOfferList.add(new CarOffer(2, 300, 100, "2033", "2033", "2088", "2", "1", "3", 3, "Van"));
        CarOfferList.add(new CarOffer(3, 400, 100, "2044", "2044", "2056", "2", "5", "11", 3, "Motorcycle"));
        CarOfferList.add(new CarOffer(4, 500, 100, "2033", "2033", "2222", "2", "1", "3", 3, "Off road"));
        CarOfferList.add(new CarOffer(5, 600, 100, "2055", "2055", "2088", "2", "6", "11", 2, "Four wheel drive"));
        CarOfferList.add(new CarOffer(6, 700, 100, "2033", "2033", "2056", "2", "1", "3", 1, "Motorcycle"));*/
        //Dummy Variables for CarSeekList (Member/PickupTime/DestinationPC/CurrentPC)
        CarSeekList.add(new CarSeek(1, null, "12", "2204", "2210", 5));
        CarSeekList.add(new CarSeek(2, null, "16", "2056", "2215", 3));
        CarSeekList.add(new CarSeek(3, null, "13", "2304", "2415", 3));
        CarSeekList.add(new CarSeek(4, null, "12", "2222", "2232", 1));
        CarSeekList.add(new CarSeek(5, null, "18", "2044", "2620", 3));

    }

    private void SaveListToDataBase() {

    }

    public ArrayList<String> DisplayAllCarSeek() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < CarSeekList.size(); i++) {
            CarSeek carSeekTemp = CarSeekList.get(i);
            list.add(carSeekTemp.getCarSeekID() + ", " + carSeekTemp.getPickupTime() + ",  " + carSeekTemp.getDestinationPostCode() + ",  " + carSeekTemp.getCurrentPostCode());
            //System.out.println(carSeekTemp.getPickupTime() + carSeekTemp.getDestinationPostCode() + carSeekTemp.getCurrentPostCode());
        }
        return list;
    }

    public ArrayList<String> DisplayAllCarOffer() {
        ArrayList<String> list = new ArrayList<String>();
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
                if (postCodeTemp == y.getDestinationPostCode()) {
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
            if (postCode == y.getDestinationPostCode()) {

                matchesFoundCarOffer.add(y.getPrice() + ", " + y.getQuota() + ", " + y.getTravelTime() + ", " + y.getCurrentPostCode() + ", " + y.getStartingPostCode() + ", " + y.getDestinationPostCode());
                //System.out.println(y.getPrice() + y.getQuota() + y.getTravelTime() + y.getCurrentPostCode() + y.getStartingPostCode() + y.getDestinationPostCode());
            }
        }
        return matchesFoundCarOffer;

    }

    public boolean addCarOffer(int carOfferID, int quota, double price, String currentPostCode, String startingPostCode, String destinationPostCode, String travelTime, Date pickupTime, Date dropOffTime, int numOfPassengers, String carType) {

        if (startingPostCode == destinationPostCode) {
            System.out.println("Starting postcode and destination postcode are the same");
            return false;

        }

        if (startingPostCode.length() != 4) {
            System.out.println("Invalid postcode length");
            return false;
        }
        
        if (price <= 0){
            System.out.println("Invalid price");
            return false;
        }
        CarOfferList.add(new CarOffer(carOfferID, quota, price, currentPostCode, startingPostCode, destinationPostCode, travelTime, pickupTime, dropOffTime, numOfPassengers, carType));
        return true;

    }

    public void addCarSeek(int carSeekID, Member ownerMember, String pickupTime, String destinationPostCode, String currentPostCode, int numOfPassengers) {
        CarSeekList.add(new CarSeek(carSeekID, ownerMember, pickupTime, destinationPostCode, currentPostCode, numOfPassengers));

    }

    public boolean removeCarOffer(int carOfferID) {
        try {
            for (int i = 0; i < CarOfferList.size(); i++) {
                if (CarOfferList.get(i).getCarOfferID() == carOfferID) {

                    CarOfferList.remove(i);
                    return true;
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid carOfferID");
        }
        return false;
    }

    public boolean removeCarSeek(int carSeekID) {
        try {
            for (int i = 0; i < CarSeekList.size(); i++) {
                if (CarSeekList.get(i).getCarSeekID() == carSeekID) {
                    CarSeekList.remove(i);
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid CarSeekID");
        }
        return false;
    }

    private void RemoveCarSeek(int carSeekID) {
    }

    private void chooseOffer() {

    }

    private void displayOffersAvailable() {

    }

    public void editCarOffer(int carOfferID, int quota, double price, String currentPostCode, String startingPostCode, String destinationPostCode, String travelTime, Date pickupTime, Date dropOffTime, int numOfPassengers, String carType) {
        for (int i = 0; i < CarOfferList.size(); i++) {
            if (CarOfferList.get(i).getCarOfferID() == carOfferID) {
                CarOfferList.get(i).setQuota(quota);
                CarOfferList.get(i).setPrice(price);
                CarOfferList.get(i).setCurrentPostCode(currentPostCode);
                CarOfferList.get(i).setStartingPostCode(startingPostCode);
                CarOfferList.get(i).setDestinationPostCode(destinationPostCode);
                CarOfferList.get(i).setTravelTime(travelTime);
                CarOfferList.get(i).setPickupTime(pickupTime);
                CarOfferList.get(i).setDropOffTime(dropOffTime);
                CarOfferList.get(i).setNumOfPassengers(numOfPassengers);
                CarOfferList.get(i).setCarType(carType);
            }
        }
    }

    public void editCarSeek(int carSeekID, Member ownerMember, String pickupTime, String destinationPostCode, String currentPostCode, int numOfPassengers) {
        for (int i = 0; i < CarSeekList.size(); i++) {
            if (CarSeekList.get(i).getCarSeekID() == carSeekID) {
                CarSeekList.get(i).setPickupTime(pickupTime);
                CarSeekList.get(i).setDestinationPostCode(destinationPostCode);
                CarSeekList.get(i).setCurrentPostCode(currentPostCode);
                CarSeekList.get(i).setNumOfPassengers(numOfPassengers);
            }
        }
    }

    private void searchCarOffers() {

    }

}
