package packageFiles;

import com.sun.javafx.css.StyleManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    // potentially here i will save the pages as a String
    public static String logInPageFXML     = "FXML/logInPage.fxml";
    public static String registrationFXML  = "FXML/registrationPage.fxml";
    public static String mainMenuFXML      = "FXML/mainMenu.fxml";
    public static String checkoutFXML      = "FXML/checkout.fxml";
    public static String styleCSS          = "/packageFiles/CSS/style.css";
    public static String dragonPic         = "/packageFiles/Media/dragonPic.png";
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(logInPageFXML));
        Scene logInScene = new Scene(root);
        logInScene.getStylesheets().clear();
        logInScene.getStylesheets().add(styleCSS);
        primaryStage.setTitle("Shenlong");
        primaryStage.getIcons().add(new Image(dragonPic));
        primaryStage.setScene(logInScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
