package GPP_project;

import GPP_project.controller.TheaterController;
import GPP_project.model.Seat;
import GPP_project.model.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Alexander Bergendorff version 05-12-2014.
 */
public class Main extends Application {
    Seat[][] seatsArr = new Seat[6][];
    Screening screening;
    Theater theater1;

    public Main() {
        // Get screening data ( Currently mock of screening.theater class )
        ArrayList<SeatRow> seats = new ArrayList<>();
            
        for(int row = 0; row < seatsArr.length; row++){
                SeatRow seatRow;
                if(row == 1){
                    ArrayList<Seat> seatrow = new ArrayList<>();
                    for(int col = 0; col < 8; col++){
                        if(col == 3 || col == 4){
                            seatrow.add(null);
                        }else seatrow.add(new Seat(row, col));
                    }
                    seatRow = new SeatRow(seatrow);
                }else if(row == 2){
                    ArrayList<Seat> seatrow = new ArrayList<>();
                    
                    for(int col = 0; col < 8; col++){
                        seatrow.add(null);
                    }
                    seatRow = new SeatRow(seatrow);
                }else{
                    ArrayList<Seat> seatrow = new ArrayList<>();
                    
                    for(int col = 0; col < 8; col++){
                        seatrow.add(new Seat(row, col));
                    }
                    
                    seatRow = new SeatRow(seatrow);
                }
                seats.add(seatRow);
            }
        theater1 = new Theater(seats, 1);    
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showTodoView(primaryStage);
    }
    public void showReservations(Stage stage) throws IOException{
        Pane root = new Pane();
        Parent layout = FXMLLoader.load(getClass().getResource("Layout_Reservationview.fxml"));
        
        Scene scene = new Scene(root);
        root.getChildren().add(layout);
        
        stage.setTitle("Widere Biograf");
        stage.setScene(scene);
        stage.show();
        
    }
    public void showTodoView(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Layout_theaterview.fxml"));
            Parent todoView = loader.load();

            /*Get the controller from the fx:controller attribute of our FXML*/
            TheaterController controller = loader.getController();
            controller.setTheater(theater1);
            
            stage.setTitle("Widere Biograf");
            stage.setScene(new Scene(todoView, 1024, 576));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); //Show a dialog or something... ?
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
