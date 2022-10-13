package Boundary.UtilityViewsController;

import Boundary.UtilityViews.ReadInfoOperaioView;
import Control.Controller;
import Entity.Operaio;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class ReadInfoOperaioController {

    private Integer operaioScelto ;
    private Integer cantiereLavori;
    private Integer idRuolo;

    @FXML
    private Button modifyRuoloButton;

    @FXML
    private ListView<String> areeListView;

    @FXML
    private Label cellulare;

    @FXML
    private Label cellulare2;

    @FXML
    private Label codiceFiscale;

    @FXML
    private Label cognome;

    @FXML
    private DatePicker data_n;

    @FXML
    private Label idOperaio;

    @FXML
    private Label noAreeLabel;

    @FXML
    private Label nome;

    @FXML
    private Label ruolo;

    @FXML
    private Label sesso;


    @FXML
    void closeView(MouseEvent event) {
        ReadInfoOperaioView.getInstance().hide();
    }

    @FXML
    void modifyRuolo(MouseEvent event) {
        try {
            Controller.getInstance().openModifyMasioneView(idRuolo);
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Si è verificato un errore nella modifica della mansione. Riporvare dal pannello principale.", ButtonType.OK);
            alert.setTitle("Errore !");
            alert.setHeaderText("Attenzione");
            alert.showAndWait();
        }
    }

    public void setControls(Integer operaioScelto, Integer cantiereLavori) {
        Controller ctrl = Controller.getInstance();
        this.cantiereLavori = cantiereLavori;
        this.operaioScelto = operaioScelto ;
        idRuolo = ctrl.getIdRuolo(operaioScelto,cantiereLavori);


        Operaio operaio = ctrl.getOperaioById(operaioScelto);
        nome.setText(operaio.getNome());
        cognome.setText(operaio.getCognome());
        sesso.setText(operaio.getSesso());
        data_n.setValue(operaio.getData_n());
        codiceFiscale.setText(operaio.getCodiceFiscale());
        cellulare.setText(operaio.getCellulare());
        cellulare2.setText(operaio.getCellulare2());
        idOperaio.setText(operaio.getId_Operaio().toString());

        setMansioneLabel(operaioScelto,cantiereLavori);



        fillListView();



    }

    public void setMansioneLabel(Integer operaioScelto, Integer cantiereLavori){
        String mansione = Controller.getInstance().getMansioneOperaio(operaioScelto,cantiereLavori);
        if (mansione.length()>0) {
            ruolo.setText(mansione);
            modifyRuoloButton.setDisable(false);
        }else{
            ruolo.setText("Nessun Ruolo");
            modifyRuoloButton.setDisable(true);
        }
    }

    public void refreshMansione(){
        setMansioneLabel(operaioScelto,cantiereLavori);
    }

    public void fillListView(){
        ArrayList<String> array = Controller.getInstance().getListaAreeGestite(operaioScelto,cantiereLavori);
        if (array.size()>0) {
            areeListView.getItems().setAll(Controller.getInstance().getListaAreeGestite(operaioScelto, cantiereLavori));
            noAreeLabel.setVisible(false);
        }
        else
        {
            noAreeLabel.setVisible(true);
        }
    }
}
