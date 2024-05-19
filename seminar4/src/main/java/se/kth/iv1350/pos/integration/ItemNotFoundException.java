package se.kth.iv1350.pos.integration;

/**
 * Represents an exception that is thrown when an item is not found in the inventory.
 */
public class ItemNotFoundException extends Exception {

    /**
     * Creates a new instance representing the invalid ID that could not be found.
     *
     * @param itemID The ID of the item that could not be found.
     */
    public ItemNotFoundException(int itemID){
        super("Unable to find item with item ID " + itemID + ".");
    }
}