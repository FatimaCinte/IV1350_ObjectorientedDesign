package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

public class Item {
    private final ItemDTO itemDTO;
    private int quantity;

    Item(ItemDTO itemDTO, int quantity) {
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }
    
    ItemDTO getItemDTO(){
        return itemDTO;
    }

    int getQuantity(){
        return quantity;
    }

    void updateQuantity(int quantity) {
        this.quantity += quantity;
    }
}
