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

import static packageFiles.Main.mainMenuFXML;

public class logInPageController {

    @FXML
    private Button okButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button googleButton;
    @FXML
    private Button facebookButton;


    public void loadMain(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(mainMenuFXML));
            Scene mainMenuScene = new Scene(mainMenuView);
            //gets stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(mainMenuScene);
            window.show();

        } catch (Exception e) {
            System.out.println("Error occured while opening registration page");
            e.printStackTrace();
        }

    }
}
