package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.integration.PriceDetails;
import se.kth.iv1350.pos.model.SaleObserver;
import se.kth.iv1350.pos.util.FileLogger;

/**
 * TotalRevenueFileOutput
 */
class TotalRevenueFileOutput extends TotalRevenue {
    private FileLogger fileLogger;

    TotalRevenueFileOutput(FileLogger fileLogger){
        this.fileLogger = fileLogger;
    }

    //String.format("Total revenue %.5.2f%s", netPrice, " SEK")
    @Override
    public void doShowTotalRevenue() {
        fileLogger.logMessage(String.format("Total revenue %5.2f%s", totalRevenue, " SEK"));
    }
}