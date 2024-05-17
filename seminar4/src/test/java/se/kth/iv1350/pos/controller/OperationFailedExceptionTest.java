package se.kth.iv1350.pos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.AccountingHandler;
import se.kth.iv1350.pos.integration.DiscountDBHandler;
import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.PrinterHandler;
import se.kth.iv1350.pos.util.FileLogger;

public class OperationFailedExceptionTest {
    private Controller controller;
    private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
    private DiscountDBHandler discountDBHandler;
    private PrinterHandler printerHandler;
    private FileLogger fileLogger;

    @BeforeEach
    public void setUp() {
        accountingHandler = new AccountingHandler();
        inventoryHandler = new InventoryHandler();
        discountDBHandler = new DiscountDBHandler();
        printerHandler = new PrinterHandler();
        fileLogger = new FileLogger();
        controller = new Controller(accountingHandler, inventoryHandler, discountDBHandler, printerHandler);
        controller.startSale();
        controller.setFileLogger(fileLogger);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void operationFailedExceptionTest() {        
        String expectedMessage = "Could not scan item.";
        OperationFailedException exception = assertThrows(OperationFailedException.class, () -> controller.scanItem(666, 1));
        assertEquals(expectedMessage, exception.getMessage(), "The correct exception was not thrown");
    }
}

