package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.ItemDTO;

public class Item {
    private int itemID;
    private String name;
    private int vatRate;
    private int quantity;

    Item(int itemID, int quantity, InventoryHandler inventoryHandler) {
        ItemDTO itemDTO = inventoryHandler.getItemDTO(itemID);

        this.itemID = itemID;
        this.name = itemDTO.getItemName();
        this.vatRate = itemDTO.getVatRate();
        this.quantity = quantity;
    }
    
}
