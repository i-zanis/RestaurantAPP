package packageFiles;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            name = "Unknown";
        System.out.println(session);
        try {
            // getString needs try/catch for error
            name = StoreDBManager.getCustomer(session).getString("first_name");
        } catch (Exception e) {
            System.out.println("Something went wrong");
        }
        checkoutControllerMessage2.setText(
                String.format(
                        "%s %s.", thankYouMessageWithoutPeriod, name));
        System.out.println(StoreDBManager.logout(session));
    }
}

