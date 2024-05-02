package se.kth.iv1350.pos.model;

import se.kth.iv1350.pos.integration.SaleDTO;

/**
 * Represents a receipt for a sale.
 */
public class Receipt {
    private SaleDTO saleInformation;
    private double paidAmount;
    private double changeAmount;

    Receipt(SaleDTO saleInformation, double paidAmount, double changeAmount){
        this.saleInformation = saleInformation;
        this.paidAmount = paidAmount;
        this.changeAmount = changeAmount;
    }

    /**
     * Get the value of saleDTO
     * 
     * @return the value of saleDTO
     */
    public SaleDTO getSaleDTO() {
        return saleInformation;
    }

    /**
     * Get the value of paidAmount
     * 
     * @return the value of paidAmount
     */
    public double getPaidAmount() {
        return paidAmount;
    }

    /**
     * Get the value of changeAmount
     * 
     * @return the value of changeAmount
     */
    public double getChangeAmount() {
        return changeAmount;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Receipt)){
            return false;
        }
        Receipt other = (Receipt) object;
        return this == other;
    }

}
