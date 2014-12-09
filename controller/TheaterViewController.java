/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GPP_project.controller;

import GPP_project.model.Seat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author
 * Alexander
 */
public class TheaterViewController implements Initializable{
    @FXML
    private GridPane theater;
    
    @FXML
    private Text infoField;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        /**Image img1 = new Image("resources/images/test.png", 32, 32, true, false);
        ImageView imgv = new ImageView();
        imgv.setImage(img1);
           */
        
        
        //theater.add(imgv, 1,1);
        infoField = new Text("Cookies!");
        
        
        //initTheater(theater);
    }
    
     public void initTheater(GridPane theater){
         
            Image img1 = new Image("resources/images/test.png", 32, 32, true, false);
            Image img2 = new Image("resources/images/test1.png", 32, 32, true, false);
          
            // Mock of movie theater layout - as gotten from database
            // Instead, there should be a class that imports and constructs
            // a movie theater layout from the database, 
            // that the program will use
            Seat[][] seats = new Seat[6][];

            for(int row = 0; row < seats.length; row++){
                if(row == 0) seats[row] = new Seat[]{
                    new Seat(row,row),new Seat(row,row),new Seat(row,row),
                    new Seat(row,row),new Seat(row,row),
                    new Seat(row,row),new Seat(row,row),new Seat(row,row)};
                if(row == 1) seats[row] = new Seat[]{
                    new Seat(row,row),new Seat(row,row),new Seat(row,row),
                    null, null,
                    new Seat(row,row),new Seat(row,row),new Seat(row,row)};
                if(row == 2) seats[row] = new Seat[]{
                    null,null,null,
                    null, null,
                    null,null,null};
                if(row == 3) seats[row] = new Seat[]{
                    new Seat(row,row),new Seat(row,row),new Seat(row,row),
                    new Seat(row,row), new Seat(row, row),
                    new Seat(row,row),new Seat(row,row),new Seat(row,row)};
                if(row == 4) seats[row] = new Seat[]{
                    new Seat(row,row),new Seat(row,row),new Seat(row,row),
                    new Seat(row,row),new Seat(row,row),
                    new Seat(row,row),new Seat(row,row),new Seat(row,row)};
                if(row == 5) seats[row] = new Seat[]{
                    new Seat(row,row),new Seat(row,row),new Seat(row,row),
                    new Seat(row,row),new Seat(row,row),
                    new Seat(row,row),new Seat(row,row),new Seat(row,row)};
            }
            // Adding individual seats to positions in our gridpane
            for(int row = 0; row < seats.length; row++){
                for(int col = 0; col < seats[row].length; col++){
                    if(seats[row][col] instanceof Seat){
                        ImageView seatview = new ImageView();
                        seatview.setImage(img1);

                        theater.add(seatview, col, row);

                        System.out.print("x");
                    }else{
                        System.out.print(" ");
                        ImageView nullview = new ImageView();
                        nullview.setImage(img2);

                        theater.add(nullview, col, row);
                    }
                }
             System.out.println();
         }
     }
}
