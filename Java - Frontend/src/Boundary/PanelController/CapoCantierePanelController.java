package Boundary.PanelController;

import Control.Controller;
import Entity.Cantiere;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CapoCantierePanelController {

    private Controller ctrl ;

    private Integer idCantiereGestito;

    @FXML
    private Label idLabel;

    @FXML
    private Label labelCantiereID;

    @FXML
    private Label labelTitoloCantiere;

    @FXML
    private Label responsabLabel;

    @FXML
    private Label titoloL;

    @FXML
    private Label idL;

    @FXML
    private ListView<Integer> listViewOperai;

    @FXML // OK
    void executeLogout(MouseEvent event) {
        ctrl.eseguiLogout();
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
            setListViewOperai();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nell'assegnazione di un ruolo ad un operaio. Riprovare", ButtonType.OK);
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
    void modifyMansione(MouseEvent event) {
        try {
            ctrl.openModifyMansioneView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella lettura dei dati sulle mansione dei vari ruoli. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void newRespons(MouseEvent event) {
        try {
            ctrl.openModifyRespView();
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella lettura delle aree registrate. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void readStatAree(MouseEvent event) {
        try {
            ctrl.openReadStatAreeView(idCantiereGestito);
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella lettura delle aree registrate. Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void readStatCant(MouseEvent event) {
        try {
            ctrl.openReadStatCantView(idCantiereGestito);
        } catch (IOException e) {
            Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nella lettura delle statistiche riguardanti il cantiere con ID : " + idCantiereGestito.toString() +" . Riprovare", ButtonType.OK);
            avviso.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML // OK
    void openStatistiche(MouseEvent event) {
        Integer operaioScelto = listViewOperai.getSelectionModel().getSelectedItem();
        if (operaioScelto!=null) {
            try {
                ctrl.openReadStatView(operaioScelto, idCantiereGestito);
            } catch (IOException e) {
                Alert avviso = new Alert(Alert.AlertType.ERROR, "Si è verificato un errore nel visualizzare le informazioni inerenti all'operaio con ID : " + operaioScelto.toString() + ". Riprovare", ButtonType.OK);
                avviso.showAndWait();
                e.printStackTrace();
            }
        }
    }

    public void setListViewOperai(){
        listViewOperai.getItems().setAll(ctrl.getListaIdOperaiConRuolo(idCantiereGestito));
    }

    public void setMainController(Controller ctrl) {
        this.ctrl = ctrl ;
    }

    public void setIdLabel(Integer idCC) {
        idLabel.setText(idCC.toString());
    }

    public void setCantiereLabels(Integer idCC) {
        Cantiere cantiere = ctrl.getCantiereByResponsabile(idCC);
        if (cantiere != null)
        {
            labelTitoloCantiere.setText(cantiere.getTitolo());
            labelCantiereID.setText(cantiere.getIdCantiere().toString());
            titoloL.setVisible(true);
            idL.setVisible(true);
            responsabLabel.setText("Cantiere sotto la propria responsabilità");
            listViewOperai.setDisable(false);
            idCantiereGestito = cantiere.getIdCantiere();

        }
        else
        {
            labelTitoloCantiere.setText("");
            labelCantiereID.setText("");
            titoloL.setVisible(false);
            idL.setVisible(false);
            responsabLabel.setText("Nessun cantiere sotto la propria responsabilità attualmente");
            listViewOperai.setDisable(true);
        }
    }

}


