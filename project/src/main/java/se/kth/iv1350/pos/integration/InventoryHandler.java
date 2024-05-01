package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

import se.kth.iv1350.pos.model.Item;

public class InventoryHandler {

    private final ItemDTO oatmeal = new ItemDTO(123, "BigWheel Oatmeal", 29.90, 6);
    private final ItemDTO yoghurt = new ItemDTO(456, "YouGoGo Blueberry", 14.90, 6);
    private final ItemDTO pasta = new ItemDTO(1, "Barilla Spaghetti", 20, 6);
    private final ItemDTO bread = new ItemDTO(2, "Varsågod", 13, 6);

    private ArrayList<ItemDTO> inventory;

    public InventoryHandler(){
        inventory = new ArrayList<ItemDTO>();
        inventory.add(oatmeal);
        inventory.add(yoghurt);
        inventory.add(pasta);
        inventory.add(bread);
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
        ArrayList<Item> itemList = saleInforamtion.getItemList();
        for (Item itemInList : itemList) {
            System.out.println("Told external inventory system to decrease inventory quantity of item " 
            + itemInList.getItemDTO().getItemID() 
            + " by " 
            + itemInList.getQuantity());
        }
    }

}