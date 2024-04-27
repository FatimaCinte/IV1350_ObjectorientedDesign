package se.kth.iv1350.pos.integration;

public class DiscountRequestDTO {
    private BasketDTO basketDTO;
    private int customerID;

    public DiscountRequestDTO(BasketDTO basketDTO, int customerID){
        this.basketDTO = basketDTO;
        this.customerID = customerID;
    }

    public BasketDTO geBasketDTO(){
        return basketDTO;
    }

    public int getCustomerID(){
        return customerID;
    }

}
