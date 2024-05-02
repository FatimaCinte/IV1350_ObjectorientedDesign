package se.kth.iv1350.pos.integration;

/**
 * Represents discounts to be applied to sale. Instances are immutable.
 */
public class DiscountDTO {
    private final int itemListDiscount;
    private final double grossPriceDiscount;
    private final double customerIDDiscount;

    public DiscountDTO(int itemListDiscount, double grossPriceDiscount, double customerIDDiscount) {
        this.itemListDiscount = itemListDiscount; 
        this.grossPriceDiscount = grossPriceDiscount;
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
    public double getGrossPriceDiscount(){
        return grossPriceDiscount;
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