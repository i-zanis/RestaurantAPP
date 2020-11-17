package packageFiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

import static packageFiles.Main.*;
public class LogInPageController implements Initializable {
    public BorderPane logInPageController;
    @FXML
    public Label errorLabelLogIn;

    public Button signInButton;
    public TextField emailField;
    public PasswordField passwordField;
    public Button googleButton;
    public Button facebookButton;
    public Button registrationButton;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailField.setText(email);
    }


    public void signSuccessful(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainmenu));
            Scene mainMenuScene = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainMenuScene.getStylesheets().add(styleCSS);
            window.setScene(mainMenuScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the mainMenuView.");
            e.printStackTrace();
        }
    }

    public void loadMainMenu(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainmenu));
            Scene mainMenuScene = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainMenuScene.getStylesheets().add(styleCSS);
            window.setScene(mainMenuScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the mainMenuView.");
            e.printStackTrace();
        }
    }

    public void loadRegistration(ActionEvent event) throws Exception {
        try {
            Parent registrationView = FXMLLoader.load(getClass().getResource(registration));
            Scene registrationScene = new Scene(registrationView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            registrationScene.getStylesheets().add(styleCSS);
            window.setScene(registrationScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the registrationView.");
            e.printStackTrace();
        }
    }


    public void loadGoogleFacebook(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(failed));
            Scene failedScene = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(failedScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the mainMenu.");
            e.printStackTrace();
        }
    }
}
