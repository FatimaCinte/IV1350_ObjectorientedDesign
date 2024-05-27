package se.kth.iv1350.pos.view;

/**
 * TotalRevenueView
 */
class TotalRevenueView extends TotalRevenue {

    @Override    
    public void doShowTotalRevenue() {
        System.out.printf("Total revenue: %5.2f%s\n\n", totalRevenue, " SEK.");
    }

    @Override
    public void handleErrors(Exception e) {
        System.err.println("Something went wrong when trying to write the revenue to System.out: " + e.getMessage());
        e.printStackTrace(System.err);
    }
}