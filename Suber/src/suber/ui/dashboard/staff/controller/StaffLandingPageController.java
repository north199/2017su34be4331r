/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.dashboard.staff.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.LoginSession;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class StaffLandingPageController implements Initializable {
    SuberDB db;
    LoginSession session;
    
    @FXML 
    private TableView offerTable;

   
    @FXML
    private Text loginLabel;
    
    
    private void displayTableData() {
        offerTable.setEditable(true);
        offerTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        /**
         * # trip_offer_id 
         * # car_id 
         * # driver_id 
         * # offer_date 
         * # start_time 
         * # end_time 
         * # start_postcode 
         * # end_postcode 
         * # number_of_spaces
         */
        TableColumn tripOfferId = new TableColumn ("Offer ID");
        TableColumn carId = new TableColumn ("Car ID");
        TableColumn driverId = new TableColumn ("Driver ID");
        TableColumn offerDate = new TableColumn ("Offer Date");
        TableColumn startTime = new TableColumn ("Start Time");
        TableColumn endTime = new TableColumn ("End Time");
        TableColumn startPostcode = new TableColumn ("Start Postcode");
        TableColumn endPostcode = new TableColumn ("End Postcode");
        TableColumn carSpots = new TableColumn ("Car Spots");
        
        offerTable.getColumns().addAll(tripOfferId, carId, driverId, offerDate, startTime, endTime, startPostcode, endPostcode, carSpots);
        
        String tableDataQuery = "SELECT * FROM `" + db.getDatabaseName() + "`.`trip_offering`;";
        try {
            ResultSet results = db.executeQuery(tableDataQuery);
            while (results.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= results.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    System.out.println(row);
                    row.add(results.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            offerTable.setItems(data);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new SuberDB();
        session = Suber.session;
        session.setEmail("test@test.com");
        loginLabel.setText("Logged in as:\n" + session.getEmail());
        displayTableData();
    }    
    
}
