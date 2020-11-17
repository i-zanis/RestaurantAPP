package packageFiles;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public Label basketItemAmount1;
    public Label basketItemName1;
    public Label basketItemPrice1;
    public Label totalAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public int getAmount(String itemName) {
        if (itemName.equals(itemName1)) return item1Units;
        else if (itemName.equals(itemName2)) return item2Units;
        else if (itemName.equals(itemName3)) return item3Units;
        else if (itemName.equals(itemName4)) return item4Units;
        else if (itemName.equals(itemName5)) return item5Units;
        else System.out.println("Error at basketItemAmount");
        return -1;
    }
    public int getPrice(String itemName) {
        if (itemName.equals(itemName1)) return price1;
        else if (itemName.equals(itemName2)) return price2;
        else if (itemName.equals(itemName3)) return price3;
        else if (itemName.equals(itemName4)) return price4;
        else if (itemName.equals(itemName5)) return price5;
        else System.out.println("Error retrieving itemName for basketItemPrice.");
        return -1;
    }
    public double calculatePrice() {
        return (item1Units * price1) + (item2Units * price2) + (item3Units * price3) + (item4Units * price4)
                + (item5Units * price5);
    }
    public void updateBasket() {
            itemList.trimToSize();
        for (int i = 0; i < itemList.size(); i++) {
            basketItemName1.setText(itemList.get(i));
            basketItemAmount1.setText(getAmount(itemList.get(i)) + "");
            basketItemPrice1.setText(getPrice(itemList.get(i)) + "");
            totalAmount.setText(pound + calculatePrice() + "");
        }
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
        System.out.println(item1Units);
    
        switch (condition) {
            case "menu1add" :
                item1Units++;
                basketTotal++;
                menu1reduce.setVisible(true);
                menuItem1amount.setText(item1Units + "");
                menuItem1amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                itemList.add("Mapo Tofu");
                updateBasket();
                break;
            case "menu1reduce" :
                item1Units--;
                basketTotal--;
                menuItem1amount.setText(item1Units + "");
                basketNumber.setText(basketTotal + "");
                if (item1Units == 0) {
                    menu1reduce.setVisible(false);
                    menuItem1amount.setVisible(false);
                    itemList.remove("Mapo Tofu");
                }
                updateBasket();
                break;
                
            case "menu2add" :
                item2Units++;
                basketTotal++;
                menu2reduce.setVisible(true);
                break;
            case "menu2reduce" :
                item2Units--;
                basketTotal--;
                if (item2Units == 0) menu2reduce.setVisible(false);
                break;
            case "menu3add" :
                item3Units++;
                menu3reduce.setVisible(true);
                basketTotal++;
                break;
            case "menu3reduce" :
                item3Units--;
                basketTotal--;
                if (item3Units == 0) menu1reduce.setVisible(false);
                break;
            case "menu4add" :
                item4Units++;
                basketTotal++;
                menu4reduce.setVisible(true);
                break;
            case "menu4reduce" :
                item4Units--;
                basketTotal--;
                if (item4Units == 0) menu1reduce.setVisible(false);
                break;
            case "menu5add" :
                item5Units++;
                basketTotal++;
                menu1reduce.setVisible(true);
                break;
            case "menu5reduce" :
                item5Units--;
                basketTotal--;
                if (item5Units == 0) menu1reduce.setVisible(false);
                break;
        }
        }
    }
