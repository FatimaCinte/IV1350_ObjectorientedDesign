package se.kth.iv1350.pos.model;

public class PriceDetails {
    private double grossPrice;
    private double netPrice;

    PriceDetails(double grossPrice, double netPrice) {
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public double getNetPrice() {
        return netPrice;
    }
}
