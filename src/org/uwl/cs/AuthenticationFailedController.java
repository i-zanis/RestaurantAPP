package org.uwl.cs;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import static org.uwl.cs.model.Constant.CSS;
import static org.uwl.cs.model.Constant.LOGINPAGE;


public class AuthenticationFailedController {
    // takes User back from the failed Authentication page to LogInPage
    public void goBack(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(LOGINPAGE));
            Scene logInPage = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logInPage.getStylesheets().add(CSS);
            window.setScene(logInPage);
            window.show();
        } catch (Exception e) {
            System.out.println("Error occurred while opening the logInPage.");
            e.printStackTrace();
        }
    }
}
