package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.DiscountDTO;

/**
 * DiscountApplier
 */
public interface DiscountApplier {

    double applyDiscount(double price, DiscountDTO discountDTO);
}