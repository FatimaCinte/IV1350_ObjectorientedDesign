package se.kth.iv1350.pos.model;

import java.util.ArrayList;

import se.kth.iv1350.pos.integration.BasketDTO;
import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.ItemDTO;

public class Basket {
    private ArrayList<Item> itemList;
    private PriceDetails priceDetails;
    private BasketDTO basketDTO;

    Basket() {
        itemList = new ArrayList<Item>();
        priceDetails = new PriceDetails(0, 0);
        basketDTO = new BasketDTO(itemList, priceDetails);
    }

    BasketDTO updateBasket(ItemDTO itemDTO, int quantity) {
        int itemID = itemDTO.getItemID();
        boolean repeatedItem = searchForRepeatedItemInBasket(itemID);
        if (repeatedItem) {
            updateQuantityOfItemInList(quantity, itemID);
        }
        else {
            Item item = new Item(itemDTO, quantity);
            addItemToBasket(item);
        }
        updateRunningGrossPrice();
        
        return basketDTO;
	}

    private void updateQuantityOfItemInList(int quantity, int itemID){
        itemList.forEach((itemInItemList) -> {
            if (itemInItemList.getItemID() == itemID) {
                itemInItemList.updateQuantity(quantity); 
            }
        });    
    }
    
    private void addItemToBasket(Item itemToAdd){
        itemList.add(itemToAdd);
    }

    private boolean searchForRepeatedItemInBasket(int itemID) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getItemID() == itemID) {
                return true;
            }
        }
        return false;
    }
    
    private void updateRunningGrossPrice() {
        
    }

    BasketDTO getBasketDTO(){
        return basketDTO;
    }
    
    PriceDetails getPriceDetails() {
        return priceDetails;
    }

    double applyDiscount(DiscountDTO discountDTO) {
        int itemListDiscount = discountDTO.getItemListDiscount();
        double grossPriceDiscount = discountDTO.getGrossPriceDiscount();
        double customerIDDiscount = discountDTO.getCustomerIDDiscount();

        grossPrice = grossPrice * (1 - grossPriceDiscount) * (1 - customerIDDiscount) - itemListDiscount;

        return grossPrice;
    }
}
