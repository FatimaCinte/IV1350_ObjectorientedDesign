package se.kth.iv1350.pos.integration;

public class DiscountDBHandler {

    public DiscountDBHandler() {
    }

    public DiscountDTO getDiscount(DiscountRequestDTO discountRequestDTO) {
        return new DiscountDTO(0, 0, 0);
    }
}