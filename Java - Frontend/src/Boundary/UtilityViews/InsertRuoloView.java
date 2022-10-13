package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertRuoloController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertRuoloView {

    private Stage stage = null ;
    private InsertRuoloController insertRuoloController = null ;
    private static InsertRuoloView uniqueInstance = null ;

    private InsertRuoloView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Ruolo_View.fxml"));
        Parent root = loader.load();
        insertRuoloController = loader.getController();
        insertRuoloController.setControls();
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
        insertRuoloController.clean();
    }

    public static InsertRuoloView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertRuoloView(owner);
        }
        return uniqueInstance;
    }

    public static InsertRuoloView getInstance(){
        return uniqueInstance;
    }
}
