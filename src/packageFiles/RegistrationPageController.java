package packageFiles;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static packageFiles.Main.*;
import static packageFiles.StoreDBManager.*;



public class RegistrationPageController implements Initializable {

    public Label errorLabelRegistration;
    public TextField nameRegistrationField;
    public TextField surnameRegistrationField;
    public TextField emailRegistrationField1;
    public TextField emailRegistrationField2;
    public TextField mobileRegistrationField;
    public TextField addressRegistrationField;
    public TextField postCodeRegistrationField;
    public PasswordField passwordFieldRegistration1;
    public PasswordField passwordFieldRegistration2;
    /*public TextField cardRegistrationField;
    public ChoiceBox<String> yearChoiceBox;
    public ChoiceBox<String> monthChoiceBox;
    public TextField cvvRegistrationField;
    public CheckBox checkBoxRegistration;
    public Button registrationButton;
*/

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
    }

    public void registrationSuccessful(ActionEvent event) throws Exception {
        boolean proceed = false;
        try {
            errorLabelRegistration.setText(createUser(nameRegistrationField.getText(), surnameRegistrationField.getText(),
                    emailRegistrationField1.getText(), mobileRegistrationField.getText(),
                    passwordFieldRegistration1.getText()));

            if (errorLabelRegistration.getText().equals("User has been successfully created.")) proceed = true;
            else if (errorLabelRegistration.getText().equals("First name is required.")) {
                nameRegistrationField.setText("");
                nameRegistrationField.requestFocus();
            }
            else if (errorLabelRegistration.getText().equals("Last name is required.")) {
                surnameRegistrationField.setText("");
                surnameRegistrationField.requestFocus();
            }
            else if (errorLabelRegistration.getText().equals("Email is required.")) {
                emailRegistrationField1.setText("");
                emailRegistrationField2.setText("");
                emailRegistrationField1.requestFocus();
            }
            else if (errorLabelRegistration.getText().equals("Invalid email address.")) {
                emailRegistrationField1.setText("");
                emailRegistrationField2.setText("");
                emailRegistrationField1.requestFocus();
            }
            else if (!emailRegistrationField1.getText().equals(emailRegistrationField2.getText())) {
               errorLabelRegistration.setText("The email addresses do not match.");
               emailRegistrationField2.setText("");
               emailRegistrationField2.requestFocus();
           }
            else if (errorLabelRegistration.getText().equals("Phone number is required.")) {
                mobileRegistrationField.setText("");
                mobileRegistrationField.requestFocus();
            }
            else if (errorLabelRegistration.getText().equals("Invalid UK phone number, must be 10 or 11 digits.")) {
                mobileRegistrationField.setText("");
                mobileRegistrationField.requestFocus();
            }
            else if (errorLabelRegistration.getText().equals("Password is required.")) {
                passwordFieldRegistration1.setText("");
                passwordFieldRegistration1.requestFocus();
            }
            else if (errorLabelRegistration.getText().equals("Weak password less than 8 characters.")) {
                passwordFieldRegistration1.setText("");
                passwordFieldRegistration2.setText("");
                passwordFieldRegistration1.requestFocus();
            }
            else if (!passwordFieldRegistration1.getText().equals(passwordFieldRegistration2.getText())) {
                errorLabelRegistration.setText("The passwords do not match.");
                passwordFieldRegistration1.setText("");
                passwordFieldRegistration2.setText("");
                passwordFieldRegistration1.requestFocus();
            }

            email = emailRegistrationField1.getText();
            if (errorLabelRegistration.getText().equals("User has been successfully created.")) proceed = true;
            if (proceed) {
                email = emailRegistrationField1.getText();
                Parent mainMenuView = FXMLLoader.load(getClass().getResource(logInPage));
                Scene logInPage = new Scene(mainMenuView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                logInPage.getStylesheets().add(styleCSS);
                window.setScene(logInPage);
                window.show();
            }
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the registrationView.");
            e.printStackTrace();
        }
    }
    public void goBack(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(logInPage));
            Scene logInPage = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logInPage.getStylesheets().add(styleCSS);
            window.setScene(logInPage);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the registrationView.");
            e.printStackTrace();
        }
    }


}
