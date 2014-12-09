package GPP_project.controller;

import GPP_project.model.Screening;
import GPP_project.model.Seat;
import GPP_project.model.Theater;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;


/**
 * Created by pseudo on 05-11-2014.
 */
public class TheaterController {

    ArrayList<Seat> toBeReserved = new ArrayList<>();
    
    @FXML
    private GridPane theater;
    
    @FXML
    private Text movieField;
    
    @FXML
    private Text infoField;
    
    @FXML
    private Text availableSeatsText;
    
    @FXML
    private Text totalSeatsText;
    
    // Needs better method of counting available / total seats...
    Integer seatCounter = 0;
    
    private Screening screeningTheater;
    
    Image img1 = new Image("GPP_project/resources/images/test.png", 32, 32, true, false);
    Image img2 = new Image("GPP_project/resources/images/test1.png", 32, 32, true, false);
    Image img3 = new Image("GPP_project/resources/images/test2.png", 32, 32, true, false);

    public TheaterController() {
        
    }
    
    @FXML
    private void initialize() {
    }
    
    public void setTheater(Screening theater){
        screeningTheater = theater;
        for(int row = 0; row < screeningTheater.getRowAmount(); row++){
            for(int col = 0; col < screeningTheater.getRowLength(); col++){
                if(screeningTheater.getSeat(row, col) instanceof Seat){
                    initializeGrid(screeningTheater.getSeat(row, col), col, row);
                    seatCounter++;
                }else{
                    initializeGrid(screeningTheater.getSeat(row, col), col, row);
                }   
            }     
        }
    }

    public void setTextFields(Screening screening){
        movieField.setText(screening.getMovieTitle());
        infoField.setText(screening.getDay() + "." + screening.getMonth() + "." + screening.getYear() + "\n"
        + "Sal " + screening.getTheaterNumber() + "\n"
        + screening.getHour() + " : " + screening.getMinite());
        
        availableSeatsText.setText(seatCounter.toString());
        totalSeatsText.setText(seatCounter.toString());
    }
    
    private void initializeGrid(Seat seat, int col, int row) {
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
    private void reserveButton(){
        System.out.println("DU HAR TRYKKET RESERVÉR!");
    }
  
    private void seatSelected(ImageView imgview, Seat seat){
        if(seat.isSelected()){
            imgview.setImage(img1);
            seat.select();
            toBeReserved.add(seat);
        }else{
            imgview.setImage(img3);
            seat.select();
            toBeReserved.remove(seat);
        }
        // Test code
        System.out.println("A seat was selected!");
    }
}
