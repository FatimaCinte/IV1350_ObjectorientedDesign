package se.kth.iv1350.pos.view;

<<<<<<< Updated upstream
=======
import se.kth.iv1350.pos.util.FileLogger;

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public void handleErrors(Exception e) {
        System.err.println("Something went wrong when trying to write the revenue to System.out: " + e.getMessage());
        e.printStackTrace(System.err);
=======
    public void handleErrors(Exception exc) {
        System.out.println("Something went wrong when trying to print the revenue.");
        errorLogger.logException(exc);
>>>>>>> Stashed changes
    }
}