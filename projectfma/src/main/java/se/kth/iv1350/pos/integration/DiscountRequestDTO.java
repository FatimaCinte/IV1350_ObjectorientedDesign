package se.kth.iv1350.pos.integration;

public class DiscountRequestDTO {
    private BasketDTO basketDTO;
    private int customerID;

    public DiscountRequestDTO(BasketDTO basketDTO, int customerID){
        this.basketDTO = basketDTO;
        this.customerID = customerID;
    }

    /**
     * Get the value of basketDTO
     * 
     * @return the value of basketDTO
     */
    public BasketDTO geBasketDTO(){
        return basketDTO;
    }

    /**
     * Get the value of customerID
     * 
     * @return the value of customerID
     */
    public int getCustomerID(){
        return customerID;
    }

}
