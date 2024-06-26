package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.AccountingHandler;
import se.kth.iv1350.pos.integration.DiscountDBHandler;
import se.kth.iv1350.pos.integration.PrinterHandler;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.view.View;

public class Main {

    /**
     * Starts the systems of the sale process up.
     * @param args 
     */
	public static void main(String[] args) {
        AccountingHandler accountingHandler = new AccountingHandler();
        InventoryHandler inventoryHandler = new InventoryHandler();
        DiscountDBHandler discountDBHandler = new DiscountDBHandler();
        PrinterHandler printerHandler = new PrinterHandler();

        Controller controller = new Controller(accountingHandler, inventoryHandler, discountDBHandler, printerHandler);
        
        View view = new View(controller);
        
        
        view.fakeExecutionRun();
        //view.fakeBasicExecutionRun();


    }
}
