package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ModifySogliaView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ModifySogliaController {

    @FXML
    private ChoiceBox<Integer> sensorChoiceBox;

    @FXML
    private Spinner<Integer> sogliaSpinner;

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    @FXML
    void registraNuovaSoglia(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        ( (sensorChoiceBox.getValue() != null) && (sogliaSpinner.getValue() != null) )
        {
            if (ctrl.modifySoglia(sensorChoiceBox.getValue(), sogliaSpinner.getValue()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuova soglia registrata correttamente.", ButtonType.OK);
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

    public void clean() {
        sensorChoiceBox.setValue(null);
        sogliaSpinner.getValueFactory().setValue(0);
    }

    public void hide() {
        ModifySogliaView modifySogliaView = ModifySogliaView.getInstance();
        modifySogliaView.hide();
    }

    public void setControls() {
        // Setting the choice box
        Controller ctrl = Controller.getInstance();
        ArrayList<Integer> sensorList = ctrl.getListaIdSensoriArea();
        ObservableList<Integer> obsList = sensorChoiceBox.getItems();
        obsList.setAll(sensorList);

        // Setting the spinner
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
        sogliaSpinner.setValueFactory(spinnerValueFactory);
    }

}
