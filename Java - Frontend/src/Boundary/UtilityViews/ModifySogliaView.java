package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ModifySogliaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ModifySogliaView {

    private Stage stage = null ;
    private ModifySogliaController modifySogliaController = null ;
    private static ModifySogliaView uniqueInstance = null ;

    private ModifySogliaView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Modify_Soglia_View.fxml"));
        Parent root = loader.load();
        modifySogliaController = loader.getController();
        modifySogliaController.setControls();
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
        modifySogliaController.clean();
    }

    public static ModifySogliaView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new ModifySogliaView(owner);
        }
        return uniqueInstance;
    }

    public static ModifySogliaView getInstance(){
        return uniqueInstance;
    }
}
