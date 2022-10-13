package Boundary;

import Boundary.PanelController.AdminPanelController;
import Control.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPanelView {

    private final Controller ctrl ;
    private AdminPanelController adminPanelController ;

    public AdminPanelView() {
        this.ctrl = Controller.getInstance();
    }

    public void start(Stage stage, Integer id) throws IOException {
        FXMLLoader loader = new FXMLLoader(AdminPanelView.class.getResource("FXML/Admin_Panel.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.hide();
        stage.setScene(scene);

        // Recupera il controller della view, istanziato da JavaFX.
        adminPanelController = loader.getController();
        // Passa al controller della view il controller della business logic "ctrl"
        adminPanelController.setMainController(ctrl);
        // Passa al controller della view l'id dell'admin che ha loggato
        adminPanelController.setIdAdmin(id);
        // Carica i cantieri nella ListView
        adminPanelController.setListViewCantieri();

        stage.setTitle("Gestione Cantieri UniNa");
        stage.setFullScreen(false);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.setWidth(750.0);
        stage.setHeight(575.0);
        stage.setResizable(true);
        stage.setMinWidth(635);
        stage.setMinHeight(460);


        stage.show();
    }

}
