package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.BasketDTO;

public class View {
    private Controller controller;
    private String name;
    private int itemID;
    private double itemNetPrice;
    private int vatRate;
    private double runningNetPrice;
    private double runningGrossPrice;
    private double vatPrice;
    private double paidAmount;

    private BasketDTO currentBaket;

    public View(Controller controller){
        this.controller = controller;
    }

    public void fakeExecutionRun(){
        controller.startSale();

        currentBaket = controller.scanItem(123, 1);
        presentItemInformation(currentBaket);

        currentBaket = controller.scanItem(123, 1);
        presentItemInformation(currentBaket);

        currentBaket = controller.scanItem(456, 1);
        presentItemInformation(currentBaket);

        controller.endSale();
        System.out.println("End sale: ");
        System.out.println("Total cost (incl. VAT):     " + runningNetPrice + " SEK\n");

        paidAmount = 100;
        System.out.println("Customer pays:     " + paidAmount + " SEK\n");

        double changeAmount = controller.presentPayment(100);   
        System.out.println("Change to give the customer:    " + changeAmount);     
    }

    private void presentItemInformation(BasketDTO basketDTO) {
        itemID = basketDTO.getLatestItem().getItemDTO().getItemID();
        name = basketDTO.getLatestItem().getItemDTO().getItemName();
        itemNetPrice = basketDTO.getLatestItem().getItemDTO().getItemNetPrice();
        vatRate = basketDTO.getLatestItem().getItemDTO().getVatRate();
        runningNetPrice = basketDTO.getPriceDetails().getNetPrice();
        runningGrossPrice = basketDTO.getPriceDetails().getGrossPrice();
        vatPrice = runningNetPrice - runningGrossPrice;
        
        System.out.println("Latest scanned item: ");
        System.out.println("Item ID:    " + itemID );
        System.out.println("Item name:    " + name);
        System.out.println("Item net price:     " + itemNetPrice + " SEK");
        System.out.println("VAT:     " + vatRate + "%\n");
        
        System.out.println("Total cost (incl. VAT):     " + runningNetPrice + " SEK");
        System.out.println("Total VAT:     " + vatPrice + "+ SEK\n");
        
    }
}
