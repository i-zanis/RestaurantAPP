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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkoutControllerMessage.setText("");
        /* alert pop up ugly window
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");
        alert.showAndWait();
        */
    }
}

