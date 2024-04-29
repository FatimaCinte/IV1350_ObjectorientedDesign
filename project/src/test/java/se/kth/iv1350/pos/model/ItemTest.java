package se.kth.iv1350.pos.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.ItemDTO;

public class ItemTest {
    private Item instanceToTest;
    private int itemID = 777;
    private String name = "Ravioli";
    private double itemNetPrice = 35.5;
    private int vatRate = 6;
    private int quantity = 1;

    @BeforeEach
    public void setUp(){
        ItemDTO itemDTO = new ItemDTO(itemID, name, itemNetPrice, vatRate);
        instanceToTest = new Item(itemDTO, quantity);
    }

    @AfterEach
    public void tearDown(){
        instanceToTest = null;
    }

    @Test
    public void testGetQuantity(){
        int expResult = quantity;
        int result = instanceToTest.getQuantity();
        assertEquals(result, expResult, "Wrong quantity returned");
    }

    @Test
    public void testQuantityIsNotEqualToNull(){
        assertFalse(quantity == 0, "Quantity was equal to 0");
    }
    
    @Test
    public void testUpdateQuantity(){
        int expResult = quantity + 1;
        instanceToTest.updateQuantity(quantity);
        int result = instanceToTest.getQuantity();
        assertEquals(result, expResult, "Wrong quantity returned");
    }

    @Test
    public void testUpdateQuantityWithNull(){
        int expResult = 1;
        quantity = 0;
        
        instanceToTest.updateQuantity(quantity);
        int result = instanceToTest.getQuantity();
        assertEquals(result, expResult, "Wrong quantity returned");
    }
}