package packageFiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static packageFiles.Main.*;
import static packageFiles.StoreDBManager.createUser;


public class RegistrationPageController implements Initializable {

    public Label errorLabelRegistration;
    public TextField nameRegistrationField;
    public TextField surnameRegistrationField;
    public TextField emailRegistrationField1;
    public TextField emailRegistrationField2;
    public TextField mobileRegistrationField;
    public PasswordField passwordFieldRegistration1;
    public PasswordField passwordFieldRegistration2;
    public Button registrationButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void registrationSuccessful(ActionEvent event) throws Exception {
        boolean proceed = false;
        try {
            errorLabelRegistration.setText(createUser(nameRegistrationField.getText(), surnameRegistrationField.getText(),
                    emailRegistrationField1.getText(), mobileRegistrationField.getText(),
                    passwordFieldRegistration1.getText()));

            // checks if both password fields are identical and the confirmation from the database that fields are valid
            if (errorLabelRegistration.getText().equals("User has been successfully created.")
                    && (passwordFieldRegistration1.getText().equals(passwordFieldRegistration2.getText()))) {
                proceed = true;
            } else if (errorLabelRegistration.getText().equals("First name is required.")) {
                nameRegistrationField.setText("");
                nameRegistrationField.requestFocus();
            } else if (errorLabelRegistration.getText().equals("Last name is required.")) {
                surnameRegistrationField.setText("");
                surnameRegistrationField.requestFocus();
            } else if (errorLabelRegistration.getText().equals("Email is required.")) {
                emailRegistrationField1.setText("");
                emailRegistrationField2.setText("");
                emailRegistrationField1.requestFocus();
            } else if (errorLabelRegistration.getText().equals("Invalid email address.")) {
                emailRegistrationField1.setText("");
                emailRegistrationField2.setText("");
                emailRegistrationField1.requestFocus();
            } else if (!emailRegistrationField1.getText().equals(emailRegistrationField2.getText())) {
                errorLabelRegistration.setText("The email addresses do not match.");
                emailRegistrationField2.setText("");
                emailRegistrationField2.requestFocus();
            } else if (errorLabelRegistration.getText().equals("Phone number is required.")) {
                mobileRegistrationField.setText("");
                mobileRegistrationField.requestFocus();
            } else if (errorLabelRegistration.getText().equals("Invalid UK phone number, must be 10 or 11 digits.")) {
                mobileRegistrationField.setText("");
                mobileRegistrationField.requestFocus();
            } else if (errorLabelRegistration.getText().equals("Password is required.")) {
                passwordFieldRegistration1.setText("");
                passwordFieldRegistration2.setText("");
                passwordFieldRegistration1.requestFocus();
            } else if (!passwordFieldRegistration1.getText().equals(passwordFieldRegistration2.getText())) {
                errorLabelRegistration.setText("The passwords do not match.");
                passwordFieldRegistration1.setText("");
                passwordFieldRegistration2.setText("");
                passwordFieldRegistration1.requestFocus();
            } else if (errorLabelRegistration.getText().equals("Weak password less than 8 characters.")) {
                passwordFieldRegistration1.setText("");
                passwordFieldRegistration2.setText("");
                passwordFieldRegistration1.requestFocus();
            }


            // this temporary solution till addition in the database.
            // sends the email back to Log in Page
            email = emailRegistrationField1.getText();
            // saves the name for the thanking message
            nameRegistration = nameRegistrationField.getText();

            if (proceed) {
                email = emailRegistrationField1.getText();
                Parent mainMenuView = FXMLLoader.load(getClass().getResource(logInPage));
                Scene logInPage = new Scene(mainMenuView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                logInPage.getStylesheets().add(styleCSS);
                window.setScene(logInPage);
                window.show();
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println("Error occurred while opening the registrationView.");
            e.printStackTrace();
        }
    }


}
