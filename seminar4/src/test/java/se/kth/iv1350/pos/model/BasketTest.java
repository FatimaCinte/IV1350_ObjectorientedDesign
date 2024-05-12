package se.kth.iv1350.pos.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import se.kth.iv1350.pos.integration.BasketDTO;
import se.kth.iv1350.pos.integration.ItemDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BasketTest {
    private Basket basket;
    private ItemDTO pastaDTO;
    private ItemDTO breadDTO;
    private BasketDTO basketDTO;

    @BeforeEach
    public void setUp() {
        pastaDTO = new ItemDTO(1, "Barilla Spaghetti", 20, 6);
        breadDTO = new ItemDTO(2, "Varsågod", 13, 6);
        basket = new Basket();
    }

    @AfterEach
    public void tearDown() {
        basket = null;
    }

    @Test
    public void testAddSingleItem() {
        basket.updateBasket(pastaDTO, 1);
        basketDTO = basket.getBasketDTO();
        ItemDTO itemDTOInBasket = basketDTO.getItemList().get(0).getItemDTO();
        assertEquals(pastaDTO, itemDTOInBasket, "Item in basket was not correct");
        assertEquals(pastaDTO, basketDTO.getLatestItem().getItemDTO(), "Latest item was not correct");
    }

    @Test
    public void testAddHigherItemQuantity() {
        basket.updateBasket(pastaDTO, 5);
        basketDTO = basket.getBasketDTO();
        int quantityOfItem = basketDTO.getItemList().get(0).getQuantity();
        assertEquals(5, quantityOfItem, "Item quantity was incorrect");
    }

    @Test
    public void testAddDuplicateItem() {
        basket.updateBasket(pastaDTO, 1);
        basket.updateBasket(pastaDTO, 1);
        basketDTO = basket.getBasketDTO();
        int quantityOfItem = basketDTO.getItemList().get(0).getQuantity();
        assertEquals(2, quantityOfItem, "Item quantity was incorrect");
    }

    @Test
    public void testAddDifferentItems() {
        basket.updateBasket(pastaDTO, 1);
        basket.updateBasket(breadDTO, 1);
        basketDTO = basket.getBasketDTO();
        ArrayList<Item> itemsInBasket = basketDTO.getItemList();
        ItemDTO firstItemInBasket = itemsInBasket.get(0).getItemDTO();
        ItemDTO secondItemInBasket = itemsInBasket.get(1).getItemDTO();
        assertEquals(pastaDTO, firstItemInBasket, "First item in basket was incorrect");
        assertEquals(breadDTO, secondItemInBasket, "Second item in basket was incorrect");
    }

    @Test
    public void testAddDuplicateItemOutOfOrder() {
        basket.updateBasket(pastaDTO, 1);
        basket.updateBasket(breadDTO, 1);
        basket.updateBasket(pastaDTO, 1);
        basketDTO = basket.getBasketDTO();
        ItemDTO latestItemDTO = basketDTO.getLatestItem().getItemDTO();
        assertEquals(pastaDTO, latestItemDTO, "Latest item was incorrect");
    }
}
