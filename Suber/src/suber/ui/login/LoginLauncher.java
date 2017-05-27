/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.ui.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import static javafx.application.Application.launch;

/**
 *
 * @author Andrew
 */
public class LoginLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("view/Login.fxml");

        Parent root = FXMLLoader.load(location);
         
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Suber - Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
