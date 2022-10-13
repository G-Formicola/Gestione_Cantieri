import Control.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

// This class is the entry point of the JavaFX app
// and it will contain the main method
public class Driver extends Application {

    public static void main(String[] args) {
        // Entry point of every JavaFX app which internally calls start method
        launch();
    }

    @Override
    public void start(Stage stage) {
       stage.hide();
       // It retrieves the only instance of the controller, which will get care of the connection to the database
       Controller ctrl = Controller.getInstance(stage);
       // Show the GUI
       ctrl.openLoginPage();

    }

}
