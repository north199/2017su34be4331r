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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.RegisterUserSession;
import suber.backend.security.Crypto;
import suber.backend.security.Validator;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class RegisterStepOneController implements Initializable {

    SuberDB db;
    RegisterUserSession registerSession;

    @FXML
    private Button nextButton;
    
    @FXML
    private Button backButton;

    @FXML
    private Text firstNameLabel;

    @FXML
    private Text lastNameLabel;

    @FXML
    private Text statusLabel;

    @FXML
    private Text emailLabel;

    @FXML
    private Text phoneLabel;

    @FXML
    private Text passLabel;

    @FXML
    private Text confirmPassLabel;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField phoneText;

    @FXML
    private PasswordField passText;

    @FXML
    private PasswordField confirmPassText;

    /**
     * Return user to the home page
     * @param event 
     */
    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../login/view/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Login");
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
    private void handleNextButtonAction(ActionEvent event) throws Exception {
        // Declare paint compatible colour codes for validation
        final ColorPicker errorColour = new ColorPicker();
        errorColour.setValue(Color.RED);
        final ColorPicker correctColour = new ColorPicker();
        correctColour.setValue(Color.BLACK);

        // Set the colour of all labels to black
        firstNameLabel.setFill(correctColour.getValue());
        lastNameLabel.setFill(correctColour.getValue());
        emailLabel.setFill(correctColour.getValue());
        phoneLabel.setFill(correctColour.getValue());
        passLabel.setFill(correctColour.getValue());
        confirmPassLabel.setFill(correctColour.getValue());

        // Validate all fields are not empty
        if (firstNameText.getText().length() < 1 || lastNameText.getText().length() < 1 || emailText.getText().length() < 1
                || phoneText.getText().length() < 1 || passText.getText().length() < 1 || confirmPassText.getText().length() < 1) {
            firstNameLabel.setFill(errorColour.getValue());
            lastNameLabel.setFill(errorColour.getValue());
            emailLabel.setFill(errorColour.getValue());
            phoneLabel.setFill(errorColour.getValue());
            passLabel.setFill(errorColour.getValue());
            confirmPassLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please fill in all fields.");
            return;
        }

        // Validate confirmed and normal passwords match
        if (!passText.getText().equals(confirmPassText.getText())) {
            passLabel.setFill(errorColour.getValue());
            confirmPassLabel.setFill(errorColour.getValue());
            displayErrorMessage("Your passwords do not match.");
            return;
        }

        // Ensure no numbers in names
        if (Validator.containsDigits(firstNameText.getText()) || Validator.containsDigits(lastNameText.getText())) {
            firstNameLabel.setFill(errorColour.getValue());
            lastNameLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please enter your name correctly.");
            return;
        }

        // Email validation
        if (!Validator.isValidEmail(emailText.getText())) {
            emailLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please enter a valid email.");
            return;
        }

        // phone number validation
        if (phoneText.getText().length() != 10) {
            phoneLabel.setFill(errorColour.getValue());
            phoneText.setText("");
            displayErrorMessage("Please enter a valid Australian mobile number.");
            return;
        }
        if (!Validator.containsDigits(phoneText.getText())) {
            phoneLabel.setFill(errorColour.getValue());
            phoneText.setText("");
            displayErrorMessage("Please enter a valid Australian mobile number.");
            return;
        }
        if (!phoneText.getText().startsWith("04")) {
            phoneLabel.setFill(errorColour.getValue());
            phoneText.setText("");
            displayErrorMessage("Please enter a valid Australian mobile number.");
            return;
        }

        // purge for sql injection
        if (Validator.isMaliciousText(firstNameText.getText())) {
            firstNameLabel.setFill(errorColour.getValue());
            firstNameText.setText("");
            displayErrorMessage("Please enter your first name correctly.");
            return;
        }
        if (Validator.isMaliciousText(lastNameText.getText())) {
            lastNameLabel.setFill(errorColour.getValue());
            lastNameText.setText("");
            displayErrorMessage("Please enter your last name correctly.");
            return;
        }
        if (Validator.isMaliciousText(emailText.getText())) {
            emailLabel.setFill(errorColour.getValue());
            emailText.setText("");
            displayErrorMessage("Please enter your email correctly.");
            return;
        }
        if (Validator.isMaliciousText(phoneText.getText())) {
            phoneLabel.setFill(errorColour.getValue());
            phoneText.setText("");
            displayErrorMessage("Please enter your mobile number correctly.");
            return;
        }

        // Make sure password is strong (7 characters of both digits and letters)
        if (!Validator.isStrongPassword(passText.getText())) {
            passLabel.setFill(errorColour.getValue());
            confirmPassLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please enter a strong password with letters and numbers.");
            return;
        }

        // ensure email does not already exits
        String emailDupeQuery = "SELECT `email` FROM " + db.getDatabaseName() + ".`user_list`";
        emailDupeQuery += " WHERE `email` = '" + emailText.getText() + "';";

        try {
            ResultSet results = db.executeQuery(emailDupeQuery);
            results.next();
            String retrievedEmail = results.getString(1);
            if (retrievedEmail.equals(emailText.getText())) {
                emailLabel.setFill(errorColour.getValue());
                displayErrorMessage("The email already has an account.");
                return;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Email is not duplicate.");
        }

        // Assume data is mostly clean and ready for database here
        // Sets model/in memory to record current details
        registerSession.setFName(firstNameText.getText());
        registerSession.setLName(lastNameText.getText());
        registerSession.setEmail(emailText.getText());
        registerSession.setPhoneNumber(phoneText.getText());
        registerSession.setPassword(Crypto.encryptString(passText.getText()));        
        
        // Show next stage in registration process
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RegisterStepTwo.fxml"));
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
        
    }

}
