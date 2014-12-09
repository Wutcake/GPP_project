/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author
 * Alexander
 */
public class Main extends Application{

    @Override public void start(Stage stage) throws IOException{
        
        int xSize = 800;
        int ySize = 600;
        Parent root = FXMLLoader.load(getClass().getResource("Layout_theaterview.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Widere Biograf");
        stage.setScene(scene);
        stage.show();
       
        /**
        AnchorPane box = new AnchorPane();
        
        // screening.getMovieTitle() instead of "Ironman 4 (IMAX)"
        Text t = new Text("Film: Ironman 4 (IMAX)");
        t.setFont(new Font(20));
        
        Text t3 = new Text("Forestilling: ");
        t3.setFont(new Font(20));
        
        // screening.getHour() + ":" + screening.getMinute() +
        // + "\n" + "Sal " + screening.getTheaterNumber() + "\n" +
        // screening.getDay() + " - " + screenig.getMonth + " - " +
        // screening.getYear();
        
        Text t4 = new Text(" 19:30\n Sal 4\n 06-12-14");
        t4.setFont(new Font(12));
        Text t1 = new Text(10, 50, "This is a test");
        t1.setFont(new Font(20));
        
        Text t2 = new Text(10, 50, "This is a test");
        t2.setFont(new Font(20));
        
        box.getChildren().add(t3);
        box.getChildren().add(t4);
                
        scene.setFill(Color.WHITE);

        */
        
    }
  
    public static void main(String[] args){
        Application.launch(args);

    }
       
   
   
}
