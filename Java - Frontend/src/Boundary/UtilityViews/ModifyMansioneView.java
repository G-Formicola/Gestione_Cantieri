package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ModifyMansioneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyMansioneView {

    private Stage stage = null ;
    private ModifyMansioneController modifyMansioneController = null ;

    public ModifyMansioneView() throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Modify_Mansione_View.fxml"));
        Parent root = loader.load();
        modifyMansioneController = loader.getController();
        modifyMansioneController.setControls(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(343);
        stage.setMinHeight(267);
        stage.setResizable(false);
    }

    public ModifyMansioneView(Integer id_ruolo) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Modify_Mansione_View.fxml"));
        Parent root = loader.load();
        modifyMansioneController = loader.getController();
        modifyMansioneController.setControls(this);
        modifyMansioneController.setIdRuolo(id_ruolo);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMinWidth(343);
        stage.setMinHeight(267);
        stage.setResizable(false);
    }

    public void show(){
        if (stage != null) {
            stage.show();
        }
    }

    public void hide(){
        if (stage != null) {
            stage.close();
        }
    }

}
