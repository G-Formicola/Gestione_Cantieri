package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ModifyRespController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ModifyRespView {
    private Stage stage = null ;
    private ModifyRespController modifyRespController = null ;
    private static ModifyRespView uniqueInstance = null ;

    private ModifyRespView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Modify_Resp_View.fxml"));
        Parent root = loader.load();
        modifyRespController = loader.getController();
        modifyRespController.setControls();
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
        modifyRespController.clean();
    }

    public static ModifyRespView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new ModifyRespView(owner);
        }
        return uniqueInstance;
    }

    public static ModifyRespView getInstance(){
        return uniqueInstance;
    }
}
