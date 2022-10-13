package Boundary.UtilityViewsController;

import Boundary.UtilityViews.InsertRuoloView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class InsertRuoloController {

    @FXML
    private ChoiceBox<Integer> cantiereChoiceBox;

    @FXML
    private TextField descrizioneTF;

    @FXML
    private ChoiceBox<String> mansioneChoicheBox;

    @FXML
    private ChoiceBox<Integer> operaioChoiceBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    @FXML
    void registraNuovoRuolo(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        (       (cantiereChoiceBox.getValue() != null) &&
                (operaioChoiceBox.getValue() != null) &&
                (mansioneChoicheBox.getValue() != null) &&
                (startDatePicker.getValue() != null)
        )
        {
            if (ctrl.insertRuoloIntoDB(mansioneChoicheBox.getValue(), startDatePicker.getValue(), operaioChoiceBox.getValue(), cantiereChoiceBox.getValue(), descrizioneTF.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuovo Ruolo assegnato correttamente.", ButtonType.OK);
                alert.setTitle("Successo !");
                alert.setHeaderText("Avviso");
                alert.showAndWait();
                hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore durante l'inserimento. Riprovare.", ButtonType.OK);
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
        InsertRuoloView insertRuoloView = InsertRuoloView.getInstance();
        insertRuoloView.hide();
    }

    public void clean() {
        cantiereChoiceBox.setValue(null);
        descrizioneTF.setText("");
        mansioneChoicheBox.setValue(null);
        operaioChoiceBox.setValue(null);
        startDatePicker.setValue(null);
        setControls();
    }

    public void setControls() {
        // Setting the choice boxes
        ArrayList<String> mansioneList = new ArrayList<String>();
        mansioneList.add("Operatore");
        mansioneList.add("Carpentiere");
        mansioneList.add("Elettricista");
        mansioneList.add("Ingegnere");
        ObservableList<String> obsList = mansioneChoicheBox.getItems();
        obsList.setAll(mansioneList);

        Controller ctrl = Controller.getInstance();

        ArrayList<Integer> operaiList = ctrl.getListaIdOperai();
        ObservableList<Integer> obsListOperai = operaioChoiceBox.getItems();
        obsListOperai.setAll(operaiList);

        ArrayList<Integer> cantieriList = ctrl.getListaIdCantieriInCorso();
        ObservableList<Integer> obsListCantiere = cantiereChoiceBox.getItems();
        obsListCantiere.setAll(cantieriList);

    }


}
