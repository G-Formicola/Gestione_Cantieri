package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ReadStatCantView;
import Control.Controller;
import Entity.Cantiere;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ReadStatCantController {

    @FXML
    private DatePicker dataFine;

    @FXML
    private DatePicker dataInizio;

    @FXML
    private Label descrizione;

    @FXML
    private Label idCantiere;

    @FXML
    private Label localita;

    @FXML
    private Label numeroAree;

    @FXML
    private Label numeroOperaio;

    @FXML
    private Label numeroReport;

    @FXML
    private Label numeroSensori;

    @FXML
    private Label titolo;

    @FXML
    void closeView(MouseEvent event) {
        ReadStatCantView readStatCantView = ReadStatCantView.getInstance();
        readStatCantView.hide();
    }

    public void setControls(Integer idCantiereGestito) {
        Controller ctrl = Controller.getInstance();
        Cantiere cantiere = ctrl.getCantiereById(idCantiereGestito);
        titolo.setText(cantiere.getTitolo());
        descrizione.setText(cantiere.getDescrizione());
        idCantiere.setText(cantiere.getIdCantiere().toString());
        localita.setText(cantiere.getLocalita());
        dataInizio.setValue(cantiere.getStartDate());
        dataFine.setValue(cantiere.getFinishDate());

        Integer nOperai = ctrl.getListaIdOperaiConRuolo(idCantiereGestito).size();
        numeroOperaio.setText(nOperai.toString());
        Integer nSensori = ctrl.getNumSensori(idCantiereGestito);
        numeroSensori.setText(nSensori.toString());
        Integer nReport = ctrl.getNumReport(idCantiereGestito);
        numeroReport.setText(nReport.toString());
        Integer nAree = ctrl.getDatiAreeCantiere(idCantiereGestito).size();
        numeroAree.setText(nAree.toString());
    }
}
