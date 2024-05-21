package se.kth.iv1350.pos.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.pos.integration.SaleDTO;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.integration.BasketDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class SaleTest {
    private Sale instanceOfSale;
    private Basket instanceOfBasket;
    private BasketDTO basketDTO;
    private ItemDTO pasta;
    private ItemDTO bread;
    private List<SaleObserver> saleObservers;

    @BeforeEach
    public void setUp(){
        saleObservers = new ArrayList<>();
        instanceOfSale = new Sale(saleObservers);
        instanceOfBasket = new Basket();
        pasta = new ItemDTO(1, "Barilla Spaghetti", 20, 6);
        bread = new ItemDTO(2, "Varsågod", 13, 6);
    }

    @AfterEach
    public void tearDown(){
        instanceOfSale = null;
        instanceOfBasket = null;
    }

    @Test
    public void testSingleItemScan(){
        instanceOfBasket.updateBasket(pasta, 1);
        basketDTO = instanceOfBasket.getBasketDTO();
        ItemDTO itemDTOInBasket = basketDTO.getItemList().get(0).getItemDTO();
        assertEquals(pasta, itemDTOInBasket, "Item in instanceOfBasket was not correct");
        assertEquals(pasta, basketDTO.getLatestItem().getItemDTO(), "Latest item was not correct");
    }

    @Test
    public void testHigherItemQuantityScan() {
        instanceOfBasket.updateBasket(pasta, 5);
        basketDTO = instanceOfBasket.getBasketDTO();
        int quantityOfItem = basketDTO.getItemList().get(0).getQuantity();
        assertEquals(5, quantityOfItem, "Item quantity was incorrect");
    }

    @Test
    public void testDuplicateItemScan() {
        instanceOfBasket.updateBasket(pasta, 1);
        instanceOfBasket.updateBasket(pasta, 1);
        basketDTO = instanceOfBasket.getBasketDTO();
        int quantityOfItem = basketDTO.getItemList().get(0).getQuantity();
        assertEquals(2, quantityOfItem, "Item quantity was incorrect");
    }

    @Test
    public void testDifferentItemsScan() {
        instanceOfBasket.updateBasket(pasta, 1);
        instanceOfBasket.updateBasket(bread, 1);
        basketDTO = instanceOfBasket.getBasketDTO();
        ArrayList<Item> itemsInBasket = basketDTO.getItemList();
        ItemDTO firstItemInBasket = itemsInBasket.get(0).getItemDTO();
        ItemDTO secondItemInBasket = itemsInBasket.get(1).getItemDTO();
        assertEquals(pasta, firstItemInBasket, "First item in instanceOfBasket was incorrect");
        assertEquals(bread, secondItemInBasket, "Second item in instanceOfBasket was incorrect");
    }

    @Test
    public void testDuplicateItemOutOfOrderScan() {
        instanceOfBasket.updateBasket(pasta, 1);
        instanceOfBasket.updateBasket(bread, 1);
        instanceOfBasket.updateBasket(pasta, 1);
        basketDTO = instanceOfBasket.getBasketDTO();
        ItemDTO latestItemDTO = basketDTO.getLatestItem().getItemDTO();
        assertEquals(pasta, latestItemDTO, "Latest item was incorrect");
    }

    @Test
    public void testPositivePayment(){
        int paidAmount = 100;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        BasketDTO basketDTO = instanceOfBasket.getBasketDTO();
        PriceDetails priceDetails = basketDTO.getPriceDetails();
        double netPrice = priceDetails.getNetPrice();
        double chanageAmount = paidAmount - netPrice;
        SaleDTO saleInforamtion = new SaleDTO(date, time, basketDTO);
        Receipt expectedReceipt = new Receipt(saleInforamtion, paidAmount, chanageAmount);

        Receipt testReceipt = instanceOfSale.getReceipt(paidAmount);

        testReceipt.equals(expectedReceipt);
    }

    @Test
    public void testZeroPayment(){
        int paidAmount = 0;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        BasketDTO basketDTO = instanceOfBasket.getBasketDTO();
        PriceDetails priceDetails = basketDTO.getPriceDetails();
        double netPrice = priceDetails.getNetPrice();
        double chanageAmount = paidAmount - netPrice;
        SaleDTO saleInforamtion = new SaleDTO(date, time, basketDTO);
        Receipt expectedReceipt = new Receipt(saleInforamtion, paidAmount, chanageAmount);

        Receipt testReceipt = instanceOfSale.getReceipt(paidAmount);

        testReceipt.equals(expectedReceipt);
    }

}