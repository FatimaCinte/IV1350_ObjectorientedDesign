package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryHandlerTest {
    private InventoryHandler instanceToTest;
    private int itemID = 777;
    private String name = "Ravioli";
    private double itemNetPrice = 35.5;
    private int vatRate = 6;
    
    private final ItemDTO oatmeal = new ItemDTO(123, "BigWheel Oatmeal", 29.90, 6);
    private final ItemDTO yoghurt = new ItemDTO(456, "YouGoGo Blueberry", 14.90, 6);
    private ArrayList<ItemDTO> inventory;

    @BeforeEach
    public void setUp(){
        ItemDTO itemDTO = new ItemDTO(itemID, name, itemNetPrice, vatRate);
        inventory = new ArrayList<ItemDTO>();
        inventory.add(oatmeal);
        inventory.add(yoghurt);

        instanceToTest = new InventoryHandler();

    }

    @AfterEach
    public void tearDown(){
        instanceToTest = null;
    }

    @Test
    public void testGetItemDTO(){
        ItemDTO expResult = new ItemDTO(123, "BigWheel Oatmeal", 29.90, 6);

        ItemDTO result = instanceToTest.getItemDTO(123);
        assertEquals(result, expResult, "Wrong ItemDTO returned");

    }

}