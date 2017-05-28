/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.registration.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class RegisterStepTwoController implements Initializable {

    SuberDB db;
    RegisterUserSession registerSession;
    
    @FXML
    private ToggleGroup genderGroup;
    
    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Text statusLabel;
    
    @FXML
    private Text genderLabel;
    
    @FXML
    private RadioButton maleRButton;
    
    @FXML
    private RadioButton femaleRButton;

    @FXML
    private Text dobLabel;
    
    @FXML
    private TextField dobText;

    @FXML
    private Text userTypeLabel;

    @FXML
    private ToggleGroup accountTypeGroup;
    
    @FXML
    private RadioButton driverRButton;

    @FXML
    private RadioButton riderRButton;
    
    @FXML
    private Text corporateLabel;

    @FXML
    private TextField corporateText;
    
    
    /**
     * Return user to the home page
     * If you can be bothered, update old page details with session details
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
    
    @FXML
    private void handleNextButtonAction(ActionEvent event) throws ParseException {
        // Declare paint compatible colour codes for validation
        final ColorPicker errorColour = new ColorPicker();
        errorColour.setValue(Color.RED);
        final ColorPicker correctColour = new ColorPicker();
        correctColour.setValue(Color.BLACK);
        
        // Setup radiobutton gruop
        RadioButton selectedGenderButton = (RadioButton) genderGroup.getSelectedToggle();
        RadioButton selectedTypeButton = (RadioButton) accountTypeGroup.getSelectedToggle();

        // Set the colour of all labels to black
        genderLabel.setFill(correctColour.getValue());
        dobLabel.setFill(correctColour.getValue());
        userTypeLabel.setFill(correctColour.getValue());
        corporateLabel.setFill(correctColour.getValue());
        
        //validation
        if (dobText.getText().length() < 1) {
            dobLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please fill in all required fields.");
            return;
        }
        
        if (!Validator.isValidDate(dobText.getText())) {
            dobLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please fill in our date of birth in the format YYYY-MM-DD.");
            return;
        }
        
        // purge sqli
        if (Validator.isMaliciousText(dobText.getText())) {
            dobLabel.setFill(errorColour.getValue());
            dobText.setText("");
            displayErrorMessage("Please enter your date of birth correctly.");
            return;
        }
        
        if (Validator.isMaliciousText(corporateText.getText())) {
            corporateLabel.setFill(errorColour.getValue());
            corporateText.setText("");
            displayErrorMessage("Please enter your corporate code correctly.");
            return;
        }
        
        // check if valid corporate code
        if (corporateText.getText().length() > 0 && Validator.containsDigits(corporateText.getText())) {
            // try sql for corporate
            String corporateCheck = "SELECT `corporate_code` FROM `ab66986_suber`.`corporate_entity` ";
            corporateCheck += "WHERE `corporate_code` = '" + corporateText.getText() + "';";
            try {
                ResultSet results = db.executeQuery(corporateCheck);
                results.next();
                String retrievedCode = results.getString(1);
                if (retrievedCode.equals(corporateText.getText())) {
                    registerSession.setCompanyCode(Integer.parseInt(corporateText.getText()));         
                }
            } catch (Exception e) {
                corporateLabel.setFill(errorColour.getValue());
                corporateText.setText("");
                displayErrorMessage("Invalid corporate code.");
                return;
            }
        }
        
        // assume data is clean
        registerSession.setGender(selectedGenderButton.getText());
        Date dob = new SimpleDateFormat("yyyy-mm-dd").parse(dobText.getText());
        registerSession.setDob(dob);
        registerSession.setAccountType(selectedTypeButton.getText());
        
        // goto next page :)
        try {
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
        maleRButton.setToggleGroup(genderGroup);
        femaleRButton.setToggleGroup(genderGroup);
        driverRButton.setToggleGroup(accountTypeGroup);
        riderRButton.setToggleGroup(accountTypeGroup);
    }    
    
}
