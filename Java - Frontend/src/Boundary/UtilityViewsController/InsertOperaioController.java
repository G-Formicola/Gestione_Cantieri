package Boundary.UtilityViewsController;

import Boundary.UtilityViews.InsertOperaioView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class InsertOperaioController {

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField cell1TF;

    @FXML
    private TextField cell2TF;

    @FXML
    private TextField codiceFTF;

    @FXML
    private TextField cognomeTF;

    @FXML
    private TextField nomeTF;

    @FXML
    private ChoiceBox<String> sexChoiceBox;

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    @FXML
    void registraNuovoOperaio(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if  (
                (nomeTF.getText().length()>0) &&
                        (cognomeTF.getText().length()>0) &&
                        (codiceFTF.getText().length()==16) &&
                        (cell1TF.getText().length()>0) &&
                        (birthDatePicker.getValue() != null) &&
                        (sexChoiceBox.getValue() != null)
        )
        {
            if (ctrl.insertOperaioIntoDB(nomeTF.getText(), cognomeTF.getText(), codiceFTF.getText(), cell1TF.getText(), cell2TF.getText(), birthDatePicker.getValue(), sexChoiceBox.getValue()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuovo Operaio inserito correttamente.", ButtonType.OK);
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

    public void clean() {
        nomeTF.setText("");
        cognomeTF.setText("");
        cell1TF.setText("");
        cell2TF.setText("");
        codiceFTF.setText("");
        birthDatePicker.setValue(null);
        sexChoiceBox.setValue(null);
    }

    public void hide() {
        InsertOperaioView insertOperaioView = InsertOperaioView.getInstance();
        insertOperaioView.hide();
    }

    public void setControls() {
        ArrayList<String> sexList = new ArrayList<String>();
        sexList.add("M");
        sexList.add("F");
        ObservableList<String> obsList = sexChoiceBox.getItems();
        obsList.setAll(sexList);
    }

}
