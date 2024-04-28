package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.BasketDTO;
import se.kth.iv1350.pos.model.PriceDetails;

public class View {
    private Controller controller;

    public View(Controller controller){
        this.controller = controller;
    }

    public void fakeExecutionRun(){
        controller.startSale();
        controller.scanItem(123, 1);
        controller.scanItem(123, 1);
        controller.scanItem(456, 1);
        PriceDetails priceDetails = controller.endSale();
        double grossPrice = priceDetails.getGrossPrice();
        System.out.println(grossPrice);

        double paidAmount = 100;
        double changeAmount = controller.presentPayment(paidAmount);
        System.out.println(changeAmount);
        
    }
}
