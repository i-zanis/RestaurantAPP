package org.uwl.cs;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static org.uwl.cs.Main.*;


public class ThankYouPageController implements Initializable {

    // variable names in the FXML file
    public Label checkoutControllerMessage1;
    public Label checkoutControllerMessage2;

    // initializes values for ThankYouPage
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // display a message without the User's name if name not found, to be better implemented
        if (nameRegistration.equals("")) {
            checkoutControllerMessage2.setText(thankYouMessagePeriod);
        } else
            // for educational purposes it is displayed
            name = "Unknown";
        System.out.println(session);
        try {
            // getString() needs try/catch for error
            name = StoreDBManager.getCustomer(session).getString("first_name");
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
        checkoutControllerMessage2.setText(
                String.format(
                        "%s %s.", thankYouMessageWithoutPeriod, name));
        System.out.println(StoreDBManager.logout(session));
    }
}

