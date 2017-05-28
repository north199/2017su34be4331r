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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import suber.backend.member.registration.RegisterCar;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class RegisterVehicleController implements Initializable {
    RegisterCar registerCar;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private Text statusLabel;
            
    @FXML
    private TextField licenseText;
    
    @FXML
    private TextField brandText;
    
    @FXML
    private TextField yearText;
    
    @FXML 
    private TextField capacityText;

    @FXML
    private void handleNextButtonAction(ActionEvent event) throws Exception {
        
        registerCar.RegisterCar(licenseText.getText(), brandText.getText(), Integer.parseInt(yearText.getText()), Integer.parseInt(capacityText.getText()));
        
        // redirect to dashboard driver
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../driver/view/DriverLandingPage.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage primaryStage = new Stage();
            primaryStage.setScene(new Scene(root1, 600, 400));
            primaryStage.setTitle("Suber - Dashboard");
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registerCar = new RegisterCar();
    }

}
