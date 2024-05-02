package se.kth.iv1350.pos.model;

import java.util.ArrayList;

import se.kth.iv1350.pos.integration.BasketDTO;
import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.PriceDetails;

class Basket {
    private ArrayList<Item> itemList;
    private Item latestItem;
    private PriceDetails priceDetails;

    /**
     * Creates an instance of Basket
     */
    Basket() {
        itemList = new ArrayList<Item>();
        latestItem = null;
        priceDetails = new PriceDetails(0, 0);
    }

    /**
     * Get the value of basketDTO
     * 
     * @return the value of basketDTO
     */
    BasketDTO getBasketDTO(){
        BasketDTO basketDTO = new BasketDTO(itemList, latestItem, priceDetails);
        return basketDTO;
    }
    
    /**
     * Get the value of priceDetails
     * 
     * @return the value of priceDetails
     */
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
        updateRunningPriceDetails(itemDTO, quantity);
	}

    private void updateQuantityOfItemInList(int quantity, int itemID){
        itemList.forEach((itemInItemList) -> {
            ItemDTO currentItemInformation = itemInItemList.getItemDTO();
            if (currentItemInformation.getItemID() == itemID) {
                itemInItemList.updateQuantity(quantity);
                latestItem = itemInItemList;
            }
        });
    }
    
    private void addItemToBasket(Item itemToAdd){
        itemList.add(itemToAdd);
        latestItem = itemToAdd;
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

    private void updateRunningPriceDetails(ItemDTO itemDTO, int quantity) {
        double newItemNetPrice = itemDTO.getItemNetPrice() * quantity;
        int newItemVat = itemDTO.getVatRate();
        double newItemGrossPrice = calculateGrossPriceFromNetPrice(newItemNetPrice, newItemVat);

        double netPrice = priceDetails.getNetPrice() + newItemNetPrice;
        double grossPrice = priceDetails.getGrossPrice() + newItemGrossPrice;

        priceDetails = new PriceDetails(grossPrice, netPrice);
    }

    private double calculateGrossPriceFromNetPrice(double netPrice, int itemVatRate) {
        return netPrice - netPrice * (itemVatRate/(100.0 + itemVatRate));
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
