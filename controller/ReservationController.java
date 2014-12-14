/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPP_project.controller;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import GPP_project.model.*;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Erik
 */
public class ReservationController{
    private ArrayList<Reservation> reservations;
    private ArrayList<Button> resButtons = new ArrayList();
    private ArrayList<Reservation> ALLReservations;
    private ArrayList<Customer> ALLCustomers;
    private ArrayList<Screening> ALLScreenings;
    private Statement statement;
    private Reservation clickedReservation;
    
    //Initialises Containers and textfields from Widere Biograf.fxml
    
    private VBox buttonPane;
    private TextField searchBar;
    private Button searchButton;
    private Button deleteButton;
    private Text nameField;
    private Text titleField;
    private Text theaterField;
    private Text seatField;
    private Text phoneField;
    
    public ReservationController(ArrayList<Customer> ALLCustomers,ArrayList<Reservation> ALLReservations,ArrayList<Screening> ALLScreenings, 
            Statement statement) throws Exception{
        this.ALLCustomers=ALLCustomers;
        this.ALLReservations=ALLReservations;
        this.ALLScreenings=ALLScreenings;
        this.statement=statement;
        reservations = new ArrayList<Reservation>();
        for(Reservation res: ALLReservations){
            reservations.add(res);
        }
        
    } 
    
    public void FXMLLoader(VBox buttonPane, TextField searchBar, Button searchButton, Button deleteButton, 
            Text nameField, Text titleField, Text theaterField, Text seatField, Text phoneField){
        
        this.buttonPane = buttonPane;
        this.searchBar = searchBar;
        this.searchButton = searchButton;
        this.deleteButton = deleteButton;
        this.nameField = nameField;
        this.titleField = titleField;
        this.theaterField = theaterField;
        this.seatField = seatField;
        this.phoneField = phoneField;
        
        Image SearchIcon = new Image("GPP_project/resources/images/Searchicon.png");
        searchButton.setGraphic(new ImageView(SearchIcon));
            
        makeButtons();
    
    }
    
    public void makeButtons() {
        
        //Generates an ArrayList of buttons based on a list of Reservations
        //To be shown in the reservation overview.
        for(Button btn: resButtons){
            buttonPane.getChildren().remove(btn);
        }
        resButtons.clear();
        for(int i=1;i<reservations.size();i++){
            Reservation res=reservations.get(i);
            if(res.getReservationID()==0)
                continue;
            Button btn = new Button();
                btn.setText(res.getScreening().getDate()+"    "+res.getName());
                btn.setPrefSize(331, 65);
                btn.setOnAction(new EventHandler<ActionEvent>() {
            
                    /*Gives each button the ability to overwrite their 
                    reservation's information onto the detailed information field.*/
                    @Override
                    public void handle(ActionEvent event) {
                        nameField.setText(res.getName());
                        phoneField.setText("Tlf.: "+res.getPhoneNumber());
                        titleField.setText(res.getScreening().getMovieTitle());
                        theaterField.setText("Theater: "+res.getScreening().getTheaterNumber());
                        seatField.setText(res.printSeats());
                        clickedReservation= res;
                    }
                });
            resButtons.add(btn);
        }
        //Adds the buttons from the list to the VBox in the view. 
        for(Button btn: resButtons){
            buttonPane.getChildren().add(btn);
        }
        
    }  
    public void search() throws Exception{
        Reservation JD=reservations.get(0);
        reservations.clear();
        reservations.add(JD);
        String query = "SELECT * FROM Customers WHERE Name LIKE '"+searchBar.getCharacters().toString()+"%'";
        ResultSet rs = statement.executeQuery(query);
        ArrayList<Integer> IDs = new ArrayList<>();
        while(rs.next()){
            int CustomerID = rs.getInt("CustomerID");
            IDs.add(CustomerID);
        }
        rs.close();
        for(int i: IDs){
            query = "SELECT * FROM Reservations WHERE CustomerID = '"+i+"'";
            rs = statement.executeQuery(query);
            if(rs.next()){
                reservations.add(ALLReservations.get(rs.getInt("ReservationID")));
            }
            rs.close();
        }
        makeButtons();
    }
    
    public void deleteReservation()throws Exception{
        int reservationID = clickedReservation.getReservationID();
        int customerID = clickedReservation.getCustomerID();
        int screeningID = clickedReservation.getScreeningID();
        ArrayList<Seat> reservedSeats = clickedReservation.getSeats();

        String update = "UPDATE Reservations SET ReservationID = ReservationID - 1 WHERE ReservationID > '" + reservationID + "'";
        statement.executeUpdate(update);

        update = "DELETE FROM Reservations WHERE ReservationID = '" + reservationID + "' AND CustomerID = '" + customerID + "' AND ScreeningID = '" + screeningID + "'";
        statement.executeUpdate(update);

        for(int i=0;i<reservedSeats.size();i++){
            int seatID = reservedSeats.get(i).getID();

            update = "DELETE FROM ReservedSeats WHERE SeatID = '"+ seatID + "' AND CustomerID = '" + customerID + "' AND ScreeningID = '" + screeningID + "'";
            statement.executeUpdate(update);

            update = "UPDATE Screenings SET SeatsReserved = SeatsReserved - 1 WHERE ScreeningID = '" + screeningID + "'";
            statement.executeUpdate(update);
        }

        // 'Klikker' på John Doe
        clickedReservation = ALLReservations.get(0);
        nameField.setText(clickedReservation.getName());
        phoneField.setText("Tlf.: "+clickedReservation.getPhoneNumber());
        titleField.setText(clickedReservation.getScreening().getMovieTitle());
        theaterField.setText("Theater: "+clickedReservation.getScreening().getTheaterNumber());
        seatField.setText(clickedReservation.printSeats());
    }

    public Reservation getClickedReservation(){
        return clickedReservation;
    }
    public void Update() {
        reservations.clear();
        for(Reservation res: ALLReservations){
            reservations.add(res);
        }
        makeButtons();
        
    } 
}
