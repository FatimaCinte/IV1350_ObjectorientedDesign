package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.SaleObserver;

/**
 * TotalRevenueView
 */
class TotalRevenueView implements SaleObserver {

    @Override    
    public void saleEnd(PriceDetails priceDetails) {
        double netPrice = priceDetails.getNetPrice();
        System.out.printf("Total revenue: %5.2f%s\n\n", netPrice, " SEK.");
    }
}