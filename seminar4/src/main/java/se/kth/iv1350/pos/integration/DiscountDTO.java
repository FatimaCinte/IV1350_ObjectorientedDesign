package se.kth.iv1350.pos.integration;

/**
 * Represents discounts to be applied to sale. Instances are immutable.
 */
public class DiscountDTO {
    private final int itemListDiscount;
    private final double netPriceDiscount;
    private final double customerIDDiscount;

    public DiscountDTO(int itemListDiscount, double netPriceDiscount, double customerIDDiscount) {
        this.itemListDiscount = itemListDiscount; 
        this.netPriceDiscount = netPriceDiscount;
        this. customerIDDiscount = customerIDDiscount;
    }

    /**
     * Get the value of itemListDiscount
     * 
     * @return the value of itemListDiscount
     */
    public int getItemListDiscount(){
        return itemListDiscount;
    } 

    /**
     * Get the value of grossPriceDiscount
     * 
     * @return the value of grossPriceDiscount
     */
    public double getNetPriceDiscount(){
        return netPriceDiscount;
    }

    /**
     * Get the value of customerIDDiscount
     * 
     * @return the value of customerIDDiscount
     */
    public double getCustomerIDDiscount(){
        return customerIDDiscount;
    }
}