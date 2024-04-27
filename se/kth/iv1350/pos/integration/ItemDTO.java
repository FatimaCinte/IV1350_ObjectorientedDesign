package se.kth.iv1350.pos.integration;

public class ItemDTO {
    private final int itemID;
    private final String name;
    private final int vatRate;

    public ItemDTO() {
        this.itemID = 0;
        this.name = "";
        this.vatRate = 0;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return name;
    }

    public int getVatRate() {
        return vatRate;
    }
}