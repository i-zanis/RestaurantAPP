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

import javax.swing.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static packageFiles.Main.*;

public class logInPageController {


    public BorderPane logInPageController;
    public Label errorLabelLogIn;
    public Label errorLabelRegistration;
    public TextField fullnameFieldRegistration;
    public TextField emailFieldRegistration;
    public TextField mobileFieldRegistration;
    public PasswordField passwordFieldRegistration;
    public TextField cardFieldRegistration;
    public TextField cvvFieldRegistration;
    public CheckBox checkBoxRegistration;
    public ChoiceBox monthChoiceBox;
    public ChoiceBox yearChoiceBox;
    public Button registrationButton;
    public Label thankingMessage;
    public Button signInButton;
    public Button googleButton;
    public PasswordField passwordField;
    public TextField emailField;
    public Button facebookButton;
    public Button checkoutButton;


    // placeholder for now
    public void signSuccessful(ActionEvent event) throws Exception {
        try {
            
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainMenuFXML));
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

    public void registrationSuccessful(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(logInPageFXML));
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
    
    public void loadMainMenu(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainMenuFXML));
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
            Parent registrationView = FXMLLoader.load(getClass().getResource(registrationFXML));
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

    public void loadCheckout(ActionEvent event) throws Exception {
        try {
            Parent checkoutView = FXMLLoader.load(getClass().getResource(checkoutFXML));
            Scene checkoutScene = new Scene(checkoutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            checkoutScene.getStylesheets().add(styleCSS);
            window.setScene(checkoutScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the checkout.");
            e.printStackTrace();
        }
    }
    public void loadGoogle(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainMenuFXML));
            Scene mainMenuScene = new Scene(mainMenuView);
            //gets stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainMenuScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the mainMenu.");
            e.printStackTrace();
        }
    }

    public void loadFacebook(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainMenuFXML));
            Scene mainMenuScene = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainMenuScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the mainMenu.");
            e.printStackTrace();
        }
    }

}
