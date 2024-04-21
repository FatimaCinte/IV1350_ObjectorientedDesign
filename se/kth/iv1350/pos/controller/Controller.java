package se.kth.iv1350.pos.controller;

import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.AccountingHandler;
import se.kth.iv1350.pos.integration.DiscountDBHandler;
import se.kth.iv1350.pos.integration.PrinterHandler;

public class Controller {
	private AccountingHandler accountingHandler;

    private InventoryHandler inventoryHandler;

	private DiscountDBHandler discountDBHandler;

	private PrinterHandler printerHandler;

    public Controller(AccountingHandler accountingHandler, InventoryHandler inventoryHandler, DiscountDBHandler discountDBHandler, PrinterHandler printerHandler){
        this.accountingHandler = accountingHandler;
        this.inventoryHandler = inventoryHandler;
        this.discountDBHandler = discountDBHandler;
        this.printerHandler = printerHandler;
    }
}
