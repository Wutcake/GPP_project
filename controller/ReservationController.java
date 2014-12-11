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
public class ReservationController implements Initializable {
    private ArrayList<Reservation> reservations;
    private final ArrayList<Reservation> ALLReservations;
    private final ArrayList<Customer> ALLCustomers;
    private final ArrayList<Screening> ALLScreenings;
    private final Statement statement;
    
    //Initialises Containers and textfields from Reservationsside.fxml
    @FXML //fx:id="ButtonPane"
    private VBox ButtonPane;
    @FXML
    private TextField SearchBar;
    @FXML
    private Button SearchButton;
    @FXML
    private Text NameField;
    @FXML
    private Text TitleField;
    @FXML
    private Text TheaterField;
    @FXML
    private Text SeatField;
    public ReservationController(ArrayList<Customer> ALLCustomers,ArrayList<Reservation> ALLReservations,ArrayList<Screening> ALLScreenings, Statement statement){
        this.ALLCustomers=ALLCustomers;
        this.ALLReservations=ALLReservations;
        this.ALLScreenings=ALLScreenings;
        this.statement=statement;
        
        reservations = ALLReservations;
        Image SearchIcon = new Image(getClass().getResourceAsStream("GPP_Prooject/resources/images/SearchIcon.png"));
        SearchButton.setGraphic(new ImageView(SearchIcon));
        SearchButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Search(statement);
            }
        });
        
        MakeButtons(ButtonPane, NameField, TitleField, TheaterField,SeatField);
        
    } 
    
    public void MakeButtons(VBox ButtonPane, Text Namefield, Text TitleField, Text TheaterField, Text SeatField) {
        
        //Generates an ArrayList of buttons based on a list of Reservations
        //To be shown in the reservation overview.
        ArrayList<Button> resButtons = new ArrayList();
        for(int i=1;i<reservations.size();i++){
            Reservation res=reservations.get(i);
            Button btn = new Button();
                btn.setText(res.getName());
                btn.setPrefSize(331, 65);
                btn.setOnAction(new EventHandler<ActionEvent>() {
            
                    /*Gives each button the ability to overwrite their 
                    reservation's information onto the detailed information field.*/
                    @Override
                    public void handle(ActionEvent event) {
                        Namefield.setText(res.getName());
                        TitleField.setText(res.getScreening().getMovieTitle());
                        TheaterField.setText("Theater: "+res.getScreening().getTheaterNumber());
                        SeatField.setText(res.printSeats());
                    }
                });
            resButtons.add(btn);
        }
        //Adds the buttons from the list to the VBox in the view. 
        for(Button btn: resButtons){
            ButtonPane.getChildren().add(btn);
        }
        
    }  
    public void Search(Statement statement) throws Exception{
        Reservation JD=reservations.get(0);
        reservations.clear();
        reservations.add(JD);
        String query = "SELECT * FROM Customers WHERE Name RLIKE"+SearchBar.textProperty();
        ResultSet rs = statement.executeQuery(query);
        ArrayList<Integer> IDs = new ArrayList();
        while(rs.next()){
            int CustomerID = rs.getInt("CustomerID");
            IDs.add(CustomerID);
        }
        for(int i: IDs){
            query = "SELECT * FROM Reservations WHERE CustomerID="+i;
            rs = statement.executeQuery(query);
            while(rs.next()){
                int scrnID = rs.getInt("ScreeningID");
                Reservation res = new Reservation(ALLScreenings.get(scrnID),ALLCustomers.get(i));
            }
        }
    }
    
}
