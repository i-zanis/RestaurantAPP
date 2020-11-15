package packageFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    // potentially here i will save the pages as a String
    public static String logInPageFXML     = "FXML/logInPage.fxml";
    public static String registrationFXML  = "FXML/registrationPage";
    public static String mainMenuFXML      = "FXML/mainMenu.fxml";
    public static String productDetailFXML = "FXML/productDetail.fxml";
    public static String basketFXML        = "FXML/basket.fxml";
    public static String checkoutFXML      = "FXML/checkout.fxml";



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(logInPageFXML));
        primaryStage.setTitle("Shenlong");
        Scene scene = new Scene(root);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/packageFiles/CSS/style.css.");
        primaryStage.setScene(scene);

        primaryStage.getIcons().add(new Image("packageFiles/Media/dragonPic.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
