package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.util.FileLogger;

/**
 * TotalRevenueView
 */
class TotalRevenueView extends TotalRevenue {
    private FileLogger errorLogger;

    TotalRevenueView(FileLogger errorLogger) {
        this.errorLogger = errorLogger;
    }

    @Override
    public void doShowTotalRevenue() {
        System.out.printf("Total revenue: %5.2f%s\n\n", totalRevenue, " SEK.");
    }

    @Override
    public void handleErrors(Exception exc) {
        System.out.println("Something went wrong when trying to print the revenue.");
        errorLogger.logException(exc);
    }
}