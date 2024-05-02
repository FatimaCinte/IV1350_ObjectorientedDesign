package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

import se.kth.iv1350.pos.model.Item;

public class BasketDTO {
    private final ArrayList<Item> itemList;
    private final Item latestAddedItem;
    private final PriceDetails priceDetails;

    /**
     * 
     */
    public BasketDTO(ArrayList<Item> itemList, Item latestAddItem, PriceDetails priceDetails) {
        this.itemList = itemList;
        this.latestAddedItem = latestAddItem;
        this.priceDetails = priceDetails;
    }

    /**
     * Gets the list of items in the BasketDTO
     * 
     * @return An ArrayList containing all Items in the BasketDTO
     */
    public ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * Gets the latest Item added to the BasketDTO
     * 
     * @return 
     */
    public Item getLatestItem(){
        return latestAddedItem;
    }

    public PriceDetails getPriceDetails() {
        return priceDetails;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof BasketDTO)){
            return false;
        }
        BasketDTO other = (BasketDTO) object;
        return this == other;
    }

}
