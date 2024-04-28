package se.kth.iv1350.pos.integration;

public class ItemDTO {
    private final int itemID;
    private final String name;
    private final double itemNetPrice;
    private final int vatRate;

    public ItemDTO(int itemID, String name, double itemNetPrice, int vatRate) {
        this.itemID = itemID;
        this.name = name;
        this.itemNetPrice = itemNetPrice;
        this.vatRate = vatRate;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return name;
    }

    public double getItemNetPrice(){
        return itemNetPrice;
    }

    public int getVatRate() {
        return vatRate;
    }
}