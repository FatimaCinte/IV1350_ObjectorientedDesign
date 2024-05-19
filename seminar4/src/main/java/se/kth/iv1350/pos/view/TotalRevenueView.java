package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.SaleObserver;

/**
 * TotalRevenueView
 */
class TotalRevenueView implements SaleObserver {

    private double totalRevenue = 0;

    @Override    
    public void saleEnd(PriceDetails priceDetails) {
        totalRevenue += priceDetails.getNetPrice();
        System.out.printf("Total revenue: %5.2f%s\n\n", totalRevenue, " SEK.");
    }
}