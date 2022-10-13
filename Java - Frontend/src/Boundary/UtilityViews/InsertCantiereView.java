package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertCantiereController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertCantiereView {

    private Stage stage = null ;
    private InsertCantiereController insertCantiereController = null ;
    private static InsertCantiereView uniqueInstance = null ;

    private InsertCantiereView(Window owner) throws IOException {
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Cantiere_View.fxml"));
            Parent root = loader.load();
            insertCantiereController = loader.getController();
            insertCantiereController.setControls();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initOwner(owner);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setMinWidth(600);
            stage.setMinHeight(430);
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
        insertCantiereController.clean();
    }

    public void setIdAmm (Integer idAdmin){
        insertCantiereController.setIdAmm(idAdmin);
    }

    public static InsertCantiereView getInstance(Window owner, Integer idAdmin) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertCantiereView(owner);
        }
        uniqueInstance.setIdAmm(idAdmin);
        return uniqueInstance;
    }

    public static InsertCantiereView getInstance(){
        return uniqueInstance;
    }

}
