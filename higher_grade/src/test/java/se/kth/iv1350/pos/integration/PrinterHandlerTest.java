package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.model.Receipt;
import se.kth.iv1350.pos.model.SaleObserver;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;

/**
 * PrinterHandlerTest
 */
public class PrinterHandlerTest {
    private Controller controller;
    private ByteArrayOutputStream outContent;
    private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
    private DiscountDBHandler discountDBHandler;
    private PrinterHandler printerHandler;
    private PriceDetails priceDetails;
    private double changeAmount;
    private LocalDate date;
    
    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        accountingHandler = new AccountingHandler();
        inventoryHandler = new InventoryHandler();
        discountDBHandler = new DiscountDBHandler();
        printerHandler = new PrinterHandler();
        controller = new Controller(accountingHandler, inventoryHandler, discountDBHandler, printerHandler);
    }
    
    @AfterEach
    public void tearDown(){
    }

    @Test
    public void testCreateReceiptString(){
        controller.startSale();
        try {
            controller.scanItem(123, 1);
        } catch (Exception e) {
            fail("Scan item threw exception incorrectly.");
        }

        double paidAmount = 100;
        priceDetails = controller.endSale();
        changeAmount = controller.presentPayment(paidAmount);
        
        date = LocalDate.now();

        String expVatPrice = String.format("VAT:                                             %5.2f%s", priceDetails.getNetPrice() - priceDetails.getGrossPrice(), " SEK");
        String expTotalNetPrice = String.format("Total:                                         %5.2f%s", priceDetails.getNetPrice(), " SEK" );
        String expChangeAmount = String.format("Change:                                         %5.2f%s" , changeAmount ," SEK");
        String expPaidAmount = String.format("Cash:                                            %5.2f%s", paidAmount ," SEK");
        String expItem =  "BigWheel Oatmeal               1 x 29.9      29.9 SEK";
        String expTime = "Time of Sale: " + date;

        String result = outContent.toString();
        assertTrue(result.contains(expVatPrice), "Wrong VAT printout.");
        assertTrue(result.contains(expTotalNetPrice), "Wrong net price printout.");
        assertTrue(result.contains(expChangeAmount), "Wrong change amount printout.");
        assertTrue(result.contains(expPaidAmount), "Wrong paid amount printout.");
        assertTrue(result.contains(expItem), "Wrong item printout.");
        assertTrue(result.contains(expTime), "Wrong time printout.");
    }
}