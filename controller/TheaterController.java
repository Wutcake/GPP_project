package GPP_project.controller;

import GPP_project.model.Seat;
import GPP_project.model.Theater;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


/**
 * Created by pseudo on 05-11-2014.
 */
public class TheaterController {

    @FXML
    private GridPane theater;
    
    private Theater screeningTheater;
    
    Image img1 = new Image("GPP_project/resources/images/test.png", 32, 32, true, false);
    Image img2 = new Image("GPP_project/resources/images/test1.png", 32, 32, true, false);
    Image img3 = new Image("GPP_project/resources/images/test2.png", 32, 32, true, false);
    
    

    public TheaterController() {
        
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTheater(Theater theater){
        screeningTheater = theater;
        for(int row = 0; row < screeningTheater.getRowAmount(); row++){
            for(int col = 0; col < screeningTheater.getRowLength(); col++){
                if(screeningTheater.getSeat(row, col) instanceof Seat){
                    initializeGrid(screeningTheater.getSeat(row, col), col, row);
                    System.out.print("x");
                    
                }else{
                    System.out.print(" ");
                    initializeGrid(screeningTheater.getSeat(row, col), col, row);
                }   
            }
            System.out.println();        
        }
    }

    public void initializeGrid(Seat seat, int col, int row) {
        ImageView imgv = new ImageView();
        
        if(seat != null){
            // lambda expression
            imgv.setOnMouseClicked(evt -> 
                seatSelected(imgv, seat)
            );
            
            imgv.setImage(img1);
            if(seat.getReservationID() == 0){
                theater.add(imgv , col, row);
            }
        } else {
            imgv.setImage(img2);
            theater.add(imgv, col, row);
        }
    }
    
    @FXML
    private void newButtonHandler(){
        System.out.println("new btn clicked!");
    }
    
    private void seatSelected(ImageView imgview, Seat seat){
        if(seat.isSelected()){
            imgview.setImage(img1);
            seat.select();
        }else{
            imgview.setImage(img3);
            seat.select();
        }
        
        System.out.println("Stuff happened!");
    }
}
