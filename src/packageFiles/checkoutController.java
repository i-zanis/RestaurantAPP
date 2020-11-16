package packageFiles;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;



public class checkoutController implements Initializable{
    @FXML
    public Label checkoutControllerMessage;
    @FXML
    public TextField test;
    public String yo = " , we thank you for being such a faggot.";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkoutControllerMessage.setText(logInPageController.message + yo);

    }
}

