package se.kth.iv1350.pos.integration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import se.kth.iv1350.pos.model.Item;
import se.kth.iv1350.pos.model.Receipt;

public class PrinterHandler {
    private String name;
    private int quantity;
    private LocalDate date;
    private LocalTime time;
    private double itemNetPrice;
    private double totalNetPriceOfSameItem;

    private double vatPrice;
    private double totalNetPrice;
    private double totalGrossPrice;

    private double paidAmount;
    private double changeAmount;

    public PrinterHandler() {
        
    }

    public void printReceipt(Receipt receipt){
        System.out.println(createReceiptString(receipt));
    }

    private String createReceiptString(Receipt receipt){
        StringBuilder builder = new StringBuilder();
        appendLine(builder, "\n***********************Start receipt**********************\n");

        SaleDTO saleInformation = receipt.getSaleDTO();

        date = saleInformation.getDate();
        time = saleInformation.getTime();
        appendLine(builder, "Time of Sale: " + date + " " + time);
        endSection(builder);

        ArrayList<Item> itemList = saleInformation.getItemList();
        for (Item item : itemList) {
            ItemDTO itemDTO = item.getItemDTO();

            name = itemDTO.getItemName();
            quantity = item.getQuantity();
            itemNetPrice = itemDTO.getItemNetPrice();
            totalNetPriceOfSameItem = calculateTotalPriceOfSameItem(quantity, itemNetPrice);
            appendLine(builder, name + "               " + quantity + " x " + itemNetPrice + "      "+ totalNetPriceOfSameItem + " SEK");
        }

        endSection(builder);

        totalNetPrice = saleInformation.getNetPrice();
        appendLine(builder, String.format("Total:                                         %5.2f%s", totalNetPrice, " SEK" ));
        
        totalGrossPrice = saleInformation.getGrossPrice();
        vatPrice = calulateVatPrice(totalGrossPrice, totalNetPrice);
        appendLine(builder, String.format("VAT:                                             %5.2f%s", vatPrice," SEK"));
        endSection(builder);
        
        paidAmount = receipt.getPaidAmount();
        appendLine(builder, String.format("Cash:                                            %5.2f%s", paidAmount ," SEK"));
    
        changeAmount = receipt.getChangeAmount();
        appendLine(builder, String.format("Change:                                         %5.2f%s" , changeAmount ," SEK"));
        endSection(builder);
        appendLine(builder, "***********************End receipt**********************");

        return builder.toString();
    }

    private double calulateVatPrice(double grossPrice, double netPrice) {
        return netPrice - grossPrice;
    }

    private double calculateTotalPriceOfSameItem(int quantity, double netPrice) {
        return quantity*netPrice;
    }

    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }

    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }
}
