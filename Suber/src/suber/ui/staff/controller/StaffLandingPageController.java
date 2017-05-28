/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.staff.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private Button myProfileButton;

    @FXML
    private Button editButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button viewOffersButton;

    @FXML
    private Button viewSeekButton;

    @FXML
    private Button manageCorporatesButton;

    @FXML
    private Button mailButton;

    @FXML
    private Text loginLabel;

    @FXML
    private void handleViewOffersButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StaffOffers.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Admin");
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void handleViewSeekButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StaffSeek.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Admin");
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void handleManageCorporatesButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StaffCorporate.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Admin");
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void handleMailButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StaffMail.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Admin");
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @FXML
    private void handleMyProfileButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/StaffMail.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - My Profile");
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private void displayTableData() {
        offerTable.setEditable(true);
        offerTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();

        /**
         * # trip_offer_id # car_id # driver_id # offer_date # start_time #
         * end_time # start_postcode # end_postcode # number_of_spaces
         */
        /**TableColumn tripOfferId = new TableColumn("Offer ID");
        tripOfferId.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty("Offer ID");
            }
        });
        TableColumn carId = new TableColumn("Car ID");
        TableColumn driverId = new TableColumn("Driver ID");
        TableColumn offerDate = new TableColumn("Offer Date");
        TableColumn startTime = new TableColumn("Start Time");
        TableColumn endTime = new TableColumn("End Time");
        TableColumn startPostcode = new TableColumn("Start Postcode");
        TableColumn endPostcode = new TableColumn("End Postcode");
        TableColumn carSpots = new TableColumn("Car Spots");

            
        offerTable.getColumns().addAll(tripOfferId, carId, driverId, offerDate, startTime, endTime, startPostcode, endPostcode, carSpots);
        **/
        
        String tableDataQuery = "SELECT * FROM `" + db.getDatabaseName() + "`.`trip_offering`;";
        try {
            ResultSet rs = db.executeQuery(tableDataQuery);
            
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                offerTable.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    System.out.println(row);
                    row.add(rs.getString(i));
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
