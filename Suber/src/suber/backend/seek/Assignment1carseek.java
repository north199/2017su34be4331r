///////////////////////////////////////////////////////////////////////////////
//TEST CLASS - used for testing code
package assignment1carseek;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
//import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Assignment1carseek extends Application {

    @Override
    public void start(Stage primaryStage) {

        /* Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        //CarOffer carOfferTest = new CarOffer();
        //CarSeek carSeekTest = new CarSeek();
        SeekOfferManager sOM = new SeekOfferManager();

        TextArea resultBox = new TextArea();
        resultBox.setPrefRowCount(10);
        resultBox.setPrefColumnCount(100);
        resultBox.setWrapText(true);
        resultBox.setPrefWidth(150);

        StackPane root = new StackPane();
        root.getChildren().add(resultBox);
        resultBox.appendText("Results Box");

        DestinationTime startingTime = new DestinationTime(2017, 4, 23, 0, 0);
        DestinationTime endingTime = new DestinationTime(2017, 4, 23, 0, 0);

        java.util.Date date1 = startingTime.getDate();
        java.util.Date date2 = endingTime.getDate();

        double diff = date2.getTime() - date1.getTime();

        double diffSeconds = diff / 1000 % 60;
        double diffMinutes = diff / (60 * 1000) % 60;
        double diffHours = diff / (60 * 60 * 1000);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        //Timestamp timeStamp1 = new Timestamp(System.currentTimeMillis());
        //Timestamp timeStamp2 = new Timestamp(System.currentTimeMillis());

        resultBox.appendText("\n");
        resultBox.appendText("Difference in time");
        resultBox.appendText("Time in hours " + (diff / 3600) / 1000);
        resultBox.appendText("\n Time in hours " + (int) diffHours);
        resultBox.appendText("\n Time in minutes " + diffMinutes);
        resultBox.appendText("\n Time in seconds " + diffSeconds);
        resultBox.appendText("\n");

        //200, "100", "2033", "2033", "2056"2"
        //if(!date1.equals(date2)){
        ////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        ////ADD CAR OFFER
        JourneyDetails jDetails = new JourneyDetails(4, 4.99, "current postcode", 2033, 2056, "" + diff);
        Car car = new Car(18, "123NSW", "Honda", 2013, 4);
        sOM.addCarOffer(7, car, jDetails, new java.sql.Date(System.currentTimeMillis()), timeStamp);

        ////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        ////DISPLAY ALL CAR OFFER
        resultBox.appendText("\n");
        resultBox.appendText("!!!DisplayAllCarOffer()!!!");

        ResultSet rsDisplayAllCarOffer = sOM.DisplayAllCarOffer();
        try {
            int entriesCounter = 0;
            while (rsDisplayAllCarOffer.next()) {
                String trip_offer_id = rsDisplayAllCarOffer.getString("trip_offer_id");
                String car_id = rsDisplayAllCarOffer.getString("car_id");
                String driver_id = rsDisplayAllCarOffer.getString("driver_id");
                String offer_date = rsDisplayAllCarOffer.getString("offer_date");
                String start_time = rsDisplayAllCarOffer.getString("start_time");
                String end_time = rsDisplayAllCarOffer.getString("end_time");
                String start_postcode = rsDisplayAllCarOffer.getString("start_postcode");
                String end_postcode = rsDisplayAllCarOffer.getString("end_postcode");
                String number_of_spaces = rsDisplayAllCarOffer.getString("number_of_spaces");

                String licence_plate = rsDisplayAllCarOffer.getString("licence_plate");
                String user_id = rsDisplayAllCarOffer.getString("user_id");
                String car_brand = rsDisplayAllCarOffer.getString("car_brand");
                String car_year = rsDisplayAllCarOffer.getString("car_year");
                String car_capacity = rsDisplayAllCarOffer.getString("car_capacity");

                entriesCounter++;
                System.out.println(entriesCounter + ". ");
                System.out.print("trip_offer_id: " + trip_offer_id);
                System.out.print(", car_id: " + car_id);
                System.out.print(", driver_id: " + driver_id);
                System.out.print(", rider_id: " + offer_date);
                System.out.print(", start_time: " + start_time);
                System.out.print(", end_time: " + end_time);
                System.out.print(", status: " + start_postcode);
                System.out.print(", start_postcode: " + end_postcode);
                System.out.println(", end_postcode: " + number_of_spaces);
                System.out.print(", licence_plate: " + licence_plate);
                System.out.print(", user_id: " + user_id);
                System.out.print(", car_brand: " + car_brand);
                System.out.print(", car_year: " + car_year);
                System.out.println(", car_capacity: " + car_capacity);

            }
        } catch (Exception e) {

        }
        ////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        ////REMOVE CAR OFFER BY CAR ID
        sOM.removeCarOffer(4);

        ////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        ////EDIT CAR OFFER
        JourneyDetails newJDetails = new JourneyDetails(6, 10.99, "current postcode", 2033, 2056, "" + diff);
        Car newCar = new Car(5, "PER456", "Honda", 2013, 40);
        sOM.editCarOffer(5, newCar, newJDetails, new java.sql.Date(System.currentTimeMillis()), timeStamp);
        ////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////
        ///// SEARCH CAR OFFER
        ///// 1 means search by car_capacity
        ///// 2 means search by car_brand
        ///// 3 means search by start_postcode
        ///// 4 means search by end_postcode
        ///// 5 means search by offer_date

        //ResultSet rsDisplaySearch = sOM.searchCarOffers(1, "" + 4);
        //ResultSet rsDisplaySearch = sOM.searchCarOffers(2, "Honda");
        //ResultSet rsDisplaySearch = sOM.searchCarOffers(3, "2033");
        //ResultSet rsDisplaySearch = sOM.searchCarOffers(4, "2056");
        ResultSet rsDisplaySearch = sOM.searchCarOffers(5, "2017-05-28");

        try {
            int entriesCounter2 = 0;
            System.out.print("PRINTING searched entries");
            while (rsDisplaySearch.next()) {
                //System.out.print("PRINTING start");
                String trip_offer_id = rsDisplaySearch.getString("trip_offer_id");
                String car_id = rsDisplaySearch.getString("car_id");
                String driver_id = rsDisplaySearch.getString("driver_id");
                String offer_date = rsDisplaySearch.getString("offer_date");
                String start_time = rsDisplaySearch.getString("start_time");
                String end_time = rsDisplaySearch.getString("end_time");
                String start_postcode = rsDisplaySearch.getString("start_postcode");
                String end_postcode = rsDisplaySearch.getString("end_postcode");
                String number_of_spaces = rsDisplaySearch.getString("number_of_spaces");

                String licence_plate = rsDisplaySearch.getString("licence_plate");
                String user_id = rsDisplaySearch.getString("user_id");
                String car_brand = rsDisplaySearch.getString("car_brand");
                String car_year = rsDisplaySearch.getString("car_year");
                String car_capacity = rsDisplaySearch.getString("car_capacity");

                entriesCounter2++;
                System.out.println(entriesCounter2 + ". ");
                System.out.print("trip_offer_id: " + trip_offer_id);
                System.out.print(", car_id: " + car_id);
                System.out.print(", driver_id: " + driver_id);
                System.out.print(", rider_id: " + offer_date);
                System.out.print(", start_time: " + start_time);
                //System.out.print(", end_time: " + end_time);
                System.out.print(", status: " + start_postcode);
                System.out.print(", start_postcode: " + end_postcode);
                System.out.println(", end_postcode: " + number_of_spaces);

                System.out.print(", licence_plate: " + licence_plate);
                System.out.print(", user_id: " + user_id);
                System.out.print(", car_brand: " + car_brand);
                System.out.print(", car_year: " + car_year);
                System.out.println(", car_capacity: " + car_capacity);
            }
        } catch (Exception e) {

        }
        ///////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
        ///// Add Car SEEK

        //carSeekID, riderId, sqlDateSeeking, startTime, endTime, numberOfPeople, startPostCode, endPostCode
        java.sql.Date newSqlDate = new java.sql.Date(System.currentTimeMillis());
        sOM.addCarSeek(0, 0, newSqlDate, timeStamp, 4, 2011, 2013);

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ////REMOVE CAR SEEK
        sOM.removeCarSeek(5);
        ///////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        ////DISPLAY ALL  CAR SEEK
        ResultSet rsDisplayAllCarSeek = sOM.DisplayAllCarSeek();

        /* try{
         while (rsDisplayAllCarSeek.next()) {
                String trip_seek_id = rsDisplayAllCarSeek.getString(1);
                String rider_id = rsDisplayAllCarSeek.getString(2);
                String date_seeking = rsDisplayAllCarSeek.getString(3);
               // String rider_id = rsDisplayAllCarSeek.getString(4);
                // Date date_seeking = rsDisplayAllCarSeek.getDate("date_seeking");
                //String rider_id = rsDisplayAllCarSeek.getString("rider_id");
                
                 
                 //String date_seeking = rsDisplayAllCarSeek.getString("date_seeking");
                //String start_time = rsDisplayAllCarSeek.getString("start_time");
                
                
                System.out.println("trip_seek_id: " + trip_seek_id);
                System.out.println("rider_id: " + rider_id);
                //System.out.println("date_seeking: " + date_seeking.toString());
                //System.out.print(", driver_id: " + date_seeking);
                //System.out.print(", rider_id: " + start_time);
               
             }
        } catch (Exception e){
            
        }*/
        try {
            System.out.println("DISPLAY ALL  CAR SEEK");
            int entriesCounter = 0;
            while (rsDisplayAllCarSeek.next()) {
                String trip_seek_id = rsDisplayAllCarSeek.getString("trip_seek_id");
                String rider_id = rsDisplayAllCarSeek.getString("rider_id");
                //String date_seeking = rsDisplayAllCarSeek.getString("date_seeking");
                //String start_time = rsDisplayAllCarSeek.getString("start_time");

                //String end_time = rsDisplayAllCarSeek.getString("end_time");
                //String number_of_people = rsDisplayAllCarSeek.getString("number_of_people");
                //String start_postcode = rsDisplayAllCarSeek.getString("start_postcode");
                //String end_postcode = rsDisplayAllCarSeek.getString("end_postcode");
                entriesCounter++;
                System.out.println(entriesCounter + ". ");
                System.out.print("trip_offer_id: " + trip_seek_id);
                System.out.print(", car_id: " + rider_id);
                //System.out.print(", driver_id: " + date_seeking);
                //System.out.print(", rider_id: " + start_time);
                //System.out.print(", start_time: " + end_time);
                //System.out.print(", end_time: " + number_of_people);
                //System.out.print(", status: " + start_postcode);
                //System.out.print(", start_postcode: " + end_postcode);

            }
        } catch (Exception e) {

        }
        /////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////
        ///EDIT SEEK editCarSeek
        sOM.editCarSeek(17, 99, newSqlDate, timeStamp, 0, 0, 0);
        //////////////////////////
        ///////////////////////////////////////

        ///////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////
        /// SEARCH CAR SEEK
        ///// 1 means search by number_of_people
        ///// 2 means search by start_postcode
        ///// 3 means search by end_postcode
        ///// 4 means search by date_seeking
        //ResultSet rsDisplaySeekSearch = sOM.searchCarSeeks(1, "" + 4);
        //ResultSet rsDisplaySeekSearch = sOM.searchCarSeeks(2, "2033");
        ResultSet rsDisplaySeekSearch = sOM.searchCarSeeks(3, "2040");
        //ResultSet rsDisplaySeekSearch = sOM.searchCarSeeks(4, "2017-05-28");

        try {
            int entriesCounter = 0;
            System.out.println("SEARCH CAR SEEK");
            while (rsDisplaySeekSearch.next()) {

                String trip_seek_id = rsDisplaySeekSearch.getString("trip_seek_id");
                String rider_id = rsDisplaySeekSearch.getString("rider_id");

                //String date_seeking = rsDisplaySeekSearch.getString("date_seeking");
                //String start_time = rsDisplaySeekSearch.getString("start_time");
                //String end_time = rsDisplaySeekSearch.getString("end_time");
                //String number_of_people = rsDisplaySeekSearch.getString("number_of_people");
                //String start_postcode = rsDisplaySeekSearch.getString("start_postcode");
                //String end_postcode = rsDisplaySeekSearch.getString("end_postcode");
                entriesCounter++;
                System.out.println(entriesCounter + ". ");
                System.out.print("trip_offer_id: " + trip_seek_id);
                System.out.print(", car_id: " + rider_id);
                //System.out.print(", driver_id: " + date_seeking);
                //System.out.print(", rider_id: " + start_time);
                //System.out.print(", start_time: " + end_time);
                //System.out.print(", end_time: " + number_of_people);
                //System.out.print(", status: " + start_postcode);
                //System.out.print(", start_postcode: " + end_postcode);

            }
        } catch (Exception e) {

        }
        ////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////

        /*resultBox.appendText("\n");
        resultBox.appendText("findMatchByPostCode()");
        appendListToTextArea(resultBox, sOM.findMatchByPostCode());

        resultBox.appendText("\n");
        resultBox.appendText("findMatchBySinglePostCode(String postCode)");
        appendListToTextArea(resultBox, sOM.findMatchBySinglePostCode("2088"));

        resultBox.appendText("\n");
        resultBox.appendText("sOM.checkDatesAreEqual(new Date(), new Date()");*/
        //GeneralFunctions.setTime(date1);
        //GeneralFunctions.getTime();
        /*GeneralFunctions.setTime(date1, 2017, 11, 11, 15, 55);
        date1 = GeneralFunctions.getTime();
        GeneralFunctions.setTime(date2, 2017, 6, 15, 5, 55);
        date2 = GeneralFunctions.getTime();*/
        if (GeneralFunctions.checkDatesAreEqual(date1, date2)) {
            resultBox.appendText("TRUE");
        } else {
            resultBox.appendText("FALSE");
        }

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("CarSeek and CarOffer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /* public void appendListToTextArea(TextArea resultBox, ArrayList<String> list) {
        resultBox.appendText("\n");
        for (String tempString : list) {
            resultBox.appendText(tempString);
            resultBox.appendText("\n");
        }
    }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
