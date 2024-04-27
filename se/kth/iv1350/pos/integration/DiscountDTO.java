package se.kth.iv1350.pos.integration;

public class DiscountDTO {
    private final int itemListDiscount;
    private final double grossPriceDiscount;
    private final double customerIDDiscount;

    public DiscountDTO(int itemListDiscount, double grossPriceDiscount, double customerIDDiscount) {
        this.itemListDiscount = itemListDiscount; 
        this.grossPriceDiscount = grossPriceDiscount;
        this. customerIDDiscount = customerIDDiscount;
    }

    public int getItemListDiscount(){
        return itemListDiscount;
    } 

    public double getGrossPriceDiscount(){
        return grossPriceDiscount;
    }

    public double getCustomerIDDiscount(){
        return customerIDDiscount;
    }
}