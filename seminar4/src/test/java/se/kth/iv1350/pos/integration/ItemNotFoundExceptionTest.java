package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Executable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemNotFoundExceptionTest {
    public ItemDTO itemDTOTest;
    public InventoryHandler inventory;

    @BeforeEach
    public void setUp(){
        itemDTOTest = new ItemDTO(666, "invalid item", 999, 1000);
        inventory = new InventoryHandler();
    }

    @AfterEach
    public void tearDown(){
    }

    @Test
    public void getItemDTOTest(){
        

        Executable expectedResults = () -> inventory.getItemID(666);

        assertThrows(ItemNotFoundException.class, expectedResults, "Wrong exception thrown.");
        
    }

}
