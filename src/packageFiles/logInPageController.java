package packageFiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

import java.io.IOException;

import static packageFiles.Main.*;

public class logInPageController {

    @FXML
    private Button signInButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button googleButton;
    @FXML
    private Button facebookButton;

// placeholder for now
    public void signSuccessful(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainMenuFXML));
            Scene mainMenuScene = new Scene(mainMenuView);
            //gets stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainMenuScene.getStylesheets().add("/packageFiles/CSS/style.css.");
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
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainMenuFXML));
            Scene mainMenuScene = new Scene(mainMenuView);
            //gets stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            window.setScene(registrationScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the registrationView.");
            e.printStackTrace();
        }
    }
    public void loadProductDetail(ActionEvent event) throws Exception {
        try {
            Parent productDetailView = FXMLLoader.load(getClass().getResource(productDetailFXML));
            Scene productDetailScene = new Scene(productDetailView);
            //gets stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(productDetailScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the DetailView.");
            e.printStackTrace();
        }
    }
    public void loadBasket(ActionEvent event) throws Exception {
        try {
            Parent basketView = FXMLLoader.load(getClass().getResource(basketFXML));
            Scene basketScene = new Scene(basketView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(basketScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the basket.");
            e.printStackTrace();
        }
    }
    public void loadCheckout(ActionEvent event) throws Exception {
        try {
            Parent checkoutView = FXMLLoader.load(getClass().getResource(checkoutFXML));
            Scene checkoutScene = new Scene(checkoutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
