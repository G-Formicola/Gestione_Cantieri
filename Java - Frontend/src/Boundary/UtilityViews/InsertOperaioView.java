package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertOperaioController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertOperaioView {

    private Stage stage = null ;
    private InsertOperaioController insertOperaioController = null ;
    private static InsertOperaioView uniqueInstance = null ;

    private InsertOperaioView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Operaio_View.fxml"));
        Parent root = loader.load();
        insertOperaioController = loader.getController();
        insertOperaioController.setControls();
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
        insertOperaioController.clean();
    }


    public static InsertOperaioView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertOperaioView(owner);
        }
        return uniqueInstance;
    }

    public static InsertOperaioView getInstance(){
        return uniqueInstance;
    }
}
