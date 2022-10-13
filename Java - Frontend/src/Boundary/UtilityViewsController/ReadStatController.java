package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ReadStatView;
import Control.Controller;
import Entity.Cantiere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class ReadStatController {

    @FXML
    private Label dataFine;

    @FXML
    private Label dataInizio;

    @FXML
    private Label descrizione;

    @FXML
    private Label idAmm;

    @FXML
    private Label idCantiere;

    @FXML
    private Label idCapoC;

    @FXML
    private Label localita;

    @FXML
    private Label titolo;

    @FXML
    private Label totaleOperai;

    @FXML
    private Label nOperatori;

    @FXML
    private Label nElettricisti;

    @FXML
    private Label nIngegneri;

    @FXML
    private Label nCarpentieri;

    @FXML
    private Label numSensori;

    @FXML
    private Label numReport;

    @FXML
    private PieChart operaiChart;

    private PieChart.Data carpentieriSlice;
    private PieChart.Data operatoriSlice;
    private PieChart.Data ingegneriSlice;
    private PieChart.Data elettricistiSlice;


    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    private void hide() {
        ReadStatView readStatView = ReadStatView.getInstance();
        readStatView.hide();
    }

    public void setControls(String nomeCantiere) {

        Controller ctrl = Controller.getInstance();
        Cantiere cantiere = ctrl.getCantiereByNome(nomeCantiere);
        Integer id_cant = cantiere.getIdCantiere();
        idCantiere.setText(id_cant.toString());
        idAmm.setText(cantiere.getIdAdmin().toString());
        idCapoC.setText(cantiere.getIdCapoC().toString());
        titolo.setText(cantiere.getTitolo());
        descrizione.setText(cantiere.getDescrizione());
        localita.setText(cantiere.getLocalita());
        if (cantiere.getStartDate()!=null)
            dataInizio.setText(cantiere.getStartDate().toString());
        else
            dataInizio.setText("Non Specificato");

        if (cantiere.getFinishDate()!=null)
            dataFine.setText(cantiere.getFinishDate().toString());
        else
            dataFine.setText("Non Specificato");

        Double numOperatori = ctrl.getNumOperatori(id_cant);
        Double numIngegneri = ctrl.getNumIng(id_cant);
        Double numElettricisti = ctrl.getNumElett(id_cant);
        Double numCarpentieri = ctrl.getNumCarpentieri(id_cant);
        Double totale = numCarpentieri + numOperatori + numElettricisti + numIngegneri;

        Integer nSensori = ctrl.getNumSensori(id_cant);
        Integer nReport = ctrl.getNumReport(id_cant);

        nOperatori.setText(String.valueOf(numOperatori.intValue()));
        nIngegneri.setText(String.valueOf(numIngegneri.intValue()));
        nElettricisti.setText(String.valueOf(numElettricisti.intValue()));
        nCarpentieri.setText(String.valueOf(numCarpentieri.intValue()));

        carpentieriSlice.setPieValue(numCarpentieri);
        operatoriSlice.setPieValue(numOperatori);
        ingegneriSlice.setPieValue(numIngegneri);
        elettricistiSlice.setPieValue(numElettricisti);
        totaleOperai.setText(String.valueOf(totale.intValue()));


        numSensori.setText(nSensori.toString());
        numReport.setText(nReport.toString());
    }

    public void buildPie(){
        ArrayList<PieChart.Data> listaSlices = new ArrayList<PieChart.Data>();
        carpentieriSlice = new PieChart.Data("Carpentieri",1);
        operatoriSlice = new PieChart.Data("Operatori",2);
        ingegneriSlice = new PieChart.Data("Ingegneri",4);
        elettricistiSlice = new PieChart.Data("Elettricisti",8);
        listaSlices.add(carpentieriSlice);
        listaSlices.add(operatoriSlice);
        listaSlices.add(ingegneriSlice);
        listaSlices.add(elettricistiSlice);

        ObservableList<PieChart.Data> slices = FXCollections.observableList(listaSlices);

        operaiChart.setData(slices);
        operaiChart.setLabelsVisible(false);
    }

}
