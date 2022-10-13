package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ReadStatAreeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ReadStatAreeView {

    private Stage stage = null ;
    private ReadStatAreeController readStatAreeController = null ;
    private static ReadStatAreeView uniqueInstance = null ;

    private ReadStatAreeView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Read_Stat_Aree_View.fxml"));
        Parent root = loader.load();
        readStatAreeController = loader.getController();
        readStatAreeController.clean();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(730);
        stage.setMinHeight(509);
        stage.setResizable(false);
    }

    public void show(Integer idCantiereGestito){
        if (stage != null) {
            readStatAreeController.setControls(idCantiereGestito);
            stage.show();
        }
    }

    public void hide(){
        if (stage != null) {
            stage.hide();
        }
    }

    public static ReadStatAreeView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new ReadStatAreeView(owner);
        }
        return uniqueInstance;
    }

    public static ReadStatAreeView getInstance(){
        return uniqueInstance;
    }
}
