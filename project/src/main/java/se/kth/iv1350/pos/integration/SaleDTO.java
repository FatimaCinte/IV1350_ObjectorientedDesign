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

    /**
     * Get the value of date
     * 
     * @return the value of date
     */
    public LocalDate getDate(){
        return date;
    }

    /**
     * Get the value of time
     * 
     * @return the value of time
     */
    public LocalTime getTime(){
        return time;
    }

    /**
     * Get the value of grossPrice
     * 
     * @return the value of grossPrice
     */
    public double getGrossPrice(){
        return grossPrice;
    }

    /**
     * Get the value of netPrice
     * 
     * @return the value of netPrice
     */
    public double getNetPrice(){
        return netPrice;
    }

    /**
     * Get the value of itemList
     * 
     * @return the value of itemList
     */
    public ArrayList<Item> getItemList(){
        return itemList;
    }
}
