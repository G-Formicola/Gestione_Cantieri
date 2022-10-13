package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ReadStatController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ReadStatView {
    private Stage stage = null ;
    private ReadStatController readStatController = null ;
    private static ReadStatView uniqueInstance = null ;

    private ReadStatView(Window owner) throws IOException {

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Read_Stat_View.fxml"));
        Parent root = loader.load();
        readStatController = loader.getController();
        readStatController.buildPie();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(750);
        stage.setMinHeight(620);
        stage.setResizable(false);

    }

    public void show(String nomeCantiere){
        if (stage != null) {
            readStatController.setControls(nomeCantiere);
            stage.show();
        }
    }

    public void hide(){
        if (stage != null) {
            stage.hide();
        }
    }

    public static ReadStatView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new ReadStatView(owner);
        }
        return uniqueInstance;
    }

    public static ReadStatView getInstance(){
        return uniqueInstance;
    }
}
