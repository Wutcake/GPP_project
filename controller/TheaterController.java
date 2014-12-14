package GPP_project.controller;

import GPP_project.model.Customer;
import GPP_project.model.Reservation;
import GPP_project.model.Screening;
import GPP_project.model.Seat;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Created by pseudo on 05-11-2014.
 */
public class TheaterController {

    // FXML elements
    private GridPane theaterGrid;
    private Text movieField;
    private Text infoField;
    private Text availableSeatsText;
    private Text totalSeatsText;
    private Text amountSelectedSeatsText;
    private TextField nameInput;
    private TextField phoneNumberInput;
    
    // Needs better method of counting available / total seats...
    private Integer seatCounter = 0, reservedSeatsCounter = 0, amountSelected = 0;
    private int screeningID;
    private int reservationID = 0;
    
    private Screening screeningTheater;
    private ArrayList<Seat> toBeReserved;
    private ArrayList<Seat> toBeSelected;
    private ArrayList<Customer> ALLCustomers;
    private ArrayList<Reservation> ALLReservations;
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

    public TheaterController(Statement SQLStatement,  
            ArrayList<Seat> ALLSeats, ArrayList<Customer> ALLCustomers, ArrayList<Reservation> ALLReservations) throws Exception{
        
        this.ALLSeats = ALLSeats;
        this.ALLCustomers = ALLCustomers;
        this.ALLReservations = ALLReservations;
        reservationIDsRowCol = new ArrayList<ArrayList<IntegerProperty>>();
        this.SQLStatement = SQLStatement;
        toBeReserved = new ArrayList<Seat>();
    }
    
    public void FXMLLoader(GridPane theaterGrid, Text movieField, Text infoField, 
            Text availableSeatsText, Text totalSeatsText, Text amountSelectedSeatsText,
            TextField nameInput, TextField phoneNumberInput){
        this.theaterGrid = theaterGrid;
        this.movieField = movieField;
        this.infoField = infoField;
        this.availableSeatsText = availableSeatsText;
        this.totalSeatsText = totalSeatsText;
        this.amountSelectedSeatsText = amountSelectedSeatsText;
        this.amountSelectedSeatsText.setText("0");
        
        this.nameInput = nameInput;
        this.phoneNumberInput = phoneNumberInput;
    }
    
    public void setTheater(int screeningID, Screening screening, ArrayList<ArrayList<Seat>> ALLSeatsTheaterRowCol) throws Exception{
        theaterGrid.getChildren().clear();
        
        this.screeningID = screeningID;
        this.ALLSeatsRowCol = ALLSeatsTheaterRowCol;
        screeningTheater = screening;
        
        
        reservationIDsRowCol.clear();
        reservationIDsRowCol.add(new ArrayList<IntegerProperty>());
        for(int row = 1; row < ALLSeatsRowCol.size(); row++){
            reservationIDsRowCol.add(new ArrayList<IntegerProperty>());
            reservationIDsRowCol.get(row).add(new SimpleIntegerProperty(0));
            for(int col = 1; col < ALLSeatsRowCol.get(row).size(); col++){
                reservationIDsRowCol.get(row).add(new SimpleIntegerProperty(getReservationID(getSeat(row, col).getID())));
                if(getSeat(row, col).getSeatNumber() > 0){
                    initializeGrid(getSeat(row, col), col, row);
                }else{
                    initializeGrid(getSeat(row, col), col, row);
                }   
            }
        }
        
        setTextFields(screeningTheater);
    }
    
    public void setTheater(int screeningID, Screening screening, ArrayList<ArrayList<Seat>> ALLSeatsTheaterRowCol, int customerID, ArrayList<Seat> seatsReserved) throws Exception{
        nameInput.setText(ALLCustomers.get(customerID).getName());

        String phoneNum = "" + ALLCustomers.get(customerID).getPhoneNumber();
        phoneNumberInput.setText(phoneNum);

        toBeSelected = seatsReserved;

        setTheater(screeningID, screening, ALLSeatsTheaterRowCol);
    }

    private void setTextFields(Screening screening){
        movieField.setText(screening.getMovieTitle());
        infoField.setText(screening.getDate() + "\n"
        + "Sal " + screening.getTheaterNumber() + "\n"
        + screening.getTime());
        
        Integer availableSeats = screeningTheater.getAmountSeats() - screeningTheater.getAmountReserved();
        
        availableSeatsText.setText(availableSeats.toString());
        totalSeatsText.setText(screeningTheater.getAmountSeats().toString());
    }
    
    private void initializeGrid(Seat seat, int col, int row) throws Exception {
        ImageView imgv = new ImageView();
        IntegerProperty reservationID = reservationIDsRowCol.get(row).get(col);

        if (seat.getSeatNumber() > 0) {
            if (reservationID.get() == 0) {
                // lambda expression
                imgv.setOnMouseClicked(evt ->
                                seatSelected(imgv, seat)
                );
                imgv.setImage(img1);
                theaterGrid.add(imgv, col, row);
            } else {
                imgv.setImage(img4);
                theaterGrid.add(imgv, col, row);
            }
            reservationID.addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue o, Object oldVal, Object newVal) {
                    seatReserved(imgv);
                }
            });

        } else if (toBeSelected.contains(seat)){
            seatSelected(imgv, seat);
            theaterGrid.add(imgv, col, row);
            toBeSelected.remove(seat);
        } else {
            imgv.setImage(img2);
            theaterGrid.add(imgv, col, row);
        }
    }
    
    @FXML
    public void reserveButton() throws Exception{
        System.out.println("DU HAR TRYKKET RESERVÉR!");
        Scanner scanner;
        
        String name = nameInput.getCharacters().toString();
        int phoneNumber = 0;
        
        
        String toParse = phoneNumberInput.getCharacters().toString();
        scanner = new Scanner(toParse);
        
        phoneNumber = scanner.nextInt();
        Customer currentCustomer;
        
        //1. MySQL query check if customer exists
        int customerID = checkCustomer(name, phoneNumber);
        
        if(customerID == 0){
            customerID = ALLCustomers.size();
            createCustomer(customerID, name, phoneNumber);
        }
        
        currentCustomer = ALLCustomers.get(customerID);
        //1b. If not, make one w. name and number
        
        //2. Create reservation w. customer for this screening.
        
        //2.1. Create reservation object(screening, customer);
        int reservationID = ALLReservations.size();
        Reservation currentReservation = new Reservation(screeningTheater, currentCustomer);
        ALLReservations.add(currentReservation);
        
        //3. Reserve seats
        //3.1. Reserve in object
        //3.2. Reserve in Database
        for(int counter = 0; counter < toBeReserved.size(); counter++){
            toBeReserved.get(counter).select();
            reserveNewSeat(reservationID, customerID, toBeReserved.get(counter));
        }
        amountSelected = 0;
        toBeReserved.clear();
        setTheater(screeningID, screeningTheater, ALLSeatsRowCol);
    }
    
    private void seatReserved(ImageView imgv){
        imgv.setImage(img4);
        imgv.setOnMouseClicked(null);
    }
  
    private void seatSelected(ImageView imgview, Seat seat){
        seat.select();
        if(!seat.isSelected()){
            imgview.setImage(img1);
            amountSelected--;
            System.out.println("removed: " + toBeReserved.get(amountSelected));
            toBeReserved.remove(seat);
        }else{
            imgview.setImage(img3);
            toBeReserved.add(seat);
            System.out.println("added: " + toBeReserved.get(amountSelected));
            amountSelected++;
        }
        amountSelectedSeatsText.setText(amountSelected.toString());
        
        // Test code
        System.out.println("A seat was selected!");
    }
    
    private Seat getSeat(int row, int col){
        return ALLSeatsRowCol.get(row).get(col);
    }
    
    private int getReservationID(int seatID) throws Exception{
        String query = "SELECT * FROM ReservedSeats WHERE SeatID = " + seatID + " AND ScreeningID = " + screeningID;
        ResultSet rs = SQLStatement.executeQuery(query);
        int customerID;
        if(rs.next()){
            customerID = rs.getInt("CustomerID");
        }else
            customerID = 0;
        
        rs.close();
        return customerID;
    }
    
    private int checkCustomer(String name, int phoneNumber) throws Exception{
        String query = "SELECT * FROM Customers WHERE Name = '" + name + "' AND PhoneNumber = '" + phoneNumber + "'";
        ResultSet rs = SQLStatement.executeQuery(query);
        
        if(rs.next()){
            int customerID = rs.getInt("CustomerID");
            if(customerID > 0){
                rs.close();
                return customerID;
            }
            rs.close();
            return 0;
        }
        rs.close();
        return 0;
    }
    
    private void createCustomer(int customerID, String name, int phoneNumber) throws Exception{
        String update = "INSERT INTO Customers (CustomerID, Name, PhoneNumber) VALUES ('" + customerID + "', '" +  name + "', '" + phoneNumber + "')";
        
        ALLCustomers.add(new Customer(name, phoneNumber, customerID));
        
        SQLStatement.executeUpdate(update);
    }
    
    private void reserveNewSeat(int reservationID, int customerID, Seat seat) throws Exception{
        String update = "INSERT INTO Reservations (ReservationID, ScreeningID, CustomerID, SeatID) VALUES ('" + reservationID + "', '" + screeningID + "', '" + customerID + "', '" + seat.getID() + "')";
        SQLStatement.executeUpdate(update);
        
        update = "INSERT INTO ReservedSeats(SeatID, ScreeningID, CustomerID) VALUES ('" + seat.getID() + "', '" + screeningID + "', '" + customerID + "')";
        SQLStatement.executeUpdate(update);

        update = "UPDATE Screenings SET SeatsReserved = SeatsReserved + 1 WHERE ScreeningID = '" + screeningID + "'";
        SQLStatement.executeUpdate(update);
    }
}
