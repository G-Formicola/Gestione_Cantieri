package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertCapocantiereController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertCapocantiereView {

    private Stage stage = null ;
    private InsertCapocantiereController insertCapocantiereController = null ;
    private static InsertCapocantiereView uniqueInstance = null ;

    private InsertCapocantiereView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Capocantiere_View.fxml"));
        Parent root = loader.load();
        insertCapocantiereController = loader.getController();
        insertCapocantiereController.setControls();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(600);
        stage.setMinHeight(400);
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
        insertCapocantiereController.clean();
    }


    public static InsertCapocantiereView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertCapocantiereView(owner);
        }
        return uniqueInstance;
    }

    public static InsertCapocantiereView getInstance(){
        return uniqueInstance;
    }
}
