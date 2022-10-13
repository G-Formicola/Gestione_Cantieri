package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertAdminController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertAdminView {

    private Stage stage = null ;
    private InsertAdminController insertAdminController = null ;
    private static InsertAdminView uniqueInstance = null ;

    private InsertAdminView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Admin_View.fxml"));
        Parent root = loader.load();
        insertAdminController = loader.getController();
        insertAdminController.setControls();
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
        insertAdminController.clean();
    }


    public static InsertAdminView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertAdminView(owner);
        }
        return uniqueInstance;
    }

    public static InsertAdminView getInstance(){
        return uniqueInstance;
    }
}
