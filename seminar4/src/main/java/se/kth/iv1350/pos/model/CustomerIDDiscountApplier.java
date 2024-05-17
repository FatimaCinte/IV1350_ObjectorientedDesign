package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.DiscountDTO;

/**
 * CustomerIDDiscountApplier
 */
public class CustomerIDDiscountApplier implements DiscountApplier {
    private final int CHANGE_FACTOR_ONE = 1;

    public CustomerIDDiscountApplier(){
    }

    @Override
    public double applyDiscount(double price, DiscountDTO discountDTO) {
        double customerIDDiscount = discountDTO.getCustomerIDDiscount();
        price = price * (CHANGE_FACTOR_ONE - customerIDDiscount);
        return price;
    }
}