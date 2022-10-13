package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ReadInfoOperaioController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ReadInfoOperaioView {

    private Stage stage = null ;
    private ReadInfoOperaioController readInfoOperaioController = null ;
    private static ReadInfoOperaioView uniqueInstance = null ;

    private ReadInfoOperaioView(Window owner) throws IOException {

        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Read_Info_Operaio_View.fxml"));
        Parent root = loader.load();
        readInfoOperaioController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(730);
        stage.setMinHeight(620);
        stage.setResizable(false);

    }

    public void show(Integer operaioScelto, Integer cantiereLavori){
        if (stage != null) {
            readInfoOperaioController.setControls(operaioScelto, cantiereLavori);
            stage.show();
        }
    }

    public void hide(){
        if (stage != null) {
            stage.hide();
        }
    }

    public static ReadInfoOperaioView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new ReadInfoOperaioView(owner);
        }
        return uniqueInstance;
    }

    public static ReadInfoOperaioView getInstance(){
        return uniqueInstance;
    }

    public void refreshMansione() {
        readInfoOperaioController.refreshMansione();
    }
}
