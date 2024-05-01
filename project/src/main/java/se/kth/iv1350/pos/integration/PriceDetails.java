package se.kth.iv1350.pos.integration;

public class PriceDetails {
    private double grossPrice;
    private double netPrice;

    public PriceDetails(double grossPrice, double netPrice) {
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
    }

    /**
     * Get the value of grossPrice
     * 
     * @return the value of grossPrice
     */
    public double getGrossPrice() {
        return grossPrice;
    }

    /**
     * Get the value of netPrice
     * 
     * @return the value of netPrice
     */
    public double getNetPrice() {
        return netPrice;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof PriceDetails)){
            return false;
        }
        PriceDetails other = (PriceDetails) object;
        return this.grossPrice == other.grossPrice && this.netPrice == other.netPrice;
    }
}
