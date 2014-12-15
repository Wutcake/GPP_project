package GPP_project;

import GPP_project.controller.MasterController;

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

    public Main() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        show(primaryStage);
    }
    
    public void show(Stage stage){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/Widere Biograf.fxml"));
            Parent view = loader.load();
            
            MasterController masterController = loader.getController();
            masterController.FXMLInitializer();
            
            stage.setTitle("Widere Biograf");
            stage.setScene(new Scene(view, 1024, 576));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace(); //Show a dialog or something... ?
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
