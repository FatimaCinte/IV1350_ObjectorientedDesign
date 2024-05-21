package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.SaleObserver;

/**
 * TotalRevenue
 */
public abstract class TotalRevenue implements SaleObserver {

    protected double totalRevenue = 0;

    // This is the method defined in the observer interface .
    public void saleEnd(PriceDetails priceDetails) {
        calculateTotalRevenue(priceDetails); // Calculate
        // the total amount paid since the program started.
        showTotalRevenue();
    }

    private void showTotalRevenue () {
        try {
            doShowTotalRevenue();
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    private void calculateTotalRevenue(PriceDetails priceDetails) {
        totalRevenue += priceDetails.getNetPrice();
    }

    protected abstract void doShowTotalRevenue() throws Exception ;

    //protected abstract void handleErrors(Exception e);
}