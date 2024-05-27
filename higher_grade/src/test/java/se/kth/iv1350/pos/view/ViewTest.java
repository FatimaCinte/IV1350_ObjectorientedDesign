package se.kth.iv1350.pos.view;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
    
    private double paidAmount;
    private double runningNetPrice;
    private double changeAmount;
    private String name;
    private int itemID;
    private double itemNetPrice;
    private int vatRate;
    private double runningGrossPrice;
    private double vatPrice;

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
    public void testFakeBasicExecutionRun() {
        view.fakeBasicExecutionRun();

        itemID = 123;
        name = "BigWheel Oatmeal";
        itemNetPrice = 29.90;
        vatRate = 6;
        runningNetPrice = 29.90;
        runningGrossPrice = 29.90;
        vatPrice = 1.69;
        paidAmount = 100;
        runningNetPrice = 29.90;
        paidAmount = 100.00;

        String expItemID = String.format("Item ID:     %d\n" , itemID);
        String expItemName = String.format("Item name:     %s\n" , name);
        String expItemNetPrice = String.format("Item net price:     %5.2f%s\n" , itemNetPrice , " SEK");
        String expVATRate = String.format("VAT:     %d%%\n\n", vatRate);
        String expTotalRunningNetPrice = String.format("Total cost (incl. VAT):     %5.2f%s\n", runningNetPrice, " SEK");
        String expVatPrice = String.format("Total VAT:     %5.2f%s\n\n" , vatPrice , " SEK");
        String expRunningNetPrice = String.format("Total cost (incl. VAT):     %5.2f%s\n",runningNetPrice," SEK");
        String expPaidAmount = String.format("Customer pays:     %5.2f%s\n" , paidAmount , " SEK\n");

        String result = outContent.toString();
        assertTrue(result.contains(expItemID), "Wrong item ID printout.");
        assertTrue(result.contains(expRunningNetPrice), "Wrong running Net Price printout.");
        assertTrue(result.contains(expVatPrice), "Wrong VAT price printout.");
        assertTrue(result.contains(expTotalRunningNetPrice), "Wrong total running net price printout.");
        assertTrue(result.contains(expPaidAmount), "Wrong paid amount printout.");
        assertTrue(result.contains(expItemName), "Wrong item name printout.");
        assertTrue(result.contains(expItemNetPrice), "Wrong item net price printout.");
        assertTrue(result.contains(expVATRate), "Wrong VAT rate printout.");
    }
    
    @Test
    public void testPresentItemInformation() {
        
    }
}