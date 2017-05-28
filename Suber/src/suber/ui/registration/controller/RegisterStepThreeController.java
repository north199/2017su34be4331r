/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.registration.controller;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.RegisterUserSession;
import suber.backend.security.Validator;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class RegisterStepThreeController implements Initializable {

    SuberDB db;
    RegisterUserSession registerSession;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Text statusLabel;
    
    @FXML
    private CheckBox tosCBox;
    
    @FXML
    private Text workLabel;
    
    @FXML
    private Text homeLabel;
    
    @FXML
    private TextField workAddressNum;
    
    @FXML
    private TextField workStreet;
    
    @FXML
    private TextField workSuburb;
    
    @FXML
    private TextField workPostCode;
    
    @FXML
    private TextField homeAddressNum;
    
    @FXML
    private TextField homeStreet;
    
    @FXML
    private TextField homeSuburb;
    
    @FXML
    private TextField homePostCode;
    
    /**
     * Return user to the home page If you can be bothered, update old page
     * details with session details
     *
     * @param event
     */
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RegisterStepOne.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Register");
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    
    /**
     * Validates the registration field.
     *
     * @param event
     */
    @FXML
    private void handleNextButtonAction(ActionEvent event) {
        // Declare paint compatible colour codes for validation
        final ColorPicker errorColour = new ColorPicker();
        errorColour.setValue(Color.RED);
        final ColorPicker correctColour = new ColorPicker();
        correctColour.setValue(Color.BLACK);
        
        // colour resets
        homeLabel.setFill(correctColour.getValue());
        workLabel.setFill(correctColour.getValue());
        
        // Check if agreed to TOS
        if (!tosCBox.isSelected()) {
            displayErrorMessage("You must agree with our terms.");
            return;
        }
        
        // validation and stuff
        if (workAddressNum.getText().length() < 1 || workStreet.getText().length() < 1 || workSuburb.getText().length() < 1
                || workPostCode.getText().length() < 1 || homeAddressNum.getText().length() < 1 || homeStreet.getText().length() < 1
                || homeSuburb.getText().length() < 1 || homePostCode.getText().length() < 1) {
            homeLabel.setFill(errorColour.getValue());
            workLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please fill in all fields.");
            return;
        }
        
        if (!Validator.containsDigits(workAddressNum.getText()) || !Validator.containsDigits(workPostCode.getText()) ||
                !Validator.containsDigits(homeAddressNum.getText()) || !Validator.containsDigits(homePostCode.getText())) {
            homeLabel.setFill(errorColour.getValue());
            workLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        
        // sqli purge
        if (Validator.isMaliciousText(workAddressNum.getText())) {
            workLabel.setFill(errorColour.getValue());
            workAddressNum.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(workStreet.getText())) {
            workLabel.setFill(errorColour.getValue());
            workStreet.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(workSuburb.getText())) {
            workLabel.setFill(errorColour.getValue());
            workSuburb.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(workPostCode.getText())) {
            workLabel.setFill(errorColour.getValue());
            workPostCode.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(homeAddressNum.getText())) {
            homeLabel.setFill(errorColour.getValue());
            homeAddressNum.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(homeStreet.getText())) {
            homeLabel.setFill(errorColour.getValue());
            homeStreet.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(homeSuburb.getText())) {
            homeLabel.setFill(errorColour.getValue());
            homeSuburb.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(homePostCode.getText())) {
            homeLabel.setFill(errorColour.getValue());
            homePostCode.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        
        // assume data is clean
        registerSession.setWorkNumber(Integer.parseInt(workAddressNum.getText()));
        registerSession.setWorkPostcode(Integer.parseInt(workPostCode.getText()));
        registerSession.setWorkStreet(workStreet.getText());
        registerSession.setWorkSuburb(workSuburb.getText());
        registerSession.setHomeNumber(Integer.parseInt(homeAddressNum.getText()));
        registerSession.setHomePostcode(Integer.parseInt(homePostCode.getText()));
        registerSession.setHomeStreet(homeStreet.getText());
        registerSession.setHomeSuburb(homeSuburb.getText());
        
        // next page :)
        System.out.println("Success!");
        /**
         * try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RegisterStepThree.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Register");
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
         */
    }
    
    /**
     * Utilises the statusText field to display error messages
     *
     * @param error Error message as a string
     */
    private void displayErrorMessage(String error) {
        statusLabel.setText(error);
        statusLabel.setVisible(true);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new SuberDB();
        registerSession = Suber.registerSession;
    }    
    
}
