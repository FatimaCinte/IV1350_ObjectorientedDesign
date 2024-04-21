package se.kth.iv1350.pos.integration;

public class ItemDTO {
    private final int itemID;
    private final String name;
    private final int vatRate;

    public ItemDTO() {
        itemID = 0;
        name = "banan";
        vatRate = 100;
    }

    public int getItemID() {
        return this.itemID;
    }

    public String getItemName() {
        return this.name;
    }

    public int getVatRate() {
        return this.vatRate;
    }
}