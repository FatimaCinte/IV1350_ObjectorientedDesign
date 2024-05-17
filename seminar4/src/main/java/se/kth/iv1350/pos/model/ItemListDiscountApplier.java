package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.DiscountDTO;

/**
 * ItemListDiscountApplier
 */
public class ItemListDiscountApplier implements DiscountApplier {

    public ItemListDiscountApplier() {
    }

    @Override
    public double applyDiscount(double price, DiscountDTO discountDTO) {
        int itemListDiscount = discountDTO.getItemListDiscount();
        price = price - itemListDiscount;
        return price;
    }
}