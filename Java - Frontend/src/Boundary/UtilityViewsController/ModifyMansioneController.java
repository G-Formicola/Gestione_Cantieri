package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ModifyMansioneView;
import Boundary.UtilityViews.ReadInfoOperaioView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ModifyMansioneController {

    private ModifyMansioneView modifyMansioneView ;

    @FXML
    private ChoiceBox<String> mansioneChoicheBox;

    @FXML
    private ChoiceBox<Integer> ruoloChoiceBox;

    @FXML
    private Button confermaButton;

    @FXML
    void closeView(MouseEvent event) {
        modifyMansioneView.hide();
    }


    @FXML
    void registraNuovaMansione(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        ( (ruoloChoiceBox.getValue() != null) && (mansioneChoicheBox.getValue() != null) )
        {
            if (ctrl.modifyMansione(ruoloChoiceBox.getValue(), mansioneChoicheBox.getValue()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuova mansione registrata correttamente.", ButtonType.OK);
                alert.setTitle("Successo !");
                alert.setHeaderText("Avviso");
                alert.showAndWait();
                ReadInfoOperaioView.getInstance().refreshMansione();
                modifyMansioneView.hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore durante la modifica. Riprovare.", ButtonType.OK);
                alert.setTitle("Inserimento fallito !");
                alert.setHeaderText("Attenzione");
                alert.showAndWait();
                ruoloChoiceBox.setValue(null);
                mansioneChoicheBox.setValue(null);
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

    public void setControls(ModifyMansioneView modMansioneView) {
        modifyMansioneView = modMansioneView;
        Controller ctrl = Controller.getInstance();
        ArrayList<String> mansioneList = new ArrayList<String>();
        mansioneList.add("Operatore");
        mansioneList.add("Carpentiere");
        mansioneList.add("Elettricista");
        mansioneList.add("Ingegnere");
        ObservableList<String> obsList = mansioneChoicheBox.getItems();
        obsList.setAll(mansioneList);

        ArrayList<Integer> ruoliList = ctrl.getListaIdRuolo();
        if (ruoliList.size()>0) {
            ObservableList<Integer> obsList2 = ruoloChoiceBox.getItems();
            obsList2.setAll(ruoliList);
        }else{
            ruoloChoiceBox.setDisable(true);
            mansioneChoicheBox.setDisable(true);
            confermaButton.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Non ci sono operai a cui modificare la mansione assegnata. Inserire direttamente un nuovo ruolo invece di modificarne uno esistente.", ButtonType.OK);
            alert.setTitle("Errore !");
            alert.setHeaderText("Attenzione");
            alert.showAndWait();
            modifyMansioneView.hide();
        }

    }

    public void setIdRuolo(Integer id_ruolo) {
        ruoloChoiceBox.setDisable(true);
        if (id_ruolo != null) {
            ruoloChoiceBox.setValue(id_ruolo);
        }else{
            ruoloChoiceBox.setValue(0);
            confermaButton.setDisable(true);
        }

    }
}
