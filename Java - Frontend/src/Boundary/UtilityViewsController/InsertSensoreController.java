package Boundary.UtilityViewsController;

import Boundary.UtilityViews.InsertSensoreView;
import Control.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class InsertSensoreController {

    @FXML
        private ChoiceBox<String> sensorChoiceBox;

        @FXML
        private Spinner<Integer> sogliaSpinner;

        @FXML
        void closeView(MouseEvent event) {
            hide();
        }

        @FXML
        void registraNuovoSensore(MouseEvent event) {
            Controller ctrl = Controller.getInstance();
            if
            ( (sensorChoiceBox.getValue() != null) && (sogliaSpinner.getValue() != 0) )
            {
                if (ctrl.insertSensoreIntoDB(sensorChoiceBox.getValue(), sogliaSpinner.getValue()))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Nuovo Sensore registrato correttamente.", ButtonType.OK);
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
            sensorChoiceBox.setValue(null);
            sogliaSpinner.getValueFactory().setValue(0);
        }

        public void hide() {
            InsertSensoreView insertSensoreView = InsertSensoreView.getInstance();
            insertSensoreView.hide();
        }

        public void setControls() {
            // Setting the choice box
            ArrayList<String> sensorList = new ArrayList<String>();
            sensorList.add("Sensore_Rumore");
            sensorList.add("Sensore_Gas");
            ObservableList<String> obsList = sensorChoiceBox.getItems();
            obsList.setAll(sensorList);

            // Setting the spinner
            SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);
            sogliaSpinner.setValueFactory(spinnerValueFactory);

        }


}
