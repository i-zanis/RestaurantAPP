package packageFiles;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    
    // potentially here i will save the pages as a String
    public static String logInPage    = "FXML/loginpage.fxml";
    public static String registration = "FXML/registrationpage.fxml";
    public static String mainmenu     = "FXML/mainmenu.fxml";
    public static String checkout     = "FXML/checkout.fxml";
    public static String failed       = "FXML/authenticationFailedPage.fxml";
    public static String styleCSS     = "/packageFiles/CSS/style.css";
    public static String dragonPic    = "/packageFiles/Media/dragonpic.png";

    // logInPage
    public static String email = "";
    public static String password = "";

    // registrationPage
    public static String nameRegistration = "";
    public static String surnameRegistration = "";
    public static String emailRegistration = "";
    public static String passwordRegistration = "";
    public static String mobileRegistration = "";
    public static String cardNumberRegistration = "";
    public static String monthRegistration = "";
    public static String yearRegistration = "";
    public static Boolean defaultPayment = false;

    //mainMain
    public static int item1 = 0;
    public static int item2 = 0;
    public static int item3 = 0;
    public static int item4 = 0;
    public static int item5 = 0;

    final static int price1 = 18;
    public static int basketTotal = 0;
    public static ArrayList<String> itemList = new ArrayList<>();

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

