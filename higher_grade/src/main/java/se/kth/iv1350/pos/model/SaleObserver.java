package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.PriceDetails;

/**
 * BasketObserver
 */
public interface SaleObserver {

    void saleEnd(PriceDetails priceDetails);
}