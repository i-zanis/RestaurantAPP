package packageFiles;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;



public class CheckoutController implements Initializable{
    public Label checkoutControllerMessage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkoutControllerMessage.setText("");
    }
}

