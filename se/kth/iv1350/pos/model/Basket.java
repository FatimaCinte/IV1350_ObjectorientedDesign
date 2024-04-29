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
        basketDTO = new BasketDTO(itemList, null,  priceDetails);
    }

    BasketDTO getBasketDTO(){
        return basketDTO;
    }
    
    PriceDetails getPriceDetails() {
        return priceDetails;
    }

    /**
     * If item information belongs to a previously scanned item and already exists in basket, increase the quantity of that item with the given parameter.
     * Otherwise, add the item to the basket. 
     * Update price details.  
     * 
     * @param itemDTO The item information of a scanned item.
     * @param quantity The quantity of a scanned item. 
     */
    void updateBasket(ItemDTO itemDTO, int quantity) {
        int itemID = itemDTO.getItemID();
        boolean repeatedItem = searchForRepeatedItemInBasket(itemID);
        if (repeatedItem) {
            updateQuantityOfItemInList(quantity, itemID);
        }
        else {
            Item item = new Item(itemDTO, quantity);
            addItemToBasket(item);
        }
        updateRunningPriceDetails();
	}

    private void updateQuantityOfItemInList(int quantity, int itemID){
        itemList.forEach((itemInItemList) -> {
            ItemDTO currentItemInformation = itemInItemList.getItemDTO();
            if (currentItemInformation.getItemID() == itemID) {
                itemInItemList.updateQuantity(quantity);
                addLatestItemToBasketDTO(itemInItemList);
            }
        });
        
    }
    
    private void addItemToBasket(Item itemToAdd){
        itemList.add(itemToAdd);
        addLatestItemToBasketDTO(itemToAdd);
    }

    private boolean searchForRepeatedItemInBasket(int itemID) {
        for (int i = 0; i < itemList.size(); i++) {
            ItemDTO currentItemInformation = itemList.get(i).getItemDTO();
            if (currentItemInformation.getItemID() == itemID) {
                return true;
            }
        }
        return false;
    }
    
    private void updateRunningPriceDetails() {
        double grossPrice = 0;
        double netPrice = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Item itemInList = itemList.get(i);
            ItemDTO itemInListInformation = itemInList.getItemDTO();
            
            double itemGrossPrice = itemInListInformation.getItemNetPrice();
            int itemQuantity = itemInList.getQuantity();
            int itemVatRate = itemInListInformation.getVatRate();

            grossPrice += itemGrossPrice * itemQuantity;
            netPrice += grossPrice + grossPrice * (itemVatRate/100);
        }

        priceDetails = new PriceDetails(grossPrice, netPrice);
    }

    private void addLatestItemToBasketDTO (Item latestItem){
        basketDTO = new BasketDTO(itemList, latestItem,  priceDetails);
    }

    /**
     * Applies discount to, and updates price details. 
     * 
     * @param discountDTO The discounts to be applied to the price details.
     */
    void applyDiscount(DiscountDTO discountDTO) {
        double grossPrice = priceDetails.getGrossPrice();
        double netPrice = priceDetails.getNetPrice();

        grossPrice = applyDiscount(grossPrice, discountDTO);
        netPrice = applyDiscount(netPrice, discountDTO);

        priceDetails = new PriceDetails(grossPrice, netPrice);
    }

    private double applyDiscount(double price, DiscountDTO discountDTO){
        double itemListDiscount = discountDTO.getItemListDiscount();
        double grossPriceDiscount = discountDTO.getGrossPriceDiscount();
        double customerIDDiscount = discountDTO.getCustomerIDDiscount();

        price = price * (1 - grossPriceDiscount) * (1 - customerIDDiscount) - itemListDiscount;
        
        return price;
    }

    
}
