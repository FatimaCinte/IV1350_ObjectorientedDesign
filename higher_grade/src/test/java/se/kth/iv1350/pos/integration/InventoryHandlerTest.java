package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.view.View;

public class InventoryHandlerTest {
    private static PrintStream systemOut;
    private ByteArrayOutputStream outContent;
    private InventoryHandler instanceToTest;
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
    public void setUp(){
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        instanceToTest = new InventoryHandler();
        accountingHandler = new AccountingHandler();
        inventoryHandler = new InventoryHandler();
        discountDBHandler = new DiscountDBHandler();
        printerHandler = new PrinterHandler();
        controller = new Controller(accountingHandler, inventoryHandler, discountDBHandler, printerHandler);
        view = new View(controller);
    }

    @AfterEach
    public void tearDown(){
        instanceToTest = null;
    }

    @Test
    public void testGetItemDTO() throws ItemNotFoundException{
        int itemID = 123;
        String name = "BigWheel Oatmeal";
        double itemNetPrice = 29.90;
        int vatRate = 6;
        ItemDTO expResult = new ItemDTO(itemID, name, itemNetPrice, vatRate);

        ItemDTO result = instanceToTest.getItemDTO(123);
        assertEquals(expResult, result, "Wrong ItemDTO returned");
    }

    @Test
    public void updateInventoryPrintoutTest(){
        view.fakeBasicExecutionRun();
        
        String expResult ="Told external inventory system to decrease inventory quantity of item " + 123 + " by " + 1;
        String result = outContent.toString();

        assertTrue(result.toString().contains(expResult));
    }
}