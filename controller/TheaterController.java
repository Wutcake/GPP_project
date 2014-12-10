package GPP_project.controller;

import GPP_project.model.Screening;
import GPP_project.model.Seat;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


/**
 * Created by pseudo on 05-11-2014.
 */
public class TheaterController {
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
    
    @FXML
    private TextField nameInput;
     
    @FXML
    private TextField phoneNumberInput;
    
    // Needs better method of counting available / total seats...
    private Integer seatCounter = 0, amountSelected = 0;
    private int screeningID;
    
    
    private Screening screeningTheater;
    private ArrayList<Seat> toBeReserved = new ArrayList<>();
    private ArrayList<ArrayList<Seat>> ALLSeatsRowCol;
    private ArrayList<Seat> ALLSeats;
    private ArrayList<ArrayList<IntegerProperty>> reservationIDsRowCol;
    private Statement SQLStatement;
    
    // Not reserved, selected or null.
    Image img1 = new Image("GPP_project/resources/images/test.png", 32, 32, true, false);
    // Null
    Image img2 = new Image("GPP_project/resources/images/test1.png", 32, 32, true, false);
    // Selected
    Image img3 = new Image("GPP_project/resources/images/test2.png", 32, 32, true, false);
    // Reserved
    Image img4 = new Image("GPP_project/resources/images/test3.png", 32, 32, true, false);

    public TheaterController(Statement SQLStatement, ArrayList<ArrayList<Seat>> ALLSeatsTheaterRowCol, 
            ArrayList<Seat> ALLSeats, Screening screening, int screeningID) throws Exception{
        
        this.ALLSeats = ALLSeats;
        this.ALLSeatsRowCol = ALLSeatsTheaterRowCol;
        this.SQLStatement = SQLStatement;
        
        this.screeningID = screeningID;
        screeningTheater = screening;
        setTheater(SQLStatement);
        
    }
    
    @FXML
    private void initialize() {
    }
    
    
    
    public void setTheater(Statement SQLStatement) throws Exception{
        reservationIDsRowCol.clear();
        reservationIDsRowCol.add(new ArrayList<IntegerProperty>());
        for(int row = 1; row < ALLSeatsRowCol.size(); row++){
            reservationIDsRowCol.add(new ArrayList<IntegerProperty>());
            reservationIDsRowCol.get(row).add(new SimpleIntegerProperty(0));
            for(int col = 1; col < ALLSeatsRowCol.get(row).size(); col++){
                reservationIDsRowCol.get(row).add(new SimpleIntegerProperty(getReservationID(SQLStatement, getSeat(row, col).getSeatID())));
                    
                if(getSeat(row, col).getSeatNumber() > 0){
                    initializeGrid(getSeat(row, col), col, row);
                    seatCounter++;
                }else{
                    initializeGrid(getSeat(row, col), col, row);
                }   
            }     
            
        }
    }

    public void setTextFields(Screening screening){
        movieField.setText(screening.getMovieTitle());
        infoField.setText(screening.getDay() + "." + screening.getMonth() + "." + screening.getYear() + "\n"
        + "Sal " + screening.getTheaterNumber() + "\n"
        + screening.getHour() + " : " + screening.getMinute());
        
        availableSeatsText.setText(seatCounter.toString());
        totalSeatsText.setText(seatCounter.toString());
    }
    
    private void initializeGrid(Seat seat, int col, int row) {
        ImageView imgv = new ImageView();
        int reservationID = reservationIDsRowCol.get(row).get(col);
        
        if(seat.getSeatNumber() > 0){
            if(reservationID == 0){
                // lambda expression
                imgv.setOnMouseClicked(evt -> 
                    seatSelected(imgv, seat)
                );
                imgv.setImage(img1);
                theater.add(imgv , col, row);
            }else{
                imgv.setImage(img4);
                theater.add(imgv, col, row);
            }
            seat.reservationIDProperty().addListener(new ChangeListener(){
                @Override public void changed(ObservableValue o, Object oldVal, Object newVal){
                    seatReserved(imgv);}
            });
            
        } else {
            imgv.setImage(img2);
            theater.add(imgv, col, row);
        }
    }
    
    @FXML
    private void reserveButton(){
        System.out.println("DU HAR TRYKKET RESERVÉR!");
        System.out.println(nameInput.getCharacters().toString());
        System.out.println(phoneNumberInput.getCharacters().toString());
        for(int counter = 0; counter < toBeReserved.size(); counter++){
            toBeReserved.get(counter).select();
            toBeReserved.get(counter).setReservationID(25);
            System.out.println(toBeReserved.get(counter) + " has been reserved.");
        }
        amountSelected = 0;
        toBeReserved.clear();
    }
    
    private void seatReserved(ImageView imgv){
        imgv.setImage(img4);
        imgv.setOnMouseClicked(null);
    }
  
    private void seatSelected(ImageView imgview, Seat seat){
        if(seat.isSelected()){
            imgview.setImage(img1);
            seat.select();
            amountSelected--;
            System.out.println("removed: " + toBeReserved.get(amountSelected));
            toBeReserved.remove(seat);
        }else{
            imgview.setImage(img3);
            seat.select();
            toBeReserved.add(seat);
            System.out.println("added: " + toBeReserved.get(amountSelected));
            amountSelected++;
        }
        // Test code
        System.out.println("A seat was selected!");
    }
    
    private Seat getSeat(int row, int col){
        return ALLSeatsRowCol.get(row).get(col);
    }
    
    private int getReservationID(Statement SQLStatement, int seatID) throws Exception{
        String query = "SELECT * FROM ReservedSeats WHERE SeatID = " + seatID + "AND ScreeningID = " + screeningID;
        ResultSet rs = SQLStatement.executeQuery(query);
        
        rs.next();
        int customerID = rs.getInt("CustomerID");
        rs.close();
        return customerID;
    }
    
}
