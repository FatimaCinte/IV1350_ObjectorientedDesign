package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.ItemDTO;

public class Item {
    private final ItemDTO itemDTO;
    private int quantity;

    Item(ItemDTO itemDTO, int quantity) {
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }
    
    /**
     * Get the value of itemDTO
     * 
     * @return the value of itemDTO
     */
    public ItemDTO getItemDTO(){
        return itemDTO;
    }

    /**
     * Get the value of quantity
     * 
     * @return the value of quantity
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Updates the quantity.
     * 
     * @param quantity The amount to change this instance's quantity with.
     */
    void updateQuantity(int quantity) {
        this.quantity += quantity;
    }
        
  
}
