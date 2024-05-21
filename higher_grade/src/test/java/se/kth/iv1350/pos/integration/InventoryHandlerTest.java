package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class InventoryHandlerTest {
    private InventoryHandler instanceToTest;

    @BeforeEach
    public void setUp(){
        instanceToTest = new InventoryHandler();
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


    @Disabled
    @Test
    public void updateInventoryPrintoutTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        String expResult = "Told external inventory system to decrease inventory quantity of item 123 by 1";
        


        assertTrue(outContent.toString().contains(expResult));
    }
}