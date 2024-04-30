package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

import se.kth.iv1350.pos.model.Item;

public class SaleDTO {
    private final LocalDate date;
    private final LocalTime time;
    private final double grossPrice;
    private final double netPrice;
    private final ArrayList<Item> itemList;

    public SaleDTO(LocalDate date, LocalTime time, BasketDTO basketDTO){
        this.date = date;
        this.time = time;
        this.grossPrice = basketDTO.getPriceDetails().getGrossPrice();
        this.netPrice = basketDTO.getPriceDetails().getNetPrice();
        this.itemList = basketDTO.getItemList();
    }    

    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public double getGrossPrice(){
        return grossPrice;
    }

    public double getNetPrice(){
        return netPrice;
    }

    public ArrayList<Item> getItemList(){
        return itemList;
    }
}
