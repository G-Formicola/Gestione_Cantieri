package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertAreaCantiereController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertAreaCantiereView {

    private Stage stage = null ;
    private InsertAreaCantiereController insertAreaCantiereController = null ;
    private static InsertAreaCantiereView uniqueInstance = null ;

    private InsertAreaCantiereView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Area_Cantiere_View.fxml"));
        Parent root = loader.load();
        insertAreaCantiereController = loader.getController();
        insertAreaCantiereController.setControls();
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
        insertAreaCantiereController.clean();
    }

    public static InsertAreaCantiereView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertAreaCantiereView(owner);
        }
        return uniqueInstance;
    }

    public static InsertAreaCantiereView getInstance(){
        return uniqueInstance;
    }
}
