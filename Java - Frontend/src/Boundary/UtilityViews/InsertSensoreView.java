package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertSensoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertSensoreView {

    private Stage stage = null ;
    private InsertSensoreController insertSensoreController = null ;
    private static InsertSensoreView uniqueInstance = null ;

    private InsertSensoreView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Sensore_View.fxml"));
        Parent root = loader.load();
        insertSensoreController = loader.getController();
        insertSensoreController.setControls();
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
        insertSensoreController.clean();
    }

    public static InsertSensoreView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertSensoreView(owner);
        }
        return uniqueInstance;
    }

    public static InsertSensoreView getInstance(){
        return uniqueInstance;
    }
}
