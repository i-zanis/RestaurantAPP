package packageFiles;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static packageFiles.Main.*;


public class ThankYouPageController implements Initializable {
    public Label checkoutControllerMessage1;
    public Label checkoutControllerMessage2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (nameRegistration.equals("")) {
            checkoutControllerMessage2.setText(thankYouMessagePeriod);
        } else
            checkoutControllerMessage2.setText(thankYouMessageWithoutPeriod + nameRegistration);
    }
}

