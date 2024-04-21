package se.kth.iv1350.pos.model;

import java.util.ArrayList;

import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.InventoryHandler;

public class Basket {
    private int grossPrice;
    private int netPrice;
    private ArrayList<Item> itemList;

    Basket() {
    }

    void updateBasket(int itemID, int quantity, InventoryHandler inventoryHandler) {
        boolean repeatedItem = searchForRepeatedItemInBasket(itemID);
        if (repeatedItem){
            
        }
        else{
            Item item = new Item(itemID, quantity, inventoryHandler);
            addItemToBasket(item);
        }
        updateRunningGrossPrice();
	}

    private void addItemToBasket(Item itemToAdd){
        itemList.add(itemToAdd);
    }

    private boolean searchForRepeatedItemInBasket(int itemID) {
        itemList.forEach((itemInItemList) -> {
            if (itemInItemList.itemID == itemID)
                return true;
            else 
                return false;
        });
    }
    

    private int updateRunningGrossPrice() {

    }

    int getGrossPrice() {
        return grossPrice;
    }

    int getNetPrice() {
        return netPrice;
    }

    int applyDiscount(DiscountDTO discountDTO) {
        return grossPrice;
    }
}
