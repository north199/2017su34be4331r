/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.driver.controller;

import assignment1carseek.DatabaseManager;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import suber.Suber;
import suber.backend.SuberDB;
import suber.backend.member.session.LoginSession;

/**
 * FXML Controller class
 *
 * @author Andrew
 */
public class DriverSeekController implements Initializable {
    
    SuberDB db;
    LoginSession session;
    DatabaseManager insertDB;
    
    @FXML
    private Text loginLabel;
    
    @FXML
    private TextField dateSeekingText;
    
    @FXML
    private TextField numberOfPeopleText;
    
    @FXML
    private TextField startTimeText;
    
    @FXML
    private TextField endTimeText;
    
    @FXML
    private TextField startPostcodeText;
    
    @FXML
    private TextField endPostcodeText;
    
    @FXML
    private Button submitButton;
    
    @FXML
    private void submitButtonAction(ActionEvent event) {
        
        // if u have time validate the ints :)))
        
        //insertCarSeek(int carSeekID, int riderId, Date sqlDateSeeking, String startTime, int numberOfPeople, int startPostCode, int endPostCode) 
        // '16', '16', '0000-00-00', '0000-00-00', '1', '2035', '2039', '60'
        insertDB.insertCarSeek(Integer.parseInt(session.getUserId()), dateSeekingText.getText(), startTimeText.getText(), Integer.parseInt(numberOfPeopleText.getText()), 
                Integer.parseInt(startPostcodeText.getText()), Integer.parseInt(endPostcodeText.getText()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new SuberDB();
        session = Suber.session;
        try {
            insertDB = new DatabaseManager();
        } catch (Exception ex) {
            Logger.getLogger(DriverSeekController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginLabel.setText("Logged in as:\n" + session.getEmail());
    }    
    
}
