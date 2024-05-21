package se.kth.iv1350.pos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseConnectionExceptionTest {
    public InventoryHandler inventory;

    @BeforeEach
    public void setUp(){
        inventory = new InventoryHandler();
    }

    @AfterEach
    public void tearDown(){
    }

    @Test
    public void databaseConnectionExceptionTest(){
        String expectedMessage = "Unable to connect to the database.";
        DatabaseConnectionException exception = assertThrows(DatabaseConnectionException.class, () -> inventory.getItemDTO(666));
        assertEquals(expectedMessage, exception.getMessage(), "The correct exception was not thrown");
    }


}
