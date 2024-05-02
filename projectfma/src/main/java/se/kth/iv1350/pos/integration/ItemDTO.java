package se.kth.iv1350.pos.integration;

public class ItemDTO {
    private final int itemID;
    private final String name;
    private final double itemNetPrice;
    private final int vatRate;

    /**
     * Creates an instance of an itemDTO
     * 
     * @param itemID The id of the item
     * @param name The name of the item
     * @param itemNetPrice The net price of the item
     * @param vatRate The vat rate of the item
     */
    public ItemDTO(int itemID, String name, double itemNetPrice, int vatRate) {
        this.itemID = itemID;
        this.name = name;
        this.itemNetPrice = itemNetPrice;
        this.vatRate = vatRate;
    }

    /**
     * Get the value of itemID
     * 
     * @return the value of itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Get the value of itemName
     * 
     * @return the name of the item
     */
    public String getItemName() {
        return name;
    }

    /**
     * Get the value of itemNetPrice
     * 
     * @return the value of itemNetPrice
     */
    public double getItemNetPrice(){
        return itemNetPrice;
    }

    /**
     * Get the value of vatRate
     * 
     * @return the value of vatRate
     */
    public int getVatRate() {
        return vatRate;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof ItemDTO)){
            return false;
        }
        ItemDTO other = (ItemDTO) object;
        return this.itemID == other.itemID;
    }
}