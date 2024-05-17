package se.kth.iv1350.pos.model.discountappliers;

import se.kth.iv1350.pos.integration.DiscountDTO;

/**
 * ItemListDiscountApplier
 */
ItemListDiscountApplier implements DiscountApplier {

    ItemtDiscountApplier() {
    }

    @Override
    public public double applyDiscount(double price, DiscountDTO discountDTO) {
        int itemListDiscount = discountDTO.getItemListDiscount();
        price = price - itemListDiscount;
        return price;
    }
}