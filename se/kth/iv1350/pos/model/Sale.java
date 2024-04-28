package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.DiscountRequestDTO;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.SaleDTO;
import se.kth.iv1350.pos.integration.BasketDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sale {
    private Basket basket;
    private SaleDTO saleInformation;
	private DiscountRequestDTO discountRequestDTO;
	private Receipt receipt;

    private LocalTime time;
    private LocalDate date;

    public Sale() {
        date = LocalDate.now();
        time = LocalTime.now();
        basket = new Basket();
    }

    public BasketDTO scanItem(ItemDTO itemDTO, int quantity) {
        basket.updateBasket(itemDTO, quantity);
        BasketDTO basketDTO = basket.getBasketDTO();

        return basketDTO; 
    }

    public PriceDetails getPriceDetails() {
        PriceDetails priceDetails = basket.getPriceDetails();
        return priceDetails;
    }

    public SaleDTO getSaleDTO(){
        BasketDTO basketDTO = basket.getBasketDTO();

        saleInformation = new SaleDTO(date, time, basketDTO);

        return saleInformation;
    }

    public Receipt getReceipt(double paidAmount) {
        PriceDetails priceDetails = basket.getPriceDetails();
        double grossPrice = priceDetails.getGrossPrice();
        double changeAmount = calculateChange(paidAmount, grossPrice);

        receipt = new Receipt(saleInformation, paidAmount, changeAmount);

        return receipt;
    }

    private double calculateChange(double paidAmount, double grossPrice){
        return paidAmount - grossPrice;
    }

    public DiscountRequestDTO getDiscountRequestDTO(int customerID) {
        BasketDTO basketDTO = basket.getBasketDTO();
        discountRequestDTO = new DiscountRequestDTO(basketDTO, customerID);
        return discountRequestDTO;
    }

    public void applyDiscount(DiscountDTO discountDTO) {
        basket.applyDiscount(discountDTO);
    }
}
