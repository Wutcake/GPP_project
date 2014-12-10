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
import GPP_project.model.Reservation;

/**
 *
 * @author Erik
 */
public class ReservationController implements Initializable {
    private ArrayList reservations;
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
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        reservations = ALLReservations;
        Image SearchIcon = new Image(getClass().getResourceAsStream("GPP_Prooject/resources/images/SearchIcon.png"));
        SearchButton.setGraphic(new ImageView(SearchIcon));
        SearchButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Search();
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
                        //TitleField.setText(res.getScreening.getMovieTitle());
                        //TheaterField.setText("Theater: "+res.getScreening.getTheaternumber());
                        //Seatfield.setText("Seats: "+res.getSeats(@@@orsomething!@@@);
                    }
                });
            resButtons.add(btn);
        }
        //Adds the buttons from the list to the VBox in the view. 
        for(Button btn: resButtons){
            ButtonPane.getChildren().add(btn);
        }
        
    }  
    public void Search(Statement statement){
        
    }
    
}
