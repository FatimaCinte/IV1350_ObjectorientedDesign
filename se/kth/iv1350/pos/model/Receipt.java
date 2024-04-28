package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.SaleDTO;

public class Receipt {
    private final double paidAmount;
    private final double changeAmount;

    public Receipt(SaleDTO saleInformation, double paidAmount, double changeAmount){
        this.paidAmount = paidAmount;
        this.changeAmount = changeAmount;
    }

    public double getPaidAmount(){
        return paidAmount;
    }

    public double getChangeAmount(){
        return changeAmount;
    }
}
