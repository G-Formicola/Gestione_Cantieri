package Boundary.PanelController;

import Control.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AdminPanelController {

    private Controller ctrl ;
    private Integer idAdmin ;

    @FXML
    private Label idLabel;

    @FXML
    private ListView<String> listViewCantieri;

    @FXML // OK
    void executeLogout(MouseEvent event) {
        ctrl.eseguiLogout();
    }

    @FXML // OK
    void newAdmin(MouseEvent event) {
        try {
            ctrl.openInsertAdminView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nell'apertura del pannello. Riprovare", ButtonType.OK);
            avviso.setTitle("Errore !");
            avviso.setHeaderText("Attenzione");
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newCapoCantiere(MouseEvent event) {
        try {
            ctrl.openInsertCapocantiereView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nell'apertura del pannello. Riprovare", ButtonType.OK);
            avviso.setTitle("Errore !");
            avviso.setHeaderText("Attenzione");
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newOperaio(MouseEvent event) {
        try {
            ctrl.openInsertOperaioView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nell'apertura del pannello. Riprovare", ButtonType.OK);
            avviso.setTitle("Errore !");
            avviso.setHeaderText("Attenzione");
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newCantiere(MouseEvent event) {
        try {
            ctrl.openInsertCantiereView(idAdmin);
            setListViewCantieri();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nel inserimento di un nuovo cantiere. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newSensore(MouseEvent event) {
        try {
            ctrl.openInsertSensoreView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nel inserimento di un nuovo sensore. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newAreaCantiere(MouseEvent event) {
        try {
            ctrl.openInsertAreaCantiereView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nel inserimento di una nuova area cantiere. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void insertReport(MouseEvent event) {
        try {
            ctrl.openInsertReportSensoreView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella registrazione di un nuovo Report. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newMontaggioSensore(MouseEvent event) {
        try {
            ctrl.openInsertMontaggioSensoreView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella registrazione di un nuovo montaggio. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newRuoloOperaio(MouseEvent event) {
        try {
            ctrl.openInsertRuoloView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nell'assegnazione di un ruolo ad un operaio. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void modifySoglia(MouseEvent event) {
        try {
            ctrl.openModifySogliaView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella modifica della soglia. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void assegnaCantiere(MouseEvent event) {
        try {
            ctrl.openModifyGestioneView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella modifica della gestione. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void readReport(MouseEvent event) {
        try {
            ctrl.openReadReportView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella lettura dei dati registrati dai sensori. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void openStatistiche(MouseEvent event) {
        String cantiereScelto = listViewCantieri.getSelectionModel().getSelectedItem();
        if (cantiereScelto!=null) {
            try {
                ctrl.openReadStatView(cantiereScelto);
            } catch (IOException e) {
                Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nel visualizzare le statistiche inerenti a : " + cantiereScelto + ". Riprovare", ButtonType.OK);
                avviso.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public void setMainController(Controller ctrl) {
        this.ctrl = ctrl ;
    }

    public void setIdAdmin(Integer id){
        idLabel.setText(id.toString());
        idAdmin = id ;
    }

    public void setListViewCantieri(){
        listViewCantieri.getItems().setAll(ctrl.getListaNomiCantieriInCorso());
    }

}
