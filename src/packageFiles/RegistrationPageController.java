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
import static packageFiles.Main.*;



public class RegistrationPageController implements Initializable {

    public Label errorLabelRegistration;
    public TextField emailRegistrationField2;
    public TextField surnameRegistrationField;
    public TextField nameRegistrationField;
    public TextField emailRegistrationField1;
    public TextField mobileRegistrationField;
    public PasswordField passwordFieldRegistration1;
    public TextField cardRegistrationField;
    public TextField cvvRegistrationField;
    public CheckBox checkBoxRegistration;
    public ChoiceBox<String> yearChoiceBox;
    public ChoiceBox<String>  monthChoiceBox;
    public Button registrationButton;
    public TextField postCodeRegistrationField;
    public TextField addressRegistrationField;
    public PasswordField passwordFieldRegistration2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monthChoiceBox.getItems().addAll("Select month","Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct",
                "Nov", "Dec");
        monthChoiceBox.setValue("Select month");
        yearChoiceBox.getItems().addAll("Select year","2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029",
                "2030", "2031");
        yearChoiceBox.setValue("Select year");
        cardRegistrationField.setTooltip(new Tooltip("Please enter 16 digits."));
        cvvRegistrationField.setTooltip(new Tooltip("The 3 digit code at the back of your card."));

    }
    public void registrationSuccessful(ActionEvent event) throws Exception {
        try {
            email = emailRegistrationField1.getText();
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
