package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ModifyRespView;
import Control.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class ModifyRespController {

    @FXML
    private ChoiceBox<Integer> areaChoiceBox;

    @FXML
    private ChoiceBox<Integer> operaioChoicheBox;

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    @FXML
    void registraNuovoResponsabile(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        ( (areaChoiceBox.getValue() != null) && (operaioChoicheBox.getValue() != null) )
        {
            if (ctrl.modifyResponsability(areaChoiceBox.getValue(), operaioChoicheBox.getValue()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuova responsabilità registrata correttamente.", ButtonType.OK);
                alert.setTitle("Successo !");
                alert.setHeaderText("Avviso");
                alert.showAndWait();
                hide();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore durante la modifica. Riprovare.", ButtonType.OK);
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
        areaChoiceBox.setValue(null);
        operaioChoicheBox.setValue(null);
    }

    private void hide() {
        ModifyRespView modifyRespView = ModifyRespView.getInstance();
        modifyRespView.hide();
    }

    public void setControls() {
        Controller ctrl = Controller.getInstance();

        ArrayList<Integer> areeCantieriList = ctrl.getListaIdAreaCantiere();
        areaChoiceBox.getItems().setAll(areeCantieriList);

        ArrayList<Integer> operaiList = ctrl.getListaIdOperai();
        operaioChoicheBox.getItems().setAll(operaiList);
    }
}
