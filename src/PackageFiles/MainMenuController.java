package PackageFiles;


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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static PackageFiles.Main.*;


public class MainMenuController implements Initializable {

    // ArrayList to collect the Labels
    public static ArrayList<Label> labelList = new ArrayList<>();
    // variable names in the FXML file
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
    public Label basketItemAmount2;
    public Label basketItemName2;
    public Label basketItemPrice2;
    public Label basketItemAmount3;
    public Label basketItemName3;
    public Label basketItemPrice3;
    public Label basketItemAmount4;
    public Label basketItemName4;
    public Label basketItemPrice4;
    public Label basketItemAmount5;
    public Label basketItemName5;
    public Label basketItemPrice5;
    public Label totalAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Grouping all 15 Labels for iterations
        labelList.add(basketItemAmount1);
        labelList.add(basketItemName1);
        labelList.add(basketItemPrice1);
        labelList.add(basketItemAmount2);
        labelList.add(basketItemName2);
        labelList.add(basketItemPrice2);
        labelList.add(basketItemAmount3);
        labelList.add(basketItemName3);
        labelList.add(basketItemPrice3);
        labelList.add(basketItemAmount4);
        labelList.add(basketItemName4);
        labelList.add(basketItemPrice4);
        labelList.add(basketItemAmount5);
        labelList.add(basketItemName5);
        labelList.add(basketItemPrice5);
    }

    // displays the all the items in the basket window
    public void displayAll() {
        totalAmount.setText(pound + calculateTotal() + "");
        for (int i = 0; i < itemList.size(); i++) {
            labelList.get(i).setText(itemList.get(i));
        }
    }
    // clear all 15 labels before update
    public void clearAll() {
        for (int i = 0; i < 15; i++) {
            labelList.get(i).setText("");
        }
    }
    // adds items to be displayed in the basket window
    public void addToList(String itemName) {
        itemList.add(getAmount(itemName) + "");// empty string for string conversion
        itemList.add(itemName);
        itemList.add(pound + getPrice(itemName));
    }
    // removes items to be displayed in the basket window
    public void removeFromList(String itemName) {
        itemList.remove(itemList.indexOf(itemName) - 1);
        itemList.remove(itemList.indexOf(itemName) + 1);
        itemList.remove(itemName);
    }
    
    // Increases the amount of item EXISTING in the list
    public void increaseItemAmountBasket(String itemName, int itemUnits) {
        if (itemList.contains(itemName)) {
            if (itemUnits != 0) {
                itemList.set(itemList.indexOf(itemName) - 1, itemUnits + multi);
                itemList.set(itemList.indexOf(itemName) + 1, pound + (itemUnits * getPrice(itemName)));
            }
        }
    }
    // Decreases the amount of item EXISTING in the list
    public void decreaseItemAmountBasket(String itemName, int itemUnits) {
        if (itemList.contains(itemName)) {
            if (itemUnits != 0) {
                itemList.set(itemList.indexOf(itemName) - 1, itemUnits + multi);
                itemList.set(itemList.indexOf(itemName) + 1, pound + (itemUnits * getPrice(itemName)));
            }
        }
    }

    // the buttons have been named and set to show only graphic for this to work.
    // Adds/removes items based on (+)/(-) buttons, updates values, hides/unhides (-) button, quantity label
    public void Addremove(Event e) {
        // gets the name of the button as a string
        String condition = ((Button) e.getSource()).getText();

        // checks if itemList contains item to add for displaying in the basket window
        // if zero item units it hides (-) button and current units number
        switch (condition) {
            case "menu1add" -> {
                item1Units++; // item units to be purchased
                basketTotal++; // total of all types of units in the basket
                menu1reduce.setVisible(true); // makes the (-) button visible
                menuItem1amount.setText(item1Units + ""); // updates Label new units - empty space "" int to String
                menuItem1amount.setVisible(true); // makes units Label between (-) 1 (+) visible
                basketNumber.setText(basketTotal + ""); // updates total of all units in the basket
                if (!itemList.contains(itemName1)) addToList(itemName1);
                increaseItemAmountBasket(itemName1, item1Units);
                clearAll();
                displayAll();
            }
            case "menu1reduce" -> {
                item1Units--; // item units to be purchased
                basketTotal--; // total of all types of units in the basket
                menuItem1amount.setText(item1Units + ""); // updates Label new units - empty space "" int to String
                basketNumber.setText(basketTotal + ""); // updates total of all units in the basket
                decreaseItemAmountBasket(itemName1, item1Units);
                if (item1Units == 0) {
                    menu1reduce.setVisible(false);
                    menuItem1amount.setVisible(false);
                    removeFromList(itemName1);
                }
                clearAll();
                displayAll();
            }
            case "menu2add" -> {
                item2Units++;
                basketTotal++;
                menu2reduce.setVisible(true);
                menuItem2amount.setText(item2Units + "");
                menuItem2amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName2)) addToList(itemName2);
                increaseItemAmountBasket(itemName2, item2Units);
                clearAll();
                displayAll();
            }
            case "menu2reduce" -> {
                item2Units--;
                basketTotal--;
                menuItem2amount.setText(item2Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName2, item2Units);
                if (item2Units == 0) {
                    menu2reduce.setVisible(false);
                    menuItem2amount.setVisible(false);
                    removeFromList(itemName2);
                }
                clearAll();
                displayAll();
            }
            case "menu3add" -> {
                item3Units++;
                basketTotal++;
                menu3reduce.setVisible(true);
                menuItem3amount.setText(item3Units + "");
                menuItem3amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName3)) addToList(itemName3);
                increaseItemAmountBasket(itemName3, item3Units);
                clearAll();
                displayAll();
            }
            case "menu3reduce" -> {
                item3Units--;
                basketTotal--;
                menuItem3amount.setText(item3Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName3, item3Units);
                if (item3Units == 0) {
                    menu3reduce.setVisible(false);
                    menuItem3amount.setVisible(false);
                    removeFromList(itemName3);
                }
                clearAll();
                displayAll();
            }
            case "menu4add" -> {
                item4Units++;
                basketTotal++;
                menu4reduce.setVisible(true);
                menuItem4amount.setText(item4Units + "");
                menuItem4amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName4)) addToList(itemName4);
                increaseItemAmountBasket(itemName4, item4Units);
                clearAll();
                displayAll();
            }
            case "menu4reduce" -> {
                item4Units--;
                basketTotal--;
                menuItem4amount.setText(item4Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName4, item4Units);
                if (item4Units == 0) {
                    menu4reduce.setVisible(false);
                    menuItem4amount.setVisible(false);
                    removeFromList(itemName4);
                }
                clearAll();
                displayAll();
            }
            case "menu5add" -> {
                item5Units++;
                basketTotal++;
                menu5reduce.setVisible(true);
                menuItem5amount.setText(item5Units + "");
                menuItem5amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName5)) addToList(itemName5);
                increaseItemAmountBasket(itemName5, item5Units);
                clearAll();
                displayAll();
            }
            case "menu5reduce" -> {
                item5Units--;
                basketTotal--;
                menuItem5amount.setText(item5Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName5, item5Units);
                if (item5Units == 0) {
                    menu5reduce.setVisible(false);
                    menuItem5amount.setVisible(false);
                    removeFromList(itemName5);
                }
                clearAll();
                displayAll();
            }
        }
    }
    // returns the amount of item based on itemName parameter
    public int getAmount(String itemName) {
        switch (itemName) {
            case itemName1:
                return item1Units;
            case itemName2:
                return item2Units;
            case itemName3:
                return item3Units;
            case itemName4:
                return item4Units;
            case itemName5:
                return item5Units;
            default:
                System.out.println("Error at basketItemAmount");
                break;
        }
        return -1;
    }
    // returns the price of item based on itemName parameter
    public int getPrice(String itemName) {
        switch (itemName) {
            case itemName1:
                return price1;
            case itemName2:
                return price2;
            case itemName3:
                return price3;
            case itemName4:
                return price4;
            case itemName5:
                return price5;
            default:
                System.out.println("Error retrieving itemName for basketItemPrice.");
                break;
        }
        return -1;
    }
    // calculates total amount to pay of all items in basket
    public double calculateTotal() {
        return (item1Units * price1) + (item2Units * price2) + (item3Units * price3) + (item4Units * price4)
                + (item5Units * price5);
    }
    // Loads the Checkout View
    public void loadCheckout(ActionEvent event) throws Exception {
        try {
            Parent checkoutView = FXMLLoader.load(getClass().getResource(checkout));
            Scene checkoutScene = new Scene(checkoutView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            checkoutScene.getStylesheets().add(styleCSS);
            window.setScene(checkoutScene);
            window.show();
        } catch (Exception e) {
            System.out.println("Error occurred while opening the checkout.");
            e.printStackTrace();
        }
    }


}
