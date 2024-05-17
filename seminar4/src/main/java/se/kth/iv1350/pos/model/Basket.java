package se.kth.iv1350.pos.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.integration.BasketDTO;
import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.discountappliers.DiscountApplier;
import se.kth.iv1350.pos.model.discountappliers.DiscountApplierFactory;

class Basket {
    private List<BasketObserver> basketObservers = new ArrayList<>();

    private ArrayList<Item> itemList;
    private Item latestItem;
    private PriceDetails priceDetails;

    private static final int CHANGE_FACTOR_ONE = 1;
    private static final double ONE_HUNDRED_PERCENT = 100.0;

    /**
     * Creates an instance of Basket
     */
    Basket(List<BasketObserver> basketObservers) {
        itemList = new ArrayList<Item>();
        latestItem = null;
        priceDetails = new PriceDetails(0, 0);
        this.basketObservers = basketObservers;
    }

    private void notifyObservers() {
        for (BasketObserver obs : basketObservers) {
            obs.newScan(priceDetails);
        }
    }

    public void addObservers(BasketObserver obs){
        basketObservers.add(obs);
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
        notifyObservers();
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
        return netPrice - netPrice * (itemVatRate/(ONE_HUNDRED_PERCENT + itemVatRate));
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

    private double applyDiscount(double price, DiscountDTO discountDTO) {
        return DiscountApplierFactory.getDiscountApplierFactory().getDefaultDiscountApplier().applyDiscount(price, discountDTO);
    }

    
}
