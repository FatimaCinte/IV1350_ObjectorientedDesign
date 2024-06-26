package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.SaleObserver;
import se.kth.iv1350.pos.util.FileLogger;

/**
 * TotalRevenueFileOutput
 */
class TotalRevenueFileOutput implements SaleObserver {
    private FileLogger fileLogger;
    private double totalRevenue = 0;

    TotalRevenueFileOutput(FileLogger fileLogger){
        this.fileLogger = fileLogger;
    }

    //String.format("Total revenue %.5.2f%s", netPrice, " SEK")
    @Override
    public void saleEnd(PriceDetails priceDetails) {
        totalRevenue += priceDetails.getNetPrice();
        fileLogger.logMessage(String.format("Total revenue %5.2f%s", totalRevenue, " SEK"));
    }
}