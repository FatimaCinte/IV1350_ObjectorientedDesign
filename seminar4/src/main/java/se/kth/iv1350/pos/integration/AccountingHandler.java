package se.kth.iv1350.pos.integration;

public class AccountingHandler {

    /**
     * Creates an instance of AccountingHandler
     */
    public AccountingHandler() {

    }

    /**
     * Accepts sale information to send to the accounting system
     * @param saleInforamtion the information to send to the accounting system
     */
    public void updateAccounting(SaleDTO saleInforamtion){
        System.out.println("Sent sale info to external accounting system.");
    }
}
