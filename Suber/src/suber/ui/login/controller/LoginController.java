/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.login.controller;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.login.Login;
import suber.backend.member.session.LoginSession;
import suber.backend.security.Validator;

/**
 *
 * @author Andrew
 */
public class LoginController implements Initializable {
    SuberDB db;
    Login login;
    LoginSession session;
    
    @FXML
    private Button createButton;

    @FXML
    private Button loginButton;

    @FXML
    private Text statusLabel;

    @FXML
    private Text emailLabel;
    
    @FXML
    private Text passLabel;

    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passText;


    /**
     * Handles create account button click
     * WHY IS THIS SO SLOW
     * @param event 
     */
    @FXML
    private void handleCreateButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../registration/view/RegisterStepOne.fxml"));
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
     * Handles login button click
     * @param event 
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws Exception {
        // Declare paint compatible colour codes for validation
        final ColorPicker errorColour = new ColorPicker();
        errorColour.setValue(Color.RED);
        final ColorPicker correctColour = new ColorPicker();
        correctColour.setValue(Color.BLACK);

        // Set the colour of all labels to black
        emailLabel.setFill(correctColour.getValue());
        passLabel.setFill(correctColour.getValue());

        // check fields arent empty
        if (emailText.getText().length() < 1 || passText.getText().length() < 1) {
            emailLabel.setFill(errorColour.getValue());
            passLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please fill in all fields.");
        }
        
        // regex email
        if (!Validator.isValidEmail(emailText.getText())) {
            emailLabel.setFill(errorColour.getValue());
            displayErrorMessage("Please enter a valid email.");
        }
        
        // strip sqli
        if (Validator.isMaliciousText(emailText.getText())) {
            emailLabel.setFill(errorColour.getValue());
            emailText.setText("");
            displayErrorMessage("Please enter your email correctly.");
        }
        
        // try login
        if (login.tryLogin(emailText.getText(), passText.getText())) {
            displayDashboard(event);
            
        } else {
            emailLabel.setFill(errorColour.getValue());
            passLabel.setFill(errorColour.getValue());
            displayErrorMessage("Incorrect login!");
        }
    }
    
    private void displayDashboard(ActionEvent event) {
        if (!session.getAccountType().equalsIgnoreCase("Staff")) {
            try {
                /**
                 * FXMLLoader fxmlLoader = new
                 * FXMLLoader(getClass().getResource("../../dashboard/driver/view/DriverLandingPage.fxml"));
                 * Parent root1 = (Parent) fxmlLoader.load(); Stage primaryStage
                 * = new Stage(); primaryStage.setScene(new Scene(root1, 600,
                 * 400)); primaryStage.setTitle("Suber - Dashboard");
                 * primaryStage.setResizable(false);
                    primaryStage.show();*
                 */

                Stage stage = (Stage) loginButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../../driver/view/DriverLandingPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        } else {
            try {
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../../staff/view/StaffLandingPage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                final Node source = (Node) event.getSource();
                final Stage stage2 = (Stage) source.getScene().getWindow();
                stage2.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
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
        login = new Login();
        session = Suber.session;
    }

}
