package GPP_project;

import GPP_project.controller.TheaterController;
import GPP_project.model.Seat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Alexander Bergendorff version 05-12-2014.
 */
public class Main extends Application {
    Seat[][] seats = new Seat[6][];

    public Main() {
        // Get screening data ( Currently mock of screening.theater class )
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
                    null, null, null,
                    null, null,
                    null, null, null};
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
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showTodoView(primaryStage);
    }

    public void showTodoView(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Layout_theaterview.fxml"));
            Parent todoView = loader.load();
            stage.setTitle("Todo");
            stage.setScene(new Scene(todoView, 1024, 576));

            /*Get the controller from the fx:controller attribute of our FXML*/
            TheaterController controller = loader.getController();
            
            // Perhaps put this in controller?
            
            for(int row = 0; row < seats.length; row++){
                    for(int col = 0; col < seats[row].length; col++){
                        if(seats[row][col] instanceof Seat){
                            controller.initializeGrid(seats[row][col], col, row);
                            System.out.print("x");
                        }else{
                            System.out.print(" ");
                            controller.initializeGrid(seats[row][col], col, row);
                        }
                    }
                System.out.println();
            }
            
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); //Show a dialog or something... ?
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
