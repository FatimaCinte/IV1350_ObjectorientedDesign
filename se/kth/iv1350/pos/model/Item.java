package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

public class Item {
    private int itemID;
    private String name;
    private int vatRate;
    private int quantity;

    Item(ItemDTO itemDTO, int quantity) {
        this.itemID = itemDTO.getItemID();
        this.name = itemDTO.getItemName();
        this.vatRate = itemDTO.getVatRate();
        this.quantity = quantity;
    }
    
    int getItemID() {
        return this.itemID;
    }

    void updateQuantity(int quantity) {
        this.quantity += quantity;
    }
}
