package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.SaleObserver;

/**
 * TotalRevenueView
 */
class TotalRevenueView extends TotalRevenue {

    @Override    
    public void doShowTotalRevenue() {
        System.out.printf("Total revenue: %5.2f%s\n\n", totalRevenue, " SEK.");
    }
}