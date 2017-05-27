/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.registration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

/**
 *
 * @author Andrew
 */
public class RegisterLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("view/RegisterStepOne.fxml");

        Parent root = FXMLLoader.load(location);
         
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Suber - Register");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
