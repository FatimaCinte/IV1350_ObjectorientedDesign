package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

public class InventoryHandler {

    private final ItemDTO oatmeal = new ItemDTO(123, "BigWheel Oatmeal", 29.90, 6);
    private final ItemDTO yoghurt = new ItemDTO(456, "YouGoGo Blueberry", 14.90, 6);

    private ArrayList<ItemDTO> inventory;

    public InventoryHandler(){
        inventory = new ArrayList<ItemDTO>();
        inventory.add(oatmeal);
        inventory.add(yoghurt);
    }

    public ItemDTO getItemDTO(int itemID) {
        for (ItemDTO itemDTO : inventory) {
            if (itemDTO.getItemID() == itemID) {
                return itemDTO;
            }
        }
        return null;
    }

    public void updateInventory(SaleDTO saleInforamtion){
        
    }

    

}