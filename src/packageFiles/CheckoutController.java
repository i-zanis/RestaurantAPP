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
         /*monthChoiceBox.getItems().addAll("Select month", "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct",
                "Nov", "Dec");
        monthChoiceBox.setValue("Select month");
        yearChoiceBox.getItems().addAll("Select year", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
                "2030", "2031");
        yearChoiceBox.setValue("Select year");
        cardRegistrationField.setTooltip(new Tooltip("Please enter 16 digits."));
        cvvRegistrationField.setTooltip(new Tooltip("The 3 digit code at the back of your card."));
        */
        checkoutControllerMessage.setText("");
    }
}

