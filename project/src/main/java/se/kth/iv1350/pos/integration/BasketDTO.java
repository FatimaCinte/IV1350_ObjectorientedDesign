package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

import se.kth.iv1350.pos.model.Item;


/**
 * Represents the data basket holds. Instances are immutable.
 */
public class BasketDTO {
    private final ArrayList<Item> itemList;
    private final Item latestAddedItem;
    private final PriceDetails priceDetails;

    /**
     * Creates an instance of a BasketDTO
     *
     * @param itemList a list of the Items in the BasketDTO
     * @param latestAddItem the latest added Item to the BasketDTO
     * @param priceDetails the PriceDetails of the BasketDTO
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
     * @return the latest Item added to the BasketDTO
     */
    public Item getLatestItem(){
        return latestAddedItem;
    }

    /**
     * Get the priceDetails of the BasketDTO
     *
     * @return the priceDetails of the BasketDTO
     */
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
