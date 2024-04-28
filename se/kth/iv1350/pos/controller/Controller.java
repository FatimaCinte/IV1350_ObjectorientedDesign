package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.PriceDetails;
import se.kth.iv1350.pos.model.Receipt;

public class Controller {
	private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
	private DiscountDBHandler discountDBHandler;
	private PrinterHandler printerHandler;

    private Sale sale;

    public Controller(AccountingHandler accountingHandler, InventoryHandler inventoryHandler, DiscountDBHandler discountDBHandler, PrinterHandler printerHandler) {
        this.accountingHandler = accountingHandler;
        this.inventoryHandler = inventoryHandler;
        this.discountDBHandler = discountDBHandler;
        this.printerHandler = printerHandler;
    }

    /**
     * Creates an instance of Sale. 
     */
    public void startSale() {
        sale = new Sale();
    }

    /**
     * Calls sale with the scanned item information, and the quantity of the scanned item. 
     * 
     * @param itemID The id of the scanned item.
     * @param quantity The quantity of the scanned item. 
     * @return The BasketDTO representing the current Basket. 
     */
    public BasketDTO scanItem(int itemID, int quantity) {
        ItemDTO itemDTO = inventoryHandler.getItemDTO(itemID);
        BasketDTO basketDTO = sale.scanItem(itemDTO, quantity);
        return basketDTO;
    }

    /**
     * Gets price details from sale. 
     * 
     * @return The price details at the end of the sale. 
     */
    public PriceDetails endSale() {
        PriceDetails priceDetails = sale.getPriceDetails();
        return priceDetails;
    }

    /**
     * Applies discount to sale. 
     * 
     * @param customerID The id of the customer asking for a discount.
     * @return The price details after discount. 
     */
    public PriceDetails requestDiscount(int customerID) {
        DiscountRequestDTO discountRequestDTO = sale.getDiscountRequestDTO(customerID);
        DiscountDTO discountDTO = discountDBHandler.getDiscount(discountRequestDTO);
        
        sale.applyDiscount(discountDTO);
        PriceDetails priceDetails = sale.getPriceDetails();
        
        return priceDetails;
    }

    /**
     * Updates external accounting, and external inventory.
     * Prints receipt.
     * 
     * @param paidAmount The amount the customer is paying for the sale.
     * @return The amount of chanage the customer should receive. 
     */
    public double presentPayment(double paidAmount) {
        SaleDTO saleInformation = sale.getSaleDTO();
        accountingHandler.updateAccounting(saleInformation);
        inventoryHandler.updateInventory(saleInformation);
        
        Receipt receipt = sale.getReceipt(paidAmount);
        printerHandler.printReceipt(receipt);

        double changeAmount = receipt.getChangeAmount();

        return changeAmount;
    }
}
