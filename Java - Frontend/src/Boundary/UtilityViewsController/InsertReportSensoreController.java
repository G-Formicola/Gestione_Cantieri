package Boundary.UtilityViewsController;

import Boundary.UtilityViews.InsertReportSensoreView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class InsertReportSensoreController {

    @FXML
    private ChoiceBox<Integer> areaCantiereChoiceBox;

    @FXML
    private Button confermaButton;

    @FXML
    private Spinner<Integer> minutiSpinner;

    @FXML
    private Spinner<Integer> oreSpinner;

    @FXML
    private DatePicker rilievoDatePicker;

    @FXML
    private Spinner<Integer> rilievoSpinner;

    @FXML
    private Spinner<Integer> secondiSpinner;

    @FXML
    private ChoiceBox<Integer> sensoreChoicheBox;

    @FXML
    void caricaIdSensori(MouseEvent event) {
        Integer id_AreaCantiere = areaCantiereChoiceBox.getValue();
        clean();
        if (id_AreaCantiere != null) {
            Controller ctrl = Controller.getInstance();
            ArrayList<Integer> sensoriList = ctrl.getListaIdMontaggiArea(id_AreaCantiere);
            if (sensoriList.size()>0) {
                areaCantiereChoiceBox.setValue(id_AreaCantiere);
                ObservableList<Integer> obsListSensori = sensoreChoicheBox.getItems();
                obsListSensori.setAll(sensoriList);
                rilievoSpinner.setDisable(false);
                confermaButton.setDisable(false);
                rilievoDatePicker.setDisable(false);
                sensoreChoicheBox.setDisable(false);
                oreSpinner.setDisable(false);
                minutiSpinner.setDisable(false);
                secondiSpinner.setDisable(false);
            }
            else
            {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Non sono presenti sensori montati. Impossibile registrare un nuovo report. Registrare prima un nuovo montaggio sensore all'interno del database tramite la apposita interfaccia 'Aggiungi montaggio sensore' all'area con ID : "+ id_AreaCantiere, ButtonType.OK);
                    alert.setTitle("Caricamento fallito !");
                    alert.setHeaderText("Attenzione");
                    alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Per caricare gli ID dei sensori montati è necessario prima selezionare un area cantiere dal menu a tendina !", ButtonType.OK);
            alert.setHeaderText("Attenzione");
            alert.setTitle("Campo necessario !");
            alert.showAndWait();
        }
    }

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    @FXML
    void registraNuovoReport(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        (
                (areaCantiereChoiceBox.getValue() != null) &&
                (sensoreChoicheBox.getValue() != null) &&
                (rilievoDatePicker.getValue() != null) &&
                (oreSpinner.getValue() != null) &&
                (minutiSpinner.getValue() != null) &&
                (secondiSpinner.getValue() != null) &&
                (rilievoSpinner.getValue() != null && rilievoSpinner.getValue() != 0 )
        )
        {
            if (ctrl.insertReportSensoreIntoDB(areaCantiereChoiceBox.getValue(), sensoreChoicheBox.getValue(), rilievoDatePicker.getValue(), oreSpinner.getValue(), minutiSpinner.getValue(), secondiSpinner.getValue(), rilievoSpinner.getValue()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuovo report registrato correttamente !", ButtonType.OK);
                alert.setTitle("Successo !");
                alert.setHeaderText("Avviso");
                alert.showAndWait();
                hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore durante la registrazione. Riprovare.", ButtonType.OK);
                alert.setTitle("Inserimento fallito !");
                alert.setHeaderText("Attenzione");
                alert.showAndWait();
                clean();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Compilare correttamente tutti i campi necessari per poter proseguire", ButtonType.OK);
            alert.setHeaderText("Attenzione");
            alert.setTitle("Campi necessari !");
            alert.showAndWait();
        }
    }

    private void hide() {
        InsertReportSensoreView insertReportSensoreView = InsertReportSensoreView.getInstance();
        insertReportSensoreView.hide();
    }

    public void clean() {
        rilievoSpinner.setDisable(true);
        confermaButton.setDisable(true);
        rilievoDatePicker.setDisable(true);
        sensoreChoicheBox.setDisable(true);
        oreSpinner.setDisable(true);
        minutiSpinner.setDisable(true);
        secondiSpinner.setDisable(true);


        rilievoSpinner.setPromptText("");
        rilievoDatePicker.setValue(null);
        sensoreChoicheBox.setValue(null);
        oreSpinner.setPromptText("");
        minutiSpinner.setPromptText("");
        secondiSpinner.setPromptText("");

        setControls();
    }

    public void setControls() {
        Controller ctrl = Controller.getInstance();
        // Setting area_cantiere choicebox
        ArrayList<Integer> areeCantieriList = ctrl.getListaIdAreaCantiere();
        ObservableList<Integer> obsListAreeCantiere = areaCantiereChoiceBox.getItems();
        obsListAreeCantiere.setAll(areeCantieriList);

        // Setting Spinner
        SpinnerValueFactory<Integer> rilievoSpinnerVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        rilievoSpinner.setValueFactory(rilievoSpinnerVF);
        SpinnerValueFactory<Integer> oreSpinnerVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,23);
        oreSpinner.setValueFactory(oreSpinnerVF);
        SpinnerValueFactory<Integer> minutiSpinnerVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59);
        minutiSpinner.setValueFactory(minutiSpinnerVF);
        SpinnerValueFactory<Integer> secondiSpinnerVF = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59);
        secondiSpinner.setValueFactory(secondiSpinnerVF);
    }

}
