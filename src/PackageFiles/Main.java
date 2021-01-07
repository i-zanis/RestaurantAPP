package PackageFiles;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Made in Java 14.0.2.
 * If the Program does not work please download "javafx-sdk-11.0" and add all the jar-files in
 * Javafx-sdk-11.0.2\lib\ to the global library.
 *
 * VM options --module-path %java path% --add-modules javafx.controls,javafx.fxml
 */
public class Main extends Application {

    // To be placed in a separate Enum file in the future

    // Static variables of MainMenu
    final static String pound = "£";
    final static String multi = "x";
    final static String itemName1 = "Mapo Tofu";
    final static String itemName2 = "Prawn Dumpling";
    final static String itemName3 = "Wonton Soup";
    final static String itemName4 = "Spring Rolls";
    final static String itemName5 = "Egg Fried Rice";
    final static int price1 = 13;
    final static int price2 = 15;
    final static int price3 = 18;
    final static int price4 = 11;
    final static int price5 = 14;

    // Static variables of LogInPage
    public static String email = "";    // to save the email in Registration Page and display it back at LogInPage
    public static String password = "";

    // Static variables of RegistrationPage
    public static String nameRegistration = "";

    // Static variables for MainMenu
    public static int item1Units = 0;
    public static int item2Units = 0;
    public static int item3Units = 0;
    public static int item4Units = 0;
    public static int item5Units = 0;
    public static int basketTotal = 0;
    public static ArrayList<String> itemList = new ArrayList<>();

    // Static variables for checkout
    public static String cardNumberRegistration = "";
    public static String monthRegistration = "";
    public static String yearRegistration = "";
    public static Boolean defaultPayment = false;

    // Static variables for ThankYouPage
    final static String thankYouMessagePeriod = "Our Chefs are currently sharpening their knives and collecting the " +
            "freshest ingredients." +
            "We aim for delivery in less than 45 minutes. For one more time thank you for choosing Shenlong.";
    final static String thankYouMessageWithoutPeriod = "Our Chefs are currently sharpening their knives and " +
            "collecting the freshest ingredients " +
            "We aim for delivery in less than 45 minutes. For one more time thank you for choosing Shenlong";
    public static String session = "";
    public static String name = "";

    // Save the FXML files here to avoid errors
    public static String logInPage    = "FXML/loginpage.fxml";
    public static String registration = "FXML/registrationpage.fxml";
    public static String mainmenu     = "FXML/mainmenu.fxml";
    public static String checkout     = "FXML/checkout.fxml";
    public static String thankyou     = "FXML/thankyou.fxml";
    public static String failed       = "FXML/authenticationFailedPage.fxml";
    public static String styleCSS     = "/PackageFiles/CSS/style.css";
    public static String dragonPic    = "/PackageFiles/Media/dragonpic.png";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // sets the initial FXML file to be loaded
        Parent root = FXMLLoader.load(getClass().getResource(logInPage));
        // Create a scene and set the initial(root) FXML
        Scene logInScene = new Scene(root);
        // Clear any previous CSS to avoid inconsistencies
        logInScene.getStylesheets().clear();
        // Add CSS from separate Style Sheet
        logInScene.getStylesheets().add(styleCSS);
        // Sets the title of the window
        primaryStage.setTitle("Shenlong");
        // Sets the icon of the window in the Taskbar and top window bar
        primaryStage.getIcons().add(new Image(dragonPic));
        // Sets the Scene to the Stage
        primaryStage.setScene(logInScene);
        // Shows the Stage
        primaryStage.show();
        // makes the window not resizable
        primaryStage.setResizable(false);
    }
}




