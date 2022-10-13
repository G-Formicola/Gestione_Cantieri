package Boundary.UtilityViewsController;

import Boundary.UtilityViews.InsertMontaggioSensoreView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class InsertMontaggioSensoreController {

    @FXML
    private ChoiceBox<Integer> areaCantiereChoiceBox;

    @FXML
    private Button caricaIDButton;

    @FXML
    private Button confermaButton;

    @FXML
    private DatePicker montaggioDatePicker;

    @FXML
    private ChoiceBox<Integer> operatoreChoiceBox;

    @FXML
    private ChoiceBox<Integer> sensoreChoicheBox;

    @FXML
    void caricaIdOperai(MouseEvent event) {
        Integer id_AreaCantiere = areaCantiereChoiceBox.getValue();
        clean();
        if (id_AreaCantiere != null) {
            Controller ctrl = Controller.getInstance();
            ArrayList<Integer> operatoriList = ctrl.getListaIdOperatori(id_AreaCantiere);
            ArrayList<Integer> sensoriList = ctrl.getListaIdSensoriArea();
            if (operatoriList.size()>0 && sensoriList.size()>0) {
                areaCantiereChoiceBox.setValue(id_AreaCantiere);
                ObservableList<Integer> obsListOperatori = operatoreChoiceBox.getItems();
                obsListOperatori.setAll(operatoriList);
                ObservableList<Integer> obsListSensori = sensoreChoicheBox.getItems();
                obsListSensori.setAll(sensoriList);
                operatoreChoiceBox.setDisable(false);
                confermaButton.setDisable(false);
                montaggioDatePicker.setDisable(false);
                sensoreChoicheBox.setDisable(false);
            }
            else
            {
                if (sensoriList.size()>0)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Non sono presenti operai a cui assegnare la responsabilità dell'area cantiere. Impossibile registrare un nuovo montaggio. Inserire prima un nuovo operaio con ruolo Operatore per il cantiere scelto con ID : "+ctrl.getIdCantiere(id_AreaCantiere).toString(), ButtonType.OK);
                    alert.setTitle("Caricamento fallito !");
                    alert.setHeaderText("Attenzione");
                    alert.showAndWait();
                }
                if (operatoriList.size()>0)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Non sono presenti sensori liberi. Impossibile registrare un nuovo montaggio. Inserire prima un nuovo sensore all'interno del database tramite la apposita interfaccia 'Aggiungi sensore'.", ButtonType.OK);
                    alert.setTitle("Caricamento fallito !");
                    alert.setHeaderText("Attenzione");
                    alert.showAndWait();
                }
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Per caricare gli ID degli operatori è necessario prima selezionare un area cantiere dal menu a tendina !", ButtonType.OK);
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
    void registraNuovoMontaggio(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        (       (areaCantiereChoiceBox.getValue() != null) &&
                (montaggioDatePicker.getValue() != null) &&
                (operatoreChoiceBox.getValue() != null) &&
                (sensoreChoicheBox.getValue() != null)
        )
        {
            if (ctrl.insertMontaggioSensoreIntoDB(areaCantiereChoiceBox.getValue(), operatoreChoiceBox.getValue(), sensoreChoicheBox.getValue(), montaggioDatePicker.getValue()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuovo montaggio registrato correttamente.", ButtonType.OK);
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

    public void hide() {
        InsertMontaggioSensoreView insertMontaggioSensoreView = InsertMontaggioSensoreView.getInstance();
        insertMontaggioSensoreView.hide();
    }

    public void clean() {
        operatoreChoiceBox.setDisable(true);
        confermaButton.setDisable(true);
        montaggioDatePicker.setDisable(true);
        sensoreChoicheBox.setDisable(true);
        operatoreChoiceBox.setValue(null);
        areaCantiereChoiceBox.setValue(null);
        montaggioDatePicker.setValue(null);
        sensoreChoicheBox.setValue(null);
        setControls();
    }

    public void setControls() {
        Controller ctrl = Controller.getInstance();
        ArrayList<Integer> areeCantieriList = ctrl.getListaIdAreaCantiere();
        ObservableList<Integer> obsListAreeCantiere = areaCantiereChoiceBox.getItems();
        obsListAreeCantiere.setAll(areeCantieriList);
    }
}
