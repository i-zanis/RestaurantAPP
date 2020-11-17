package packageFiles;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {





    // potentially here i will save the pages as a String
    public static String logInPage    = "FXML/loginpage.fxml";
    public static String registration = "FXML/registrationpage.fxml";
    public static String mainmenu     = "FXML/mainmenu.fxml";
    public static String checkout     = "FXML/checkout.fxml";
    public static String styleCSS     = "/packageFiles/CSS/style.css";
    public static String dragonPic    = "/packageFiles/Media/dragonpic.png";

     @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(logInPage));
        Scene logInScene = new Scene(root);
        logInScene.getStylesheets().clear();
        logInScene.getStylesheets().add(styleCSS);



        primaryStage.setTitle("Shenlong");
        primaryStage.getIcons().add(new Image(dragonPic));
        primaryStage.setScene(logInScene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

