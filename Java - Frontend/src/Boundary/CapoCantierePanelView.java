package Boundary;

import Boundary.PanelController.CapoCantierePanelController;
import Control.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CapoCantierePanelView {

    private final Controller ctrl ;
    private CapoCantierePanelController capoCantierePanelController;
    private Integer idCapoCantiere ;

    public CapoCantierePanelView() {
        this.ctrl = Controller.getInstance() ;
    }

    public void start(Stage stage, Integer idCC) throws IOException {
        FXMLLoader loader = new FXMLLoader(CapoCantierePanelView.class.getResource("FXML/CC_Panel.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.hide();
        stage.setScene(scene);

        // Recupera il controller della view, istanziato da JavaFX.
        capoCantierePanelController = loader.getController();
        // Passa al controller della view il controller della business logic "ctrl"
        capoCantierePanelController.setMainController(ctrl);
        // Passa al controller della view l'id dell'capocantiere che ha loggato
        capoCantierePanelController.setIdLabel(idCC);
        capoCantierePanelController.setCantiereLabels(idCC);
        capoCantierePanelController.setListViewOperai();
        idCapoCantiere = idCC ;

        stage.setTitle("Pannello Capo-Cantiere");
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
