package se.kth.iv1350.pos.model;

import java.time.LocalDate;
import java.time.LocalTime;

import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.DiscountRequestDTO;
import se.kth.iv1350.pos.integration.SaleDTO;
import se.kth.iv1350.pos.integration.InventoryHandler;

public class Sale {
    private LocalTime time;
    private LocalDate date;
    
    private Basket basket;
    
    private SaleDTO saleInformation;

	private DiscountRequestDTO discountRequestDTO;

	private Receipt receipt;

    public Sale() {
        basket = new Basket();
    }

    public Basket scanItem(int itemID, int quantity, InventoryHandler inventoryHandler) {
        basket.updateBasket(itemID, quantity, inventoryHandler);

        return basket; 
    }

    public int getGrossPrice() {
        int grossPrice = basket.getGrossPrice();
        return grossPrice;
    }

    public SaleDTO getSaleDTO(){
        int grossPrice = getGrossPrice();
        int netPrice = basket.getNetPrice();
        date = LocalDate.now();
        time = LocalTime.now();

        saleInformation = new SaleDTO(date, time, grossPrice, netPrice);

        return saleInformation;
    }

    public Receipt getReceipt(int paidAmount) {
        int grossPrice = basket.getGrossPrice();
        int changeAmount = calculateChange(paidAmount, grossPrice);

        Receipt receipt = new Receipt(saleInformation, paidAmount, changeAmount);

        return receipt;
    }

    private int calculateChange(int paidAmount, int grossPrice){
        return paidAmount - grossPrice;
    }

    public DiscountRequestDTO getDiscountRequestDTO(int customerID) {
        discountRequestDTO = new DiscountRequestDTO(this.basket, customerID);
        return discountRequestDTO;
    }

    public int applyDiscount(DiscountDTO discountDTO) {
        int grossPrice = basket.applyDiscount(discountDTO);
        
        return grossPrice;
    }
}
