package se.kth.iv1350.pos.integration;

import se.kth.iv1350.pos.model.Basket;

public class DiscountRequestDTO {
    private Basket basket;
    private int customerID;

    public DiscountRequestDTO(Basket basket, int customerID){
        this.basket = basket;
        this.customerID = customerID;
    }

}
