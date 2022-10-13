package Boundary.UtilityViews;

import Boundary.UtilityViewsController.InsertReportSensoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class InsertReportSensoreView {

    private Stage stage = null ;
    private InsertReportSensoreController insertReportSensoreController = null ;
    private static InsertReportSensoreView uniqueInstance = null ;

    private InsertReportSensoreView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Insert_Report_Sensore_View.fxml"));
        Parent root = loader.load();
        insertReportSensoreController = loader.getController();
        insertReportSensoreController.setControls();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(416);
        stage.setMinHeight(424);
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
        insertReportSensoreController.clean();
    }

    public static InsertReportSensoreView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new InsertReportSensoreView(owner);
        }
        return uniqueInstance;
    }

    public static InsertReportSensoreView getInstance(){
        return uniqueInstance;
    }
}
