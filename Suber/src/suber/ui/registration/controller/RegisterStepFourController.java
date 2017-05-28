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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.registration.RegisterPaymentDetails;
import suber.backend.member.registration.RegisterUser;
import suber.backend.member.session.LoginSession;
import suber.backend.member.session.RegisterUserSession;
import suber.backend.security.Crypto;
import suber.backend.security.Validator;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class RegisterStepFourController implements Initializable {

    SuberDB db;
    RegisterUserSession registerSession;
    RegisterUser registration;
    RegisterPaymentDetails registrationPayments;
    LoginSession loginSession;

    @FXML
    private Button nextButton;

    @FXML
    private Button backButton;

    @FXML
    private Text statusLabel;

    @FXML
    private Text bankLabel;

    @FXML
    private Text cardLabel;

    @FXML
    private TextField bankName;

    @FXML
    private TextField bankBSB;

    @FXML
    private TextField bankAcc;

    @FXML
    private TextField cardName;

    @FXML
    private TextField cardNum;

    @FXML
    private TextField cardExpiry;

    @FXML
    private TextField cardCVV;

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
    private void handleNextButtonAction(ActionEvent event) throws Exception {
        System.out.println("reached");
        // Declare paint compatible colour codes for validation
        final ColorPicker errorColour = new ColorPicker();
        errorColour.setValue(Color.RED);
        final ColorPicker correctColour = new ColorPicker();
        correctColour.setValue(Color.BLACK);

        // colour resets
        bankLabel.setFill(correctColour.getValue());
        cardLabel.setFill(correctColour.getValue());

        // Validation
        if (bankName.getText().length() < 1 || bankAcc.getText().length() < 1 || bankBSB.getText().length() < 1
                || cardName.getText().length() < 1 || cardNum.getText().length() < 1 || cardExpiry.getText().length() < 1
                || cardCVV.getText().length() < 1) {
            bankLabel.setFill(errorColour.getValue());
            cardLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please fill in all fields.");
            return;
        }

        if (!Validator.containsDigits(bankBSB.getText()) || !Validator.containsDigits(bankAcc.getText())) {
            bankLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please re-enter your bank details using numbers.");
            return;
        }

        if (!Validator.containsDigits(cardNum.getText()) || !Validator.containsDigits(cardCVV.getText())) {
            cardLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please re-enter your card details only using numbers.");
            return;
        }

        // purge sqli
        if (Validator.isMaliciousText(bankName.getText())) {
            bankLabel.setFill(errorColour.getValue());
            bankName.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(bankAcc.getText())) {
            bankLabel.setFill(errorColour.getValue());
            bankAcc.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(bankBSB.getText())) {
            bankLabel.setFill(errorColour.getValue());
            bankBSB.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(cardName.getText())) {
            cardLabel.setFill(errorColour.getValue());
            cardName.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(cardNum.getText())) {
            cardLabel.setFill(errorColour.getValue());
            cardNum.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }

        if (Validator.isMaliciousText(cardExpiry.getText())) {
            cardLabel.setFill(errorColour.getValue());
            cardExpiry.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        if (Validator.isMaliciousText(cardCVV.getText())) {
            cardLabel.setFill(errorColour.getValue());
            cardCVV.setText("");
            displayErrorMessage("Please fill in all fields correctly.");
            return;
        }
        System.out.println("data is clean");
        // assume data is clean and set session
        /**
         * String firstName, String lastName, int homeStreetNum, String
         * homeStreetAddress, String homeSuburb, int homePostCode, int
         * workStreetNum, String workStreetAddress, String workSuburb, int
         * workPostCode, String phoneNum, String email, String password, String
         * gender, Date dateOfBirth
         */
        try {
            // create new user
            registration.RegisterNewUser(registerSession.getFName(), registerSession.getLName(), registerSession.getHomeNumber(), registerSession.getHomeStreet(),
                registerSession.getHomeSuburb(), registerSession.getHomePostcode(), registerSession.getWorkNumber(), registerSession.getWorkStreet(),
                registerSession.getWorkSuburb(), registerSession.getWorkPostcode(), registerSession.getPhoneNumber(), registerSession.getEmail(),
                Crypto.encryptString(registerSession.getPassword()), registerSession.getIsCorporate(), registerSession.getGender(), registerSession.getDob());
            
            if (Validator.containsDigits("" + registerSession.getUserId())) {
                // determine type of acc
                registration.RegisterNewAccountType(registerSession.getUserId(), registerSession.getAccountType());
                
                // upload encrypted payment deets
                registrationPayments.RegisterPaymentDetails(bankName.getText(), bankAcc.getText(), bankBSB.getText(), 
                    cardName.getText(), cardCVV.getText(), cardExpiry.getText(), cardNum.getText());
            } else { 
                System.out.println("registration somehow failed?");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            displayErrorMessage("An error has occurred, please restart the app.");
            return;
        }
        
        // done
        System.out.println("if i got here succesfully then a user has been registered YEAH THE BOYS");
        loginSession.setEmail(registerSession.getEmail());
        loginSession.setUserId("" + registerSession.getUserId());
        loginSession.setAccountType(registerSession.getAccountType());
        
        if (loginSession.getAccountType().equalsIgnoreCase("ride")) {
            // display dashboard
        } else if (loginSession.getAccountType().equalsIgnoreCase("drive")) {
            // display car registration page
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
        registration = Suber.registration;
        registrationPayments = Suber.registrationPayments;
        loginSession = Suber.session;
    }

}
