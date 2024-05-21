package se.kth.iv1350.pos.model.discountappliers;

import se.kth.iv1350.pos.integration.DiscountDTO;

/**
 * NetPriceDiscountApplier
 */
class NetPriceDiscountApplier implements DiscountApplier {
    private static final int CHANGE_FACTOR_ONE = 1;

    NetPriceDiscountApplier() {
    }

    @Override
    public double applyDiscount(double price, DiscountDTO discountDTO) {
        double netPriceDiscount = discountDTO.getNetPriceDiscount();
        price = price * (CHANGE_FACTOR_ONE - netPriceDiscount);
        return price;
    }
}