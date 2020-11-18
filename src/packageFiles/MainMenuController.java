package packageFiles;

import javafx.collections.ObservableList;
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
import java.util.ArrayList;
import java.util.Collection;
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
    public Label basketItemAmount2;
    public Label basketItemName2;
    public Label basketItemPrice2;
    public Label basketItemAmount3;
    public Label basketItemAmount4;
    public Label basketItemAmount5;

    public Label basketItemName3;
    public Label basketItemName4;
    public Label basketItemName5;

    public Label basketItemPrice3;
    public Label basketItemPrice4;
    public Label basketItemPrice5;

    // public static ArrayList<Label> labelList = new ArrayList<>();


    //observable list that will add nodes and take directly from arrayLIst

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

public void resetFieldsWithZeroAmount() {
    if (basketItemAmount1.getText().equals("0") || basketItemAmount1.getText().equals("-1")) {
        basketItemName1.setText("");
        basketItemAmount1.setText("");
        basketItemPrice1.setText("");
    }
    if (basketItemAmount2.getText().equals("0") || basketItemAmount2.getText().equals("-1")) {
        basketItemName2.setText("");
        basketItemAmount2.setText("");
        basketItemPrice2.setText("");
    }
    if (basketItemAmount3.getText().equals("0") || basketItemAmount3.getText().equals("-1")) {
        basketItemName3.setText("");
        basketItemAmount3.setText("");
        basketItemPrice3.setText("");
    }
    if (basketItemAmount4.getText().equals("0") || basketItemAmount4.getText().equals("-1")) {
        basketItemName4.setText("");
        basketItemAmount4.setText("");
        basketItemPrice4.setText("");
    }
    if (basketItemAmount5.getText().equals("0") || basketItemAmount5.getText().equals("-1")) {
        basketItemName5.setText("");
        basketItemAmount5.setText("");
        basketItemPrice5.setText("");
    }
}

    public void updateBasket() {
        resetFieldsWithZeroAmount();
            basketItemName1.setText(itemList.get(0));
            basketItemAmount1.setText(getAmount(itemList.get(0)) + "");
            basketItemPrice1.setText(getPrice(itemList.get(0)) + "");

            if (getAmount(itemList.get(0)) == 0) {
                itemList.remove(0);
                basketItemName2.setText("");
                basketItemAmount2.setText("");
                basketItemPrice2.setText("");
            }

            if (itemList.size() > 1) {
                basketItemName2.setText(itemList.get(1));
                basketItemAmount2.setText(getAmount(itemList.get(1)) + "");
                basketItemPrice2.setText(getPrice(itemList.get(1)) + "");
                if (getAmount(itemList.get(1)) == 0) {
                    itemList.remove(1);
                    basketItemName2.setText("");
                    basketItemAmount2.setText("");
                    basketItemPrice2.setText("");
                }
            }
            if (itemList.size() > 2) {
                basketItemName2.setText(itemList.get(2));
                basketItemAmount2.setText(getAmount(itemList.get(2)) + "");
                basketItemPrice2.setText(getPrice(itemList.get(2)) + "");
            }
            totalAmount.setText(pound + calculatePrice() + "");
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
                if (!itemList.contains(itemName1)) itemList.add(itemName1);
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
                }
                updateBasket();
                break;
                
            case "menu2add" :
                item2Units++;
                basketTotal++;
                menu2reduce.setVisible(true);
                menuItem2amount.setText(item2Units + "");
                menuItem2amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName2)) itemList.add(itemName2);

                updateBasket();
                break;
            case "menu2reduce" :
                item2Units--;
                basketTotal--;
                menuItem2amount.setText(item2Units + "");
                basketNumber.setText(basketTotal + "");
                if (item2Units == 0) {
                    menu2reduce.setVisible(false);
                    menuItem2amount.setVisible(false);
                }
                updateBasket();
                break;
            case "menu3add" :
                item3Units++;
                basketTotal++;
                menu3reduce.setVisible(true);
                menuItem3amount.setText(item3Units + "");
                menuItem3amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName3)) itemList.add(itemName3);
                updateBasket();
                break;
            case "menu3reduce" :
                item3Units--;
                basketTotal--;
                menuItem3amount.setText(item3Units + "");
                basketNumber.setText(basketTotal + "");
                if (item3Units == 0) {
                    menu3reduce.setVisible(false);
                    menuItem3amount.setVisible(false);
                }
                updateBasket();
                break;

            case "menu4add" :
                item4Units++;
                basketTotal++;
                menu4reduce.setVisible(true);
                menuItem4amount.setText(item4Units + "");
                menuItem4amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName4)) itemList.add(itemName4);
                updateBasket();
                break;
            case "menu4reduce" :
                item4Units--;
                basketTotal--;
                menuItem4amount.setText(item4Units + "");
                basketNumber.setText(basketTotal + "");
                if (item4Units == 0) {
                    menu4reduce.setVisible(false);
                    menuItem4amount.setVisible(false);
                }
                updateBasket();
                break;

            case "menu5add" :
                item5Units++;
                basketTotal++;
                menu5reduce.setVisible(true);
                menuItem5amount.setText(item5Units + "");
                menuItem5amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName5)) itemList.add(itemName5);
                updateBasket();
                break;
            case "menu5reduce" :
                item5Units--;
                basketTotal--;
                menuItem5amount.setText(item5Units + "");
                basketNumber.setText(basketTotal + "");
                if (item5Units == 0) {
                    menu5reduce.setVisible(false);
                    menuItem5amount.setVisible(false);
                }
                updateBasket();
                break;
        }
        }
    }
