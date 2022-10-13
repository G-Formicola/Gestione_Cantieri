package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ModifyGestioneView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ModifyGestioneController {

    @FXML
    private ChoiceBox<Integer> cantiereChoiceBox;

    @FXML
    private ChoiceBox<Integer> capoCantiereChoicheBox;

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    @FXML
    void registraNuovaGestione(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        ( (cantiereChoiceBox.getValue() != null) && (capoCantiereChoicheBox.getValue() != null) )
        {
            if (ctrl.modifyGestione(cantiereChoiceBox.getValue(), capoCantiereChoicheBox.getValue()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuova gestione cantiere registrata correttamente.", ButtonType.OK);
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
        cantiereChoiceBox.setValue(null);
        capoCantiereChoicheBox.setValue(null);
        setControls();
    }

    public void hide() {
        ModifyGestioneView modifyGestioneView = ModifyGestioneView.getInstance();
        modifyGestioneView.hide();
    }

    public void setControls() {
        // Setting the choice box
        Controller ctrl = Controller.getInstance();
        ArrayList<Integer> cantieriList = ctrl.getListaIdCantieriInCorso();
        ObservableList<Integer> obsList1 = cantiereChoiceBox.getItems();
        obsList1.setAll(cantieriList);

        ArrayList<Integer> capoCantList = ctrl.getListaIdCapoCantieri();
        ObservableList<Integer> obsList2 = capoCantiereChoicheBox.getItems();
        obsList2.setAll(capoCantList);
    }

}
