package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.DiscountRequestDTO;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.integration.SaleDTO;
import se.kth.iv1350.pos.integration.BasketDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sale. 
 * The interface for controller to the model layer.
 */
public class Sale {
    private Basket basket;

    private LocalTime time;
    private LocalDate date;
    private List<SaleObserver> saleObservers;

    /**
     * Creates an instance of Sale.
     */
    public Sale(List<SaleObserver> saleObservers) {
        date = LocalDate.now();
        time = LocalTime.now();
        basket = new Basket();
        this.saleObservers = saleObservers;
    }

    private void notifyObservers() {
        for (SaleObserver obs : saleObservers) {
            obs.saleEnd(getPriceDetails());
        }
    }

    public void addObserver(SaleObserver obs){
        saleObservers.add(obs);
    }

    /**
     * Updates basket, and returns basket information.
     * 
     * @param itemDTO The item information of the scanned item.
     * @param quantity The quantity of the scanned item.
     * @return The basket information containing list of items, price details, and the last added item.
     */
    public BasketDTO scanItem(ItemDTO itemDTO, int quantity) {
        basket.updateBasket(itemDTO, quantity);
        BasketDTO basketDTO = basket.getBasketDTO();

        return basketDTO; 
    }

    /**
     * Get the value of priceDetails from the basket
     * 
     * @return the value of priceDetails
     */
    public PriceDetails getPriceDetails() {
        PriceDetails priceDetails = basket.getPriceDetails();
        return priceDetails;
    }

    /**
     * Returns the sale information.
     * 
     * @return The sale information.
     */
    public SaleDTO getSaleDTO(){
        SaleDTO saleInformation = createSaleDTO();
        notifyObservers();
        return saleInformation;
    }

    /**
     * Creates a new receipt, and returns it. 
     * 
     * @param paidAmount The amount the customer paid for the sale.
     * @return An instance of receipt, as confirmation of the sale.
     */
    public Receipt getReceipt(double paidAmount) {
        PriceDetails priceDetails = basket.getPriceDetails();
        double netPrice = priceDetails.getNetPrice();
        double changeAmount = calculateChange(paidAmount, netPrice);

        SaleDTO saleInformation = createSaleDTO();
        Receipt receipt = new Receipt(saleInformation, paidAmount, changeAmount);

        return receipt;
    }

    private SaleDTO createSaleDTO(){
        BasketDTO basketDTO = basket.getBasketDTO();
        SaleDTO saleInformation = new SaleDTO(date, time, basketDTO);
        return saleInformation;
    }

    public DiscountRequestDTO getDiscountRequestDTO(int customerID) {
        BasketDTO basketDTO = basket.getBasketDTO();
        DiscountRequestDTO discountRequestDTO = new DiscountRequestDTO(basketDTO, customerID);
        return discountRequestDTO;
    }

    /**
     * Applies discount to basket. 
     * 
     * @param discountDTO The discount to be applied to sale. 
     */
    public void applyDiscount(DiscountDTO discountDTO) {
        basket.applyDiscount(discountDTO);
    }

    private double calculateChange(double paidAmount, double netPrice){
        return paidAmount - netPrice;
    }
}