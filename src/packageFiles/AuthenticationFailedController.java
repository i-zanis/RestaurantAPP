package packageFiles;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static packageFiles.Main.logInPage;
import static packageFiles.Main.styleCSS;

public class AuthenticationFailedController {
    public void goBack(ActionEvent event) throws Exception {
        try {
            Parent mainMenuView = FXMLLoader.load(getClass().getResource(logInPage));
            Scene logInPage = new Scene(mainMenuView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            logInPage.getStylesheets().add(styleCSS);
            window.setScene(logInPage);
            window.show();
        } catch (Exception e) {
            System.out.println("Error occurred while opening the logInPage.");
            e.printStackTrace();
        }
    }
}
