package packageFiles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
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
    public static ArrayList<Label> labelList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        //basketItemAmount1, basketItemAmount1, basketItemAmount2, basketItemName2,
         //       basketItemPrice2, basketItemAmount3, basketItemName3, basketItemPrice3, basketItemAmount4, basketItemName4,basketItemPrice4, basketItemAmount5, basketItemName5, basketItemPrice5);
        public void displayAll() {
            totalAmount.setText(pound + calculatePrice() + "");
            for (int i = 0; i < itemList.size(); i++) {
                labelList.get(i).setText(itemList.get(i));
            }
        }
    public void addToList(String itemName) {
        itemList.add(getAmount(itemName) + "");// empty string for string conversion
        itemList.add(itemName);
        itemList.add(pound + getPrice(itemName));
    }

    public void removeFromList(String itemName) {
        itemList.remove(itemList.indexOf(itemName) - 1);
        itemList.remove(itemName);
        itemList.remove(pound + getPrice(itemName));
    }
    public void clearAll() {
     for (int i = 0; i < 15; i++) { //change to 155
         labelList.get(i).setText("");
       }
     }
    public void increaseItemAmountBasket(String itemName, int itemUnits) {
        if (itemList.contains(itemName)) {
            if (itemUnits != 0) {
                itemList.set(itemList.indexOf(itemName) - 1, itemUnits + multi);
            }
           // else itemList.set(itemList.indexOf(itemName) - 1, "");
         //   int x = Integer.parseInt(itemList.get(itemList.indexOf(itemName) - 1));
         //       itemList.set(itemList.indexOf(itemName) - 1, x + "");
            }
        }

    public void decreaseItemAmountBasket(String itemName, int itemUnits) {
        if (itemList.contains(itemName)) {
            if (itemUnits != 0) {
                itemList.set(itemList.indexOf(itemName) - 1, itemUnits + multi);
            }
        }
    }

    public void Addremove(Event e) {
        String condition = ((Button) e.getSource()).getText();
        System.out.println(condition);
        System.out.println(item1Units);

        switch (condition) {
            case "menu1add":
                item1Units++;
                basketTotal++;
                menu1reduce.setVisible(true);
                menuItem1amount.setText(item1Units + "");
                menuItem1amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName1)) addToList(itemName1);
                increaseItemAmountBasket(itemName1,item1Units);
                clearAll();
                displayAll();
                break;
            case "menu1reduce":
                item1Units--;
                basketTotal--;
                menuItem1amount.setText(item1Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName1,item1Units);
                if (item1Units == 0) {
                    menu1reduce.setVisible(false);
                    menuItem1amount.setVisible(false);
                    removeFromList(itemName1);
                }
                clearAll();
                displayAll();
                break;

            case "menu2add" :
                item2Units++;
                basketTotal++;
                menu2reduce.setVisible(true);
                menuItem2amount.setText(item2Units + "");
                menuItem2amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName2)) addToList(itemName2);
                increaseItemAmountBasket(itemName2,item2Units);
                clearAll();
                displayAll();

                break;
            case "menu2reduce" :
                item2Units--;
                basketTotal--;
                menuItem2amount.setText(item2Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName2,item2Units);
                if (item2Units == 0) {
                    menu2reduce.setVisible(false);
                    menuItem2amount.setVisible(false);
                    removeFromList(itemName2);
                }
                clearAll();
                displayAll();
                break;
            case "menu3add" :
                item3Units++;
                basketTotal++;
                menu3reduce.setVisible(true);
                menuItem3amount.setText(item3Units + "");
                menuItem3amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName3)) addToList(itemName3);
                increaseItemAmountBasket(itemName3,item3Units);
                clearAll();
                displayAll();
                break;
            case "menu3reduce" :
                item3Units--;
                basketTotal--;
                menuItem3amount.setText(item3Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName3,item3Units);
                if (item3Units == 0) {
                    menu3reduce.setVisible(false);
                    menuItem3amount.setVisible(false);
                    removeFromList(itemName3);
                }
                clearAll();
                displayAll();
                break;

            case "menu4add" :
                item4Units++;
                basketTotal++;
                menu4reduce.setVisible(true);
                menuItem4amount.setText(item4Units + "");
                menuItem4amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName4)) addToList(itemName4);
                increaseItemAmountBasket(itemName4,item4Units);
                clearAll();
                displayAll();
                break;
            case "menu4reduce" :
                item4Units--;
                basketTotal--;
                menuItem4amount.setText(item4Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName4,item4Units);
                if (item4Units == 0) {
                    menu4reduce.setVisible(false);
                    menuItem4amount.setVisible(false);
                    removeFromList(itemName4);
                }
                clearAll();
                displayAll();
                break;

            case "menu5add" :
                item5Units++;
                basketTotal++;
                menu5reduce.setVisible(true);
                menuItem5amount.setText(item5Units + "");
                menuItem5amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName5)) addToList(itemName5);
                increaseItemAmountBasket(itemName5,item5Units);
                clearAll();
                displayAll();
                break;
            case "menu5reduce" :
                item5Units--;
                basketTotal--;
                menuItem5amount.setText(item5Units + "");
                basketNumber.setText(basketTotal + "");
                decreaseItemAmountBasket(itemName5,item5Units);
                if (item5Units == 0) {
                    menu5reduce.setVisible(false);
                    menuItem5amount.setVisible(false);
                    removeFromList(itemName5);
                }
                clearAll();
                displayAll();
                break;
        }
    }

    public int getAmount(String itemName) {
        if      (itemName.equals(itemName1)) return item1Units;
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

    /*
public void resetFieldsWithZeroAmount() {
        itemList.trimToSize();
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
public void updateBasket100() {
    basketItemName1.setText("");
    basketItemAmount1.setText("");
    basketItemPrice1.setText("");
    basketItemName2.setText("");
    basketItemAmount2.setText("");
    basketItemPrice2.setText("");
    basketItemName3.setText("");
    basketItemAmount3.setText("");
    basketItemPrice3.setText("");
    basketItemName4.setText("");
    basketItemAmount4.setText("");
    basketItemPrice4.setText("");
    basketItemName5.setText("");
    basketItemAmount5.setText("");
    basketItemPrice5.setText("");

    for (int i = 0; i < itemList.size(); i++) {
        if (getAmount(itemList.get(0)) == 0) {
            itemList.remove(0);
            basketItemName1.setText("");
            basketItemAmount1.setText("");
            basketItemPrice1.setText("");
        }
    }
}
    public void updateBasket() {
        resetFieldsWithZeroAmount();
        for (String s : itemList) {
            System.out.println(s);
        }
            basketItemName1.setText(itemList.get(0));
            basketItemAmount1.setText(getAmount(itemList.get(0)) + "");
            basketItemPrice1.setText(getPrice(itemList.get(0)) + "");
            if (getAmount(itemList.get(0)) == 0) {
                itemList.remove(0);
                basketItemName1.setText("");
               basketItemAmount1.setText("");
               basketItemPrice1.setText("");
            }

                basketItemName2.setText(itemList.get(1));
                basketItemAmount2.setText(getAmount(itemList.get(1)) + "");
                basketItemPrice2.setText(getPrice(itemList.get(1)) + "");
                if (getAmount(itemList.get(1)) == 0) {
                    itemList.remove(1);
                    basketItemName2.setText("");
                    basketItemAmount2.setText("");
                    basketItemPrice2.setText("");
                }
                basketItemName3.setText(itemList.get(2));
                basketItemAmount3.setText(getAmount(itemList.get(2)) + "");
                basketItemPrice3.setText(getPrice(itemList.get(2)) + "");
                if (getAmount(itemList.get(2)) == 0) {
                    itemList.remove(2);
                    basketItemName3.setText("");
                    basketItemAmount3.setText("");
                    basketItemPrice3.setText("");
                }
            basketItemName4.setText(itemList.get(3));
            basketItemAmount4.setText(getAmount(itemList.get(3)) + "");
            basketItemPrice4.setText(getPrice(itemList.get(3)) + "");
            if (getAmount(itemList.get(3)) == 0) {
                itemList.remove(3);
                basketItemName4.setText("");
                basketItemAmount4.setText("");
                basketItemPrice4.setText("");
            }

                basketItemName5.setText(itemList.get(4));
                basketItemAmount5.setText(getAmount(itemList.get(4)) + "");
                basketItemPrice5.setText(getPrice(itemList.get(4)) + "");
                if (getAmount(itemList.get(4)) == 0) {
                    itemList.remove(4);
                    basketItemName5.setText("");
                    basketItemAmount5.setText("");
                    basketItemPrice5.setText("");
                }
                resetFieldsWithZeroAmount();
                totalAmount.setText(pound + calculatePrice() + "");
            }
*/
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

    public void Addremove22(Event e) {
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
                if (!itemList.contains(itemName1)) addToList(itemName1);
                break;
            case "menu1reduce" :
                item1Units--;
                basketTotal--;
                menuItem1amount.setText(item1Units + "");
                basketNumber.setText(basketTotal + "");
                if (item1Units == 0) {
                    menu1reduce.setVisible(false);
                    menuItem1amount.setVisible(false);
                    itemList.remove(itemName1);
                }
                break;
                
            case "menu2add" :
                item2Units++;
                basketTotal++;
                menu2reduce.setVisible(true);
                menuItem2amount.setText(item2Units + "");
                menuItem2amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName2)) addToList(itemName2);


                break;
            case "menu2reduce" :
                item2Units--;
                basketTotal--;
                menuItem2amount.setText(item2Units + "");
                basketNumber.setText(basketTotal + "");
                if (item2Units == 0) {
                    menu2reduce.setVisible(false);
                    menuItem2amount.setVisible(false);
                    itemList.remove(itemName2);
                }

                break;
            case "menu3add" :
                item3Units++;
                basketTotal++;
                menu3reduce.setVisible(true);
                menuItem3amount.setText(item3Units + "");
                menuItem3amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName3)) itemList.add(itemName3);
                break;
            case "menu3reduce" :
                item3Units--;
                basketTotal--;
                menuItem3amount.setText(item3Units + "");
                basketNumber.setText(basketTotal + "");
                if (item3Units == 0) {
                    menu3reduce.setVisible(false);
                    menuItem3amount.setVisible(false);
                    itemList.remove(itemName3);
                }
                break;

            case "menu4add" :
                item4Units++;
                basketTotal++;
                menu4reduce.setVisible(true);
                menuItem4amount.setText(item4Units + "");
                menuItem4amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName4)) itemList.add(itemName4);
                break;
            case "menu4reduce" :
                item4Units--;
                basketTotal--;
                menuItem4amount.setText(item4Units + "");
                basketNumber.setText(basketTotal + "");
                if (item4Units == 0) {
                    menu4reduce.setVisible(false);
                    menuItem4amount.setVisible(false);
                    itemList.remove(itemName4);
                }
                break;

            case "menu5add" :
                item5Units++;
                basketTotal++;
                menu5reduce.setVisible(true);
                menuItem5amount.setText(item5Units + "");
                menuItem5amount.setVisible(true);
                basketNumber.setText(basketTotal + "");
                if (!itemList.contains(itemName5)) itemList.add(itemName5);
                break;
            case "menu5reduce" :
                item5Units--;
                basketTotal--;
                menuItem5amount.setText(item5Units + "");
                basketNumber.setText(basketTotal + "");
                if (item5Units == 0) {
                    menu5reduce.setVisible(false);
                    menuItem5amount.setVisible(false);
                    if (itemList.contains(itemName5)) itemList.remove(itemName5);
                }
                break;
        }
        }
    }
