package Boundary.PanelController;

import Control.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {

    private Controller ctrl ;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField pswd;

    @FXML
    private TextField username;


    public void showMessage(String msg) {
        messageLabel.setText(msg);
    }

    public void setMainController(Controller ctrl) {
        this.ctrl = ctrl ;
    }


    public void userLogin(javafx.scene.input.MouseEvent mouseEvent) {

        ctrl.effettuaLogIn( username.getText() , pswd.getText() );

    }
}