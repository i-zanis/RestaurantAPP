package PackageFiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static PackageFiles.Main.*;


public class CheckoutPageController implements Initializable {

    // variable names in the FXML file
    public Label errorLabelCheckout;
    public TextField nameCheckoutField;
    public TextField surnameCheckoutField;
    public TextField addressCheckoutField;
    public TextField postCodeCheckoutField;
    public TextField cardCheckoutField;
    public ChoiceBox<String> yearChoiceBox;
    public ChoiceBox<String> monthChoiceBox;
    public TextField cvvCheckoutField;
    public CheckBox checkBoxCheckout;
    public Button completePaymentButton;

    // initialize values for the drop down list(ChoiceBox)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthChoiceBox.getItems().addAll("Select month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12");
        monthChoiceBox.setValue("Select month");
        yearChoiceBox.getItems().addAll("Select year", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
                "2030", "2031");
        yearChoiceBox.setValue("Select year");
        cardCheckoutField.setTooltip(new Tooltip("Please enter 16 digits."));
        cvvCheckoutField.setTooltip(new Tooltip("The 3 digit code at the back of your card."));

        // Attempts to get the First Name/Surname of the account from the database to display in the label
        try {
            nameCheckoutField.setText(StoreDBManager.getCustomer(session).getString("first_name"));
            surnameCheckoutField.setText(StoreDBManager.getCustomer(session).getString("last_name"));
        } catch (SQLException e) {
            System.out.println("Error retrieving name or surname in CheckoutPageController.");
            e.printStackTrace();
        }
    }

    // Validation Form for CheckoutPage, successful input leads to ThankYouPage
    public void loadThankYou(ActionEvent event) throws Exception {
        try {
            boolean proceed = true;
            if (addressCheckoutField.getText().equals("")) {
                errorLabelCheckout.setText("Street address is required.");
                addressCheckoutField.requestFocus();
                proceed = false;
            } else if (postCodeCheckoutField.getText().equals("")) {
                errorLabelCheckout.setText("Post code is required.");
                postCodeCheckoutField.requestFocus();
                proceed = false;
            } else if (cardCheckoutField.getText().equals("")) {
                errorLabelCheckout.setText("Card Number is required.");
                cardCheckoutField.requestFocus();
                proceed = false;
            } else if (cvvCheckoutField.getText().equals("")) {
                errorLabelCheckout.setText("CVV Number is required.");
                cvvCheckoutField.requestFocus();
                proceed = false;
            }
            // Changes the View to ThankYouPage
            if (proceed) {
                Parent checkoutView = FXMLLoader.load(getClass().getResource(thankyou));
                Scene checkoutScene = new Scene(checkoutView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                checkoutScene.getStylesheets().add(styleCSS);
                window.setScene(checkoutScene);
                window.show();
            }
        } catch (Exception e) {
            System.out.println("Error occurred while opening the checkout.");
            e.printStackTrace();
        }
    }

    // Takes you back to MainMenu (button not activated, it has to be connected to database to retrieve the data)
    public void goBack(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainmenu));
            Scene logInPage = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logInPage.getStylesheets().add(styleCSS);
            window.setScene(logInPage);
            window.show();
        } catch (Exception e) {
            System.out.println("Error occurred while opening the registrationView.");
            e.printStackTrace();
        }
    }
}

