/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.registration.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
