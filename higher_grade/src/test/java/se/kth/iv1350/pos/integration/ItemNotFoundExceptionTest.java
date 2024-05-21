package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemNotFoundExceptionTest {
    public InventoryHandler inventory;

    @BeforeEach
    public void setUp(){
        inventory = new InventoryHandler();
    }

    @AfterEach
    public void tearDown(){
    }

    @Test
    public void itemNotFoundExceptionTest(){
        String expectedMessage = "Unable to find item with item ID 77799.";
        ItemNotFoundException  exception = assertThrows(ItemNotFoundException.class, () -> inventory.getItemDTO(77799));
        assertEquals(expectedMessage, exception.getMessage(), "The correct exception was not thrown");
    }
}