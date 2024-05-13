package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.BasketObserver;

/**
 * TotalRevenueView
 */
class TotalRevenueView implements BasketObserver {

    @Override    
    public void newScan(PriceDetails priceDetails) {
        double netPrice = priceDetails.getNetPrice();
        System.out.printf("Total revenue: %5.2f%s\n", netPrice, " SEK.");
    }
}