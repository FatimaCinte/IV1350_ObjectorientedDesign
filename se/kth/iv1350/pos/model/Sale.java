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
    private BasketDTO basketDTO;
    private SaleDTO saleInformation;
	private DiscountRequestDTO discountRequestDTO;
	private Receipt receipt;
    private PriceDetails priceDetails;

    private LocalTime time;
    private LocalDate date;

    public Sale() {
        basket = new Basket();
    }

    public BasketDTO scanItem(ItemDTO itemDTO, int quantity) {
        basketDTO = basket.updateBasket(itemDTO, quantity);

        return basketDTO; 
    }

    public double getGrossPrice() {
        priceDetails = basket.getPriceDetails();
        double grossPrice = priceDetails.getGrossPrice();

        return grossPrice;
    }

    public SaleDTO getSaleDTO(){
        date = LocalDate.now();
        time = LocalTime.now();
        saleInformation = new SaleDTO(date, time, basketDTO);

        return saleInformation;
    }

    public Receipt getReceipt(int paidAmount) {
        double grossPrice = getGrossPrice();
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

    public double applyDiscount(DiscountDTO discountDTO) {
        double grossPrice = basket.applyDiscount(discountDTO);
        
        return grossPrice;
    }
}
