package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.AccountingHandler;
import se.kth.iv1350.pos.integration.DiscountDBHandler;
import se.kth.iv1350.pos.integration.DiscountDTO;
import se.kth.iv1350.pos.integration.DiscountRequestDTO;
import se.kth.iv1350.pos.integration.PrinterHandler;
import se.kth.iv1350.pos.integration.SaleDTO;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.Basket;
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

    public int endSale() {
        int grossPrice = sale.getGrossPrice();

        return grossPrice;
    }

    public Basket scanItem(int itemID, int quantity) {
        Basket basket = sale.scanItem(itemID, quantity, inventoryHandler);
        return basket;
    }

    public int presentPayment(int paidAmount) {
        SaleDTO saleInformation = sale.getSaleDTO();
        accountingHandler.updateAccounting(saleInformation);
        inventoryHandler.updateInventory(saleInformation);
        
        Receipt receipt = sale.getReceipt(paidAmount);
        printerHandler.printReceipt(receipt);

        return 0;
    }

    public int requestDiscount(int customerID) {
        DiscountRequestDTO discountRequestDTO = sale.getDiscountRequestDTO(customerID);
        DiscountDTO discountDTO = discountDBHandler.getDiscount(discountRequestDTO);
        int grossPrice = sale.applyDiscount(discountDTO);
        return grossPrice;
    }
}
