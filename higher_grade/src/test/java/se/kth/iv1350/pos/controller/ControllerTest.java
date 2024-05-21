package se.kth.iv1350.pos.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.AccountingHandler;
import se.kth.iv1350.pos.integration.BasketDTO;
import se.kth.iv1350.pos.integration.DiscountDBHandler;
import se.kth.iv1350.pos.integration.InventoryHandler;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.integration.PrinterHandler;
import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.integration.PriceDetails;

public class ControllerTest {
    private Controller controller;
    private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
    private DiscountDBHandler discountDBHandler;
    private PrinterHandler printerHandler;

    private ItemDTO oatmeal;

    @BeforeEach
    public void setUp() {
        accountingHandler = new AccountingHandler();
        inventoryHandler = new InventoryHandler();
        discountDBHandler = new DiscountDBHandler();
        printerHandler = new PrinterHandler();
        controller = new Controller(accountingHandler, inventoryHandler, discountDBHandler, printerHandler);
        controller.startSale();
        oatmeal = new ItemDTO(123, "BigWheel Oatmeal", 29.90, 6);
    }

    @AfterEach
    public void tearDown(){
        controller = null;
    }


    @Test
    public void testScanItem() throws ItemNotFoundException, OperationFailedException{
        BasketDTO basketDTO = controller.scanItem(123, 1);
        ArrayList<Item> itemsInBasket = basketDTO.getItemList();
        ItemDTO itemInBasket = itemsInBasket.get(0).getItemDTO();
        int quantityOfItemInBasket = itemsInBasket.get(0).getQuantity();
        assertEquals(oatmeal, itemInBasket, "Scanned item was not correct");
        assertEquals(1, quantityOfItemInBasket, "Quantity of scanned item was incorrect");
    }

    @Test 
    public void testPositivePayment() throws ItemNotFoundException, OperationFailedException{
        double paidAmount = 100;

        controller.scanItem(456, 1);
        double testChangeAmount = controller.presentPayment(paidAmount);

        double expectedChangeAmount = paidAmount - 14.90;
        assertEquals(expectedChangeAmount, testChangeAmount, "Change given from paymen was incorrect");
    }

    @Test
    public void testZeroPayment() throws Exception{
        double paidAmount = 0;

        controller.scanItem(456, 1);
        double testChangeAmount = controller.presentPayment(paidAmount);

        double expectedChangeAmount = paidAmount - 14.90;
        assertEquals(expectedChangeAmount, testChangeAmount, "Change given from paymen was incorrect");
        
    }
    
    @Test
    public void testEndSale() throws Exception{
        PriceDetails expectedResult = new PriceDetails(75.28301886792453, 79.8);

        controller.scanItem(123, 2);
        controller.scanItem(1, 1);
        PriceDetails result = controller.endSale();
        assertEquals(expectedResult, result, "PriceDetail was not correct");
    }

}
