package se.kth.iv1350.pos.integration;

import java.util.ArrayList;

import se.kth.iv1350.pos.model.Item;

public class InventoryHandler {

    private final int databaseDown = 666;

    private final ItemDTO oatmeal = new ItemDTO(123, "BigWheel Oatmeal", 29.90, 6);
    private final ItemDTO yoghurt = new ItemDTO(456, "YouGoGo Blueberry", 14.90, 6);
    private final ItemDTO pasta = new ItemDTO(1, "Barilla Spaghetti", 20, 6);
    private final ItemDTO bread = new ItemDTO(2, "Varsågod", 13, 6);

    private ArrayList<ItemDTO> inventory;

    /**
     * Creates an instance of an inventory handler
     */
    public InventoryHandler(){
        inventory = new ArrayList<ItemDTO>();
        addItemtoInventory(oatmeal);
        addItemtoInventory(oatmeal);
        addItemtoInventory(yoghurt);
        addItemtoInventory(pasta);
        addItemtoInventory(bread);
    }

    private void addItemtoInventory(ItemDTO itemToAdd){
        inventory.add(itemToAdd);
    }

    /**
     * Get the item information about an item with a given id.
     * @param itemID The requested item's id. 
     * @return Item information about an item with a given id.
     * @throws ItemNotFoundException if an unknown itemID is scanned
     * @throws DatabaseConnectionExceptionTest if the database call failed
     */
    public ItemDTO getItemDTO(int itemID) throws ItemNotFoundException {
        if (itemID == databaseDown) {
            throw new DatabaseConnectionException();
        }
        for (ItemDTO itemDTO : inventory) {
            if (itemDTO.getItemID() == itemID) {
                return itemDTO;
            }
        }
        throw new ItemNotFoundException(itemID);
    }

    /**
     * Updates inventory with the items sold at sale.
     * @param saleInforamtion contains the information about the sold items.
     */
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