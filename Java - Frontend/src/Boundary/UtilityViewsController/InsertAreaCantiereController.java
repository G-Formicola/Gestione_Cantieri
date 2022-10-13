package Boundary.UtilityViewsController;

import Boundary.UtilityViews.InsertAreaCantiereView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class InsertAreaCantiereController {

    @FXML
    private ChoiceBox<Integer> cantiereChoiceBox;

    @FXML
    private Button caricaIDButton;

    @FXML
    private TextField nomeZonaTF;

    @FXML
    private TextField descrizioneTF;

    @FXML
    private ChoiceBox<Integer> operaioChoiceBox;

    @FXML
    private Button confermaButton;

    @FXML
    void caricaIdOperai(MouseEvent event) {
        Integer id_Cantiere = cantiereChoiceBox.getValue();
        clean();
        if (id_Cantiere != null) {
            Controller ctrl = Controller.getInstance();
            ArrayList<Integer> operaiList = ctrl.getListaIdOperaiConRuolo(id_Cantiere);
            if (operaiList.size()>0) {
                cantiereChoiceBox.setValue(id_Cantiere);
                ObservableList<Integer> obsListOperai = operaioChoiceBox.getItems();
                obsListOperai.setAll(operaiList);
                operaioChoiceBox.setDisable(false);
                confermaButton.setDisable(false);
                descrizioneTF.setDisable(false);
                nomeZonaTF.setDisable(false);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Non sono presenti operai a cui assegnare la responsabilità dell'area cantiere. Impossibile inserire una nuova area cantiere. Inserire prima un nuovo operaio per il cantiere scelto.", ButtonType.OK);
                alert.setTitle("Caricamento fallito !");
                alert.setHeaderText("Attenzione");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Per caricare gli ID degli operai è necessario prima selezionare un cantiere dal menu a tendina !", ButtonType.OK);
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
    void registraNuovaAC(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if
        (       (cantiereChoiceBox.getValue() != null) &&
                (operaioChoiceBox.getValue() != null) &&
                (nomeZonaTF.getText().length()>0)
        )
        {
            if (ctrl.insertAreaCantiereIntoDB(cantiereChoiceBox.getValue(), operaioChoiceBox.getValue(), nomeZonaTF.getText(), descrizioneTF.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuovo area cantiere inserita correttamente.", ButtonType.OK);
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
        InsertAreaCantiereView insertAreaCantiereView = InsertAreaCantiereView.getInstance();
        insertAreaCantiereView.hide();
    }

    public void clean() {
        operaioChoiceBox.setDisable(true);
        confermaButton.setDisable(true);
        descrizioneTF.setDisable(true);
        nomeZonaTF.setDisable(true);
        operaioChoiceBox.setValue(null);
        cantiereChoiceBox.setValue(null);
        descrizioneTF.setText("");
        nomeZonaTF.setText("");
        setControls();

    }

    public void setControls() {
        Controller ctrl = Controller.getInstance();
        ArrayList<Integer> cantieriList = ctrl.getListaIdCantieriInCorso();
        ObservableList<Integer> obsListCantiere = cantiereChoiceBox.getItems();
        obsListCantiere.setAll(cantieriList);
    }

}
