package se.kth.iv1350.pos.integration;

import java.time.LocalDate;
import java.time.LocalTime;

public class SaleDTO {
    private final LocalDate date;
    private final LocalTime time;
    private final int grossPrice; 
    private final int netPrice;

    public SaleDTO(LocalDate date, LocalTime time, int grossPrice, int netPrice){
        this.date = date;
        this.time = time;
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
    }
}
