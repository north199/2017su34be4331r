
package suber;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


//Different UI elements: Shape, ImageView, Control, Pane (all of these are importable classess
//All scenes belong to one stage

public class Learningjavafx extends Application  {  //Application is a superclass

    
    @Override
    public void start(Stage primaryStage) { //Overriding a method called start in Application(?) - this is equivalane to PSVM in normal
        Button btn = new Button(); //Create button
        btn.setText("Login"); //Set button text
        Image img = new Image("https://upload.wikimedia.org/wikipedia/commons/9/99/Pumpkins.jpg", 200, 200, false, false);
        
        //Determines what button does
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        //Create stackpane to hold the object.
        //What is the difference between pane and stackpane?
        StackPane root = new StackPane(); //StackPane object let's you add multiple scenes - tailors size of container according to the content. Size of button always remains in the middle and contant
        root.getChildren().add(btn);
        root.getChildren().add(new ImageView(img)); //Need imageview to view images lol

        
        Scene scene = new Scene(root, 1000, 600);  //numbers are scene dimensions
        
        primaryStage.setTitle("Login Screen");//set stage title
        primaryStage.setScene(scene); //place scene on the stage
        primaryStage.show(); //display stage 
        
        //Second stage = 2 windows
        Stage stage = new Stage();
        stage.setTitle("Second Stage");
        //add a button
        stage.setScene(new Scene(new Button("New Stage"),100,100)); //can create scenes and buttons all in one line
        stage.show();
    }

    
    public static void main(String[] args) {
        Application.launch(args); //need this invoke method launch
    }
    
}
