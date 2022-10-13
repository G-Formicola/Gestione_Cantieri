package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ReadStatCantController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ReadStatCantView {

    private Stage stage = null ;
    private ReadStatCantController readStatCantController = null ;
    private static ReadStatCantView uniqueInstance = null ;

    private ReadStatCantView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Read_Stat_Cant_View.fxml"));
        Parent root = loader.load();
        readStatCantController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(730);
        stage.setMinHeight(468);
        stage.setResizable(false);
    }

    public void show(Integer idCantiereGestito){
        if (stage != null) {
            readStatCantController.setControls(idCantiereGestito);
            stage.show();
        }
    }

    public void hide(){
        if (stage != null) {
            stage.hide();
        }
    }

    public static ReadStatCantView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new ReadStatCantView(owner);
        }
        return uniqueInstance;
    }

    public static ReadStatCantView getInstance(){
        return uniqueInstance;
    }
}
