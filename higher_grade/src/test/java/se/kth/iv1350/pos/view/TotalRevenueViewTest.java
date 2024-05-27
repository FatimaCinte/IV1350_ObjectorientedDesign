package se.kth.iv1350.pos.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.AccountingHandler;
import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.DiscountDBHandler;
import se.kth.iv1350.pos.integration.PrinterHandler;
import se.kth.iv1350.pos.controller.Controller;

/**
 * TotalRevenueViewTest
 */
public class TotalRevenueViewTest {
    private static PrintStream systemOut;
    private ByteArrayOutputStream outContent;
    
    private View view;
    private Controller controller;
    private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
    private DiscountDBHandler discountDBHandler;
    private PrinterHandler printerHandler;

    @BeforeAll
    public static void beforeAll() {
        systemOut = System.out;
    }

    @AfterAll
    public static void cleanUp() {
        System.setOut(systemOut);
    }
    
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
    public void tearDown() {
    }

    @Test
    public void testDoShowTotalRevenue() {
        view.fakeBasicExecutionRun();

        String expResult = String.format("Total revenue: %5.2f%s\n\n", 29.90, " SEK.");
        String result = outContent.toString();
        
        assertTrue(result.contains(expResult), "Wrong revenue view printout.");
    }
}