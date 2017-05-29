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
public class Car {
    int carID;
    int userID = 0;
    String carBrand;
    int carYear;
    int carCapacity;
    String licencePlate;
    public Car(int carID, String licencePlate, String carBrand, int carYear, int carCapacity){
        this.carID = carID;
        this.licencePlate = licencePlate;
        this.carBrand = carBrand;
        this.carYear = carYear;
        this.carCapacity = carCapacity;
    }

    public int getCarID() {
        return carID;
    }
    
     public String getCarLicencePlate() {
        return licencePlate;
    }

    public String getCarBrand() {
        return carBrand;
    }
    
    

    public int getCarYear() {
        return carYear;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }
    
     public void setCarLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }   

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }
            
    
}
