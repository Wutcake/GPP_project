/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPP_project.controller;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafxreservation.Reservation;

/**
 *
 * @author Erik
 */
public class ReservationController implements Initializable {
    //Initialises Containers and textfields from Reservationsside.fxml
    @FXML //fx:id="ButtonPane"
    private VBox ButtonPane;
    
    @FXML
    private Text NameField;
    
    @FXML
    private Text TitleField;
    
    @FXML
    private Text Theaterfield;
    
    @FXML
    private Text SeatField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
      MakeButtons(ButtonPane, NameField);
        
    }
    
    
    public void MakeButtons(VBox ButtonPane, Text Namefield) {
        
        ArrayList<Reservation> reservations= new ArrayList();
        for(int n=0;n<10;n++){
            Reservation test= new Reservation(n,"Name"+n);
            reservations.add(test);
        }
        //Generates an ArrayList of buttons based on a list of Reservations
        //To be shown in the reservation overview.
        ArrayList<Button> resButtons = new ArrayList();
        int i=0;
        for(Reservation res: reservations){
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
        i++;
        }
        //Adds the buttons from the list to the VBox in the view. 
        for(Button btn: resButtons){
            ButtonPane.getChildren().add(btn);
        }
        
    }  
    
}
