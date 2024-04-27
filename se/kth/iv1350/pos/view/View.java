package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.BasketDTO;

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
        double grossPrice = controller.endSale();

        int paidAmount = 100;
        int changeAmount = controller.presentPayment(paidAmount);
        System.out.println(changeAmount);


        
    }
}
