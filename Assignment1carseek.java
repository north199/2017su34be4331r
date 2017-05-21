/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1carseek;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.util.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author adimt460a
 */
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
       sOM.LoadListFromDataBase();

        
        TextArea resultBox = new TextArea();
        resultBox.setPrefRowCount(10);
        resultBox.setPrefColumnCount(100);
        resultBox.setWrapText(true);
        resultBox.setPrefWidth(150);
        
        StackPane root = new StackPane();
        root.getChildren().add(resultBox);
        resultBox.appendText("Results Box");
        
        resultBox.appendText("\n");
        resultBox.appendText("DisplayAllCarSeek()");
        appendListToTextArea(resultBox, sOM.DisplayAllCarSeek());
        resultBox.appendText("\n");
        resultBox.appendText("DisplayAllCarOffer()");
        appendListToTextArea(resultBox, sOM.DisplayAllCarOffer());
        
         resultBox.appendText("\n Added Car Offer and Car Seek \n");
         JourneyDetails jDetails = new JourneyDetails(200.0, 100.0, "2033", "2033", "2056", "2");
         DestinationTime startingTime = new DestinationTime(2017, 4, 23, 0, 0);
         DestinationTime endingTime = new DestinationTime(2017, 4, 23, 0, 0);
         
         Date date1 = startingTime.getDate();
         Date date2 = endingTime.getDate();
         
         double diff = date2.getTime() - date1.getTime();
         
         double diffSeconds = diff / 1000 % 60;
         double diffMinutes = diff / (60 * 1000) % 60;
         double diffHours = diff / (60 * 60 * 1000);
         
         //200, "100", "2033", "2033", "2056"2"
         if(!date1.equals(date2)){
        sOM.addCarOffer(7, jDetails, date1, date2);
         } else {
             //Dates are equal
         }
        
        sOM.addCarSeek(6, null, null, "2204", "2210");
        
         resultBox.appendText("\n");
        resultBox.appendText("Difference in time");
        resultBox.appendText("Time in hours " + (diff/3600)/1000);
        resultBox.appendText("\n Time in hours " + (int)diffHours);
        resultBox.appendText("\n Time in minutes " + diffMinutes);
        resultBox.appendText("\n Time in seconds " + diffSeconds);
        resultBox.appendText("\n");
        resultBox.appendText("DisplayAllCarSeek()");
        appendListToTextArea(resultBox, sOM.DisplayAllCarSeek());
        resultBox.appendText("\n");
        resultBox.appendText("DisplayAllCarOffer()");
        appendListToTextArea(resultBox, sOM.DisplayAllCarOffer());
        
        
        resultBox.appendText("\n");
        resultBox.appendText("findMatchByPostCode()");
        appendListToTextArea(resultBox, sOM.findMatchByPostCode());
        
        
        resultBox.appendText("\n");
        resultBox.appendText("findMatchBySinglePostCode(String postCode)");
        appendListToTextArea(resultBox, sOM.findMatchBySinglePostCode("2088"));
        
        resultBox.appendText("\n");
        resultBox.appendText("sOM.checkDatesAreEqual(new Date(), new Date()");
        
        
        
        //GeneralFunctions.setTime(date1);
        //GeneralFunctions.getTime();
        /*GeneralFunctions.setTime(date1, 2017, 11, 11, 15, 55);
        date1 = GeneralFunctions.getTime();
        GeneralFunctions.setTime(date2, 2017, 6, 15, 5, 55);
        date2 = GeneralFunctions.getTime();*/
        
        
        
        if(GeneralFunctions.checkDatesAreEqual(date1, date2)){
             resultBox.appendText("TRUE");
        } else 
        {
             resultBox.appendText("FALSE");
        }
        
        
        
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("CarSeek and CarOffer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void appendListToTextArea(TextArea resultBox, ArrayList<String> list)
    {
        resultBox.appendText("\n");
        for(String tempString : list)
        {
             resultBox.appendText(tempString);
             resultBox.appendText("\n");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}