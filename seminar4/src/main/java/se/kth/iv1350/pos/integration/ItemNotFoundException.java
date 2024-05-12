package se.kth.iv1350.pos.integration;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException(int itemID){
        super("Unable to find item with item ID " + itemID + ".");
    }
}