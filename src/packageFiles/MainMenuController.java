package packageFiles;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import static packageFiles.Main.*;
import java.net.URL;
import java.util.ResourceBundle;
import static packageFiles.Main.checkout;

public class MainMenuController implements Initializable {

    public Button menu1add;
    public Button menu2add;
    public Button menu3add;
    public Button menu4add;
    public Button menu5add;
    public Button menu1reduce;
    public Button menu2reduce;
    public Button menu3reduce;
    public Button menu4reduce;
    public Button menu5reduce;
    public Label menuItem1amount;
    public Label menuItem2amount;
    public Label menuItem3amount;
    public Label menuItem4amount;
    public Label menuItem5amount;
    public Label basketNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadCheckout(ActionEvent event) throws Exception {
        try {
            Parent checkoutView = FXMLLoader.load(getClass().getResource(checkout));
            Scene checkoutScene = new Scene(checkoutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            checkoutScene.getStylesheets().add(styleCSS);
            window.setScene(checkoutScene);
            window.show();
        }
        catch (Exception e) {
            System.out.println("Error occurred while opening the checkout.");
            e.printStackTrace();
        }
    }

    // the buttons have been named and set to show only graphic for this to work.
    public void Addremove(Event e) {
        String condition  = ((Button)e.getSource()).getText();
        System.out.println(condition);
        System.out.println(item1);
    
        switch (condition) {
            case "menu1add" :
                item1++;
                basketTotal++;
                menu1reduce.setVisible(true);
                menuItem1amount.setText(item1 + "");
                menuItem1amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                break;
            case "menu1reduce" :
                item1--;
                basketTotal--;
                menuItem1amount.setText(item1 + "");
                basketNumber.setText(basketTotal + "");
                if (item1 == 0) {
                    menu1reduce.setVisible(false);
                    menuItem1amount.setVisible(false);
                }

                break;
                
            case "menu2add" :
                item2++;
                basketTotal++;
                menu2reduce.setVisible(true);
                break;
            case "menu2reduce" :
                item2--;
                basketTotal--;
                if (item2 == 0) menu2reduce.setVisible(false);
                break;
            case "menu3add" :
                item3++;
                menu3reduce.setVisible(true);
                basketTotal++;
                break;
            case "menu3reduce" :
                item3--;
                basketTotal--;
                if (item3 == 0) menu1reduce.setVisible(false);
                break;
            case "menu4add" :
                item4++;
                basketTotal++;
                menu4reduce.setVisible(true);
                break;
            case "menu4reduce" :
                item4--;
                basketTotal--;
                if (item4 == 0) menu1reduce.setVisible(false);
                break;
            case "menu5add" :
                item5++;
                basketTotal++;
                menu1reduce.setVisible(true);
                break;
            case "menu5reduce" :
                item5--;
                basketTotal--;
                if (item5 == 0) menu1reduce.setVisible(false);
                break;
        }
        }
    }
