package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertMontaggioSensoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertMontaggioSensoreView {

    private Stage stage = null ;
    private InsertMontaggioSensoreController insertMontaggioSensoreController = null ;
    private static InsertMontaggioSensoreView uniqueInstance = null ;

    private InsertMontaggioSensoreView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Montaggio_Sensore_View.fxml"));
        Parent root = loader.load();
        insertMontaggioSensoreController = loader.getController();
        insertMontaggioSensoreController.setControls();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(343);
        stage.setMinHeight(267);
        stage.setResizable(false);
    }

    public void show(){
        if (stage != null) {
            clean();
            stage.show();
        }
    }

    public void hide(){
        if (stage != null) {
            stage.hide();
        }
    }

    public void clean() {
        insertMontaggioSensoreController.clean();
    }

    public static InsertMontaggioSensoreView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertMontaggioSensoreView(owner);
        }
        return uniqueInstance;
    }

    public static InsertMontaggioSensoreView getInstance(){
        return uniqueInstance;
    }
}
