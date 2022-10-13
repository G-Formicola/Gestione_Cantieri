package Boundary.UtilityViewsController;

import Boundary.UtilityViews.InsertCantiereView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class InsertCantiereController {

    //Id of the admin which is using the view
    private Integer idAmm = 0 ;

    @FXML
    private TextField descrizioneTF;

    @FXML
    private DatePicker finishDatePicker;

    @FXML
    private ChoiceBox<Integer> id_ChoiceBox;

    @FXML
    private TextField localitaTF;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField titoloTF;

    @FXML
    void closeView(MouseEvent event) {
        hide();
    }

    @FXML
    void registraNuovoCantiere(MouseEvent event) {
        Controller ctrl = Controller.getInstance();
        if (
                (titoloTF.getText().length()>0) &&
                (descrizioneTF.getText().length()>0) &&
                (localitaTF.getText().length()>0) &&
                (startDatePicker.getValue() != null) &&
                (id_ChoiceBox.getValue() != null)
            ) {
                //if (finishDatePicker.getValue() != null)
                //{
                    if (ctrl.insertCantiereIntoDB(titoloTF.getText(), descrizioneTF.getText(), startDatePicker.getValue(), finishDatePicker.getValue(), localitaTF.getText(), idAmm, id_ChoiceBox.getValue())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cantiere creato correttamente", ButtonType.OK);
                        alert.showAndWait();
                        hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore durante la creazione del cantiere. Riprovare", ButtonType.OK);
                        alert.showAndWait();
                        clean();
                    }
                /*}
                else
                {
                    if (ctrl.insertCantiereIntoDB(titoloTF.getText(), descrizioneTF.getText(), startDatePicker.getValue(), localitaTF.getText(), idAmm, id_ChoiceBox.getValue())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Cantiere creato correttamente", ButtonType.OK);
                        alert.showAndWait();
                        hide();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore durante la creazione del cantiere. Riprovare", ButtonType.OK);
                        alert.showAndWait();
                        clean();
                    }
                }*/
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Compilare prima tutti i campi per poter proseguire", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void hide() {
        InsertCantiereView insertCantiereView = InsertCantiereView.getInstance();
        insertCantiereView.hide();
    }

    public void clean() {
        titoloTF.setText("");
        localitaTF.setText("");
        descrizioneTF.setText("");
        startDatePicker.setValue(null);
        finishDatePicker.setValue(null);
        id_ChoiceBox.setValue(null);
        setControls();
    }

    public void setIdAmm(Integer idAmm) {
        this.idAmm = idAmm;
    }

    public void setControls() {
        Controller ctrl = Controller.getInstance();
        ArrayList<Integer> listaCC = ctrl.getListaIdCapoCantieri();
        ObservableList<Integer> obsList = id_ChoiceBox.getItems();
        obsList.setAll(listaCC);
    }
}
