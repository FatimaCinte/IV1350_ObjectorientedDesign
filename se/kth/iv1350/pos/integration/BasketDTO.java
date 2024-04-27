package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.PriceDetails;

public class BasketDTO {
    private final ArrayList<Item> itemList;
    private final Item latestAddedItem;
    private final PriceDetails priceDetails;

    public BasketDTO(ArrayList<Item> itemList, Item latestAddItem, PriceDetails priceDetails) {
        this.itemList = itemList;
        this.latestAddedItem = latestAddItem;
        this.priceDetails = priceDetails;
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public PriceDetails getPriceDetails() {
        return this.priceDetails;
    }

}
