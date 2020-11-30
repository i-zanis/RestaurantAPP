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
import java.util.ResourceBundle;
import static PackageFiles.Main.*;

public class LogInPageController implements Initializable {

    // variable names in the FXML file
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

    // Validation Form for LogInPage, successful input leads to MainMenu
    public void signSuccessful(ActionEvent event) throws Exception {
        String email = emailField.getText();
        String pwd = passwordField.getText();
        session = StoreDBManager.login(email, pwd);

        if (emailField.getText().equals("")) {
            errorLabelLogIn.setText("Email is required.");
            emailField.requestFocus();
        }
        else if (passwordField.getText().equals("")) {
            errorLabelLogIn.setText("Password is required.");
            passwordField.requestFocus();
        }
        else if (session.isEmpty()) {
            errorLabelLogIn.setText("Invalid Username or Password.");
            emailField.setText("");
            passwordField.setText("");
            emailField.requestFocus();
        }
        if (session.isEmpty()) System.out.println("Invalid Session.");
            else System.out.println("Session code " + session); // for educational purposes it is displayed

        // Changes the View to MainMenu
        if (!session.isEmpty()) {
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
        } else {
            System.out.println(session);

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

    // method for Google/Facebook method that has not been implemented yet due to licensing
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
