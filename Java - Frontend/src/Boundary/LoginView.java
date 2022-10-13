package Boundary;

import Boundary.PanelController.LogInController;
import Control.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView {

    private final Controller ctrl ;
    private LogInController loginController ;
    private Stage theStage ;

    public LoginView() {
        this.ctrl = Controller.getInstance();
        theStage = null ;
    }

    public void start(Stage stage) throws IOException {
        theStage = stage ;
        FXMLLoader loader = new FXMLLoader(LoginView.class.getResource("FXML/Log_In.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.hide();
        stage.setScene(scene);

        // Recupera il controller della view, istanziato da JavaFX.
        loginController = loader.getController();
        // Passa al controller della view il controller della business logic "ctrl".
        loginController.setMainController(ctrl);

        stage.setTitle("Log In Page");
        stage.setFullScreen(false);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setWidth(630.0);
        stage.setHeight(370.0);
        stage.setMinHeight(370);
        stage.setMinWidth(630);


        stage.show();
    }

    public void failedLogIn(String reason) {
        loginController.showMessage(reason);
    }

    public void hide() {
        if (!(theStage == null)) theStage.hide();
    }
}
