package se.kth.iv1350.pos.view;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.AccountingHandler;
import se.kth.iv1350.pos.integration.DiscountDBHandler;
import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.PrinterHandler;

/**
 * ViewTest
 */
public class ViewTest {
    private ByteArrayOutputStream outContent;
    private View view;
    private Controller controller;
    private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
    private DiscountDBHandler discountDBHandler;
    private PrinterHandler printerHandler;
    
    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        accountingHandler = new AccountingHandler();
        inventoryHandler = new InventoryHandler();
        discountDBHandler = new DiscountDBHandler();
        printerHandler = new PrinterHandler();
        controller = new Controller(accountingHandler, inventoryHandler, discountDBHandler, printerHandler);
        view = new View(controller);
    }
    
        @AfterEach
    public void tearDown(){
    }

    @Test
    public void testFakeExecution() {
        controller.startSale();
        try {
            controller.scanItem(123, 1);
        } catch (Exception e) {
            fail("Scan item threw exception incorrectly.");
        }
        controller.endSale();

        
    }
    
    @Test
    public void testPresentItemInformation() {
        
    }
}