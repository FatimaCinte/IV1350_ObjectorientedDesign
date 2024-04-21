package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;

public class View {
    private Controller controller;

    public View(Controller contr){
        controller = contr;
    }

    public void fakeExecutionRun(){
        controller.startSale();
        int grossPrice = controller.endSale();

        int paidAmount = 0;
        int changeAmount = controller.presentPayment(paidAmount);
    }
}
