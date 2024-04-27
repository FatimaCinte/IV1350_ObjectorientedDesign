package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.Sale;
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

    public void startSale() {
        sale = new Sale();
    }

    public double endSale() {
        double grossPrice = sale.getGrossPrice();

        return grossPrice;
    }

    public BasketDTO scanItem(int itemID, int quantity) {
        ItemDTO itemDTO = inventoryHandler.getItemDTO(itemID);
        BasketDTO basketDTO = sale.scanItem(itemDTO, quantity);
        return basketDTO;
    }

    public int presentPayment(int paidAmount) {
        SaleDTO saleInformation = sale.getSaleDTO();
        accountingHandler.updateAccounting(saleInformation);
        inventoryHandler.updateInventory(saleInformation);
        
        Receipt receipt = sale.getReceipt(paidAmount);
        printerHandler.printReceipt(receipt);

        return 0;
    }

    public double requestDiscount(int customerID) {
        DiscountRequestDTO discountRequestDTO = sale.getDiscountRequestDTO(customerID);
        DiscountDTO discountDTO = discountDBHandler.getDiscount(discountRequestDTO);
        double grossPrice = sale.applyDiscount(discountDTO);
        return grossPrice;
    }
}
