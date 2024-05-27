package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.util.FileLogger;

/**
 * TotalRevenueFileOutput
 */
class TotalRevenueFileOutput extends TotalRevenue {
    private FileLogger revenueLogger;
    private FileLogger errorLogger;

    TotalRevenueFileOutput(FileLogger revenueLogger, FileLogger errorLogger){
        this.revenueLogger = revenueLogger;
        this.errorLogger = errorLogger;
    }

    @Override
    public void doShowTotalRevenue() {
        revenueLogger.logMessage(String.format("Total revenue %5.2f%s", totalRevenue, " SEK"));
    }

    @Override
    public void handleErrors(Exception exc) {
        System.out.println("Something went wrong when trying to write the revenue to file.");
        errorLogger.logException(exc);
    }
}