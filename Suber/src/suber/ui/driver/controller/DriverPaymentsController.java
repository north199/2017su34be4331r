/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.driver.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.LoginSession;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class DriverPaymentsController implements Initializable {

    SuberDB db;
    LoginSession session;   
    
    @FXML
    private Text loginLabel;

    @FXML
    private Button seekButton;

    @FXML
    private Button offerButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button disputeButton;
    
    @FXML
    private void handleDisputeButton(ActionEvent event) {
        try {
            Stage stage = (Stage) seekButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverHelp.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }
    
    @FXML
    private void handleSeekButton(ActionEvent event) {
        try {
            Stage stage = (Stage) seekButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverSeek.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }

    @FXML
    private void handleOfferButton(ActionEvent event) {
        try {
            Stage stage = (Stage) seekButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverOffer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
        }
    }
    
    @FXML
    private void handleProfileButton(ActionEvent event) {
        try {
            Stage stage = (Stage) profileButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../view/DriverLandingPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            final Node source = (Node) event.getSource();
            final Stage stage2 = (Stage) source.getScene().getWindow();
            stage2.close();
        } catch (IOException ioex) {
            System.out.println(ioex);
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
        loginLabel.setText("Logged in as:\n" + session.getEmail());
    }    
    
}
