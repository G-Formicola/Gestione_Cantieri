package Boundary.UtilityViews;

import Boundary.UtilityViewsController.ReadReportController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ReadReportView {

    private Stage stage = null ;
    private ReadReportController readReportController = null ;
    private static ReadReportView uniqueInstance = null ;

    private ReadReportView(Window owner) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(InsertCantiereView.class.getResource("../UtilityViewsFXML/Read_Report_View.fxml"));
        Parent root = loader.load();
        readReportController = loader.getController();
        readReportController.setControls();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(730);
        stage.setMinHeight(509);
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
        readReportController.clean();
    }

    public static ReadReportView getInstance(Window owner) throws IOException {
        if (uniqueInstance == null) {
            uniqueInstance = new ReadReportView(owner);
        }
        return uniqueInstance;
    }

    public static ReadReportView getInstance(){
        return uniqueInstance;
    }
}
