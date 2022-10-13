package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ReadStatAreeView;
import Control.Controller;
import Entity.ViewsEntities.AreeDBView;
import Entity.ViewsEntities.ReportDBView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReadStatAreeController {

    @FXML
    private TableColumn<AreeDBView, String> descrizioneColumn;

    @FXML
    private TableColumn<AreeDBView, Integer> idAreaColumn;

    @FXML
    private TableColumn<AreeDBView, String> nomeColumn;

    @FXML
    private TableView<AreeDBView> reportTableView;

    @FXML
    private TableColumn<AreeDBView, Integer> responsabileColumn;

    @FXML
    private Label titleLabel;

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    public void clean() {
        titleLabel.setText("Lista delle diverse aree cantiere appartenenti al cantiere con ID : ");
        reportTableView.getItems().setAll(new ArrayList<AreeDBView>());
    }

    private void hide() {
        ReadStatAreeView readStatAreeView = ReadStatAreeView.getInstance();
        readStatAreeView.hide();
    }

    public void setControls(Integer idCantiereGestito) {
        String titolo = "Lista delle diverse aree cantiere appartenenti al cantiere con ID : " + idCantiereGestito.toString() ;
        titleLabel.setText(titolo);

        Controller ctrl = Controller.getInstance();
        ArrayList<AreeDBView> areeDBView = ctrl.getDatiAreeCantiere(idCantiereGestito);
        reportTableView.getItems().setAll(areeDBView);

        idAreaColumn.setCellValueFactory(new PropertyValueFactory<AreeDBView,Integer>("idArea"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<AreeDBView,String>("nomeZona"));
        descrizioneColumn.setCellValueFactory(new PropertyValueFactory<AreeDBView,String>("descrizione"));
        responsabileColumn.setCellValueFactory(new PropertyValueFactory<AreeDBView,Integer>("idResponsabile"));

    }
}
