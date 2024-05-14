package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;
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
}