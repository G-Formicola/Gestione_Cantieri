package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ReadReportView;
import Control.Controller;
import Entity.ViewsEntities.ReportDBView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReadReportController {

    @FXML
    private ChoiceBox<String> criterioChoicheBox;

    @FXML
    private TableView<ReportDBView> reportTableView;

    @FXML
    private TableColumn<ReportDBView, LocalDate> dataColumn;

    @FXML
    private TableColumn<ReportDBView, Integer> idSensoreColumn;

    @FXML
    private TableColumn<ReportDBView, Integer> idReportColumn;

    @FXML
    private TableColumn<ReportDBView, Integer> idZonaColumn;

    @FXML
    private TableColumn<ReportDBView, String> nomeZonaColumn;

    @FXML
    private TableColumn<ReportDBView, Time> oreColumn;

    @FXML
    private TableColumn<ReportDBView, Integer> rilievoColumn;

    @FXML
    private TableColumn<ReportDBView, Integer> sogliaColumn;

    @FXML
    private TableColumn<ReportDBView, String> tipoSensColumn;

    @FXML
    void aggiornaOrdinamento(MouseEvent event) {
        String criterio = criterioChoicheBox.getValue();
        if (criterio != null)
            fillTable(criterio);
    }

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    public void hide() {
        ReadReportView readReportView = ReadReportView.getInstance();
        readReportView.hide();
    }

    public void setControls() {
        ArrayList<String> criterioList = new ArrayList<String>();
        criterioList.add("Data_Rilievo Asc");
        criterioList.add("Id_Report_Sensore Asc");
        criterioList.add("Id_Sensore Asc");
        criterioList.add("Tipo Asc");
        criterioList.add("Rilievo Asc");
        criterioList.add("Data_Rilievo Desc");
        criterioList.add("Id_Report_Sensore Desc");
        criterioList.add("Id_Sensore Desc");
        criterioList.add("Tipo Desc");
        criterioList.add("Rilievo Desc");
        ObservableList<String> obsList = criterioChoicheBox.getItems();
        obsList.setAll(criterioList);
        fillTable("Data_Rilievo Desc");
    }

    public void fillTable(String sortType){
        Controller ctrl = Controller.getInstance();
        ArrayList<ReportDBView> reportDBView = ctrl.getDatiReport(sortType);
        reportTableView.getItems().setAll(reportDBView);

        idReportColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,Integer>("idReport"));
        tipoSensColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,String>("tipo"));
        idSensoreColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,Integer>("idSensore"));
        sogliaColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,Integer>("soglia"));
        nomeZonaColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,String>("nomeZona"));
        idZonaColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,Integer>("idZona"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,LocalDate>("dataRilievo"));
        oreColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,Time>("oreRilievo"));
        rilievoColumn.setCellValueFactory(new PropertyValueFactory<ReportDBView,Integer>("rilievo"));


    }

    public void clean() {
        criterioChoicheBox.setValue(null);
        fillTable("Data_Rilievo Desc");
    }
}
