package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;
import se.kth.iv1350.pos.integration.BasketDTO;
import se.kth.iv1350.pos.integration.ItemNotFoundException;
import se.kth.iv1350.pos.util.FileLogger;

public class View {
    private FileLogger fileLogger = new FileLogger();

    private Controller controller;
    private String name;
    private int itemID;
    private double itemNetPrice;
    private int vatRate;
    private double runningNetPrice;
    private double runningGrossPrice;
    private double vatPrice;
    private double paidAmount;

    private BasketDTO currentBasket;

    /**
     * Creates an instance of View.
     * @param controller a reference to the controller as an interface for the sale process.
     */
    public View(Controller controller){
        this.controller = controller;
        controller.setFileLogger(fileLogger);
        controller.addBasketObserver(new TotalRevenueView());
        controller.addBasketObserver(new TotalRevenueFileOutput(fileLogger));
    }

    /** 
    * A fake run of the sale process.   
    */
    public void fakeExecutionRun(){
        controller.startSale();

        scanItem(123, 1); 

        scanItem(123, 1);
        //scanItem(456, 1);

        scanItem(99999, 1);
        scanItem(666, 1);

        controller.endSale();
        System.out.printf("End sale: \n");
        System.out.printf("Total cost (incl. VAT):     %5.2f%s\n", runningNetPrice, " SEK\n");

        controller.requestDiscount(123);

        paidAmount = 100;
        System.out.printf("Customer pays:     %5.2f%s\n", paidAmount ," SEK\n");

        double changeAmount = controller.presentPayment(100);   
        System.out.printf("Change to give the customer:    %5.2f\n", changeAmount);     
    }

    private void scanItem(int itemID, int quantity) {
        try {
            currentBasket = controller.scanItem(itemID, quantity);
            presentItemInformation(currentBasket);
        } catch (ItemNotFoundException exc) {
            System.out.println(exc.getMessage() + "\n");
        } catch (OperationFailedException exc){
            System.out.println(exc.getMessage() + "\n");
        }   
    }
    
    private void presentItemInformation(BasketDTO basketDTO) {
        itemID = basketDTO.getLatestItem().getItemDTO().getItemID();
        name = basketDTO.getLatestItem().getItemDTO().getItemName();
        itemNetPrice = basketDTO.getLatestItem().getItemDTO().getItemNetPrice();
        vatRate = basketDTO.getLatestItem().getItemDTO().getVatRate();
        runningNetPrice = basketDTO.getPriceDetails().getNetPrice();
        runningGrossPrice = basketDTO.getPriceDetails().getGrossPrice();
        vatPrice = runningNetPrice - runningGrossPrice;
        
        System.out.printf("Latest scanned item: \n");
        System.out.printf("Item ID:     %d\n", itemID );
        System.out.printf("Item name:     %s\n", name);
        System.out.printf("Item net price:     %5.2f%s\n", itemNetPrice, " SEK");
        System.out.printf("VAT:     %d%%\n\n", vatRate);
        
        System.out.printf("Total cost (incl. VAT):     %5.2f%s\n",runningNetPrice," SEK");
        System.out.printf("Total VAT:     %5.2f%s\n\n",vatPrice, " SEK");
        
    }
}
