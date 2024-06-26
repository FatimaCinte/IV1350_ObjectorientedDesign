package se.kth.iv1350.pos.model.discountappliers;

import java.util.ArrayList;

import se.kth.iv1350.pos.integration.DiscountDTO;

class CompositeDiscountApplier implements DiscountApplier {
    private ArrayList<DiscountApplier> discountAppliers;

    CompositeDiscountApplier() {
        discountAppliers = new ArrayList<DiscountApplier>();
    }

    @Override
    public double applyDiscount(double price, DiscountDTO discountDTO) {
        for (DiscountApplier discountApplier : discountAppliers) {
            price = discountApplier.applyDiscount(price, discountDTO);
        }
        return price;
    }

    void addApplier(DiscountApplier discountApplier) {
        discountAppliers.add(discountApplier);
    }
}