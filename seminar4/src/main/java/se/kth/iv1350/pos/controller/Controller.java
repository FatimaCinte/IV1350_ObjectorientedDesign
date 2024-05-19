package se.kth.iv1350.pos.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.pos.integration.*;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.util.FileLogger;
import se.kth.iv1350.pos.model.SaleObserver;
import se.kth.iv1350.pos.model.Receipt;


/**
* This is the application’s only controller class. 
* All calls to the model pass through here.
*/
public class Controller {
    private FileLogger fileLogger;
    private List<SaleObserver> saleObservers = new ArrayList<>();

	private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
	private DiscountDBHandler discountDBHandler;
	private PrinterHandler printerHandler;

    private Sale sale;

    /**
     * Creates an instance of Controller
     * 
     * @param accountingHandler 
     * @param inventoryHandler
     * @param discountDBHandler 
     * @param printerHandler 
     */
    public Controller(AccountingHandler accountingHandler, InventoryHandler inventoryHandler, DiscountDBHandler discountDBHandler, PrinterHandler printerHandler) {
        this.accountingHandler = accountingHandler;
        this.inventoryHandler = inventoryHandler;
        this.discountDBHandler = discountDBHandler;
        this.printerHandler = printerHandler;
    }

    public void setFileLogger(FileLogger fileLogger){
        this.fileLogger = fileLogger;
    }

    public void addSaleObserver(SaleObserver obs){
        saleObservers.add(obs);
    }

    /**
     * Creates an instance of Sale. 
     */
    public void startSale() {
        sale = new Sale(saleObservers);
    }

    /**
     * Calls sale with the scanned item information, and the quantity of the scanned item. 
     * 
     * @param itemID The id of the scanned item.
     * @param quantity The quantity of the scanned item. 
     * @return The BasketDTO representing the current Basket. 
     * @throws ItemNotFoundException if the item ID was not found
     * @throws OperationFailedException if the scan failed for any other reason than a missing item ID
     */
    public BasketDTO scanItem(int itemID, int quantity) throws ItemNotFoundException, OperationFailedException{
        try {
            ItemDTO itemDTO = inventoryHandler.getItemDTO(itemID);
            BasketDTO basketDTO = sale.scanItem(itemDTO, quantity);
            return basketDTO;
        } catch (DatabaseConnectionException exc) {
            fileLogger.logException(exc);
            throw new OperationFailedException("Could not scan item.", exc);
        }
    }

    /**
     * Gets price details from sale. 
     * 
     * @return The price details at the end of the sale. 
     */
    public PriceDetails endSale() {
        PriceDetails priceDetails = sale.getPriceDetails();
        return priceDetails;
    }

    /**
     * Applies discount to sale. 
     * 
     * @param customerID The id of the customer asking for a discount.
     * @return The price details after discount. 
     */
    public PriceDetails requestDiscount(int customerID) {
        DiscountRequestDTO discountRequestDTO = sale.getDiscountRequestDTO(customerID);
        DiscountDTO discountDTO = discountDBHandler.getDiscount(discountRequestDTO);
        
        sale.applyDiscount(discountDTO);
        PriceDetails priceDetails = sale.getPriceDetails();
        
        return priceDetails;
    }

    /**
     * Updates external accounting, and external inventory.
     * Prints receipt.
     * 
     * @param paidAmount The amount the customer is paying for the sale.
     * @return The amount of change the customer should receive.
     */
    public double presentPayment(double paidAmount) {
        SaleDTO saleInformation = sale.getSaleDTO();
        accountingHandler.updateAccounting(saleInformation);
        inventoryHandler.updateInventory(saleInformation);
        
        Receipt receipt = sale.getReceipt(paidAmount);
        printerHandler.printReceipt(receipt);

        double changeAmount = receipt.getChangeAmount();
        return changeAmount;
    }
}
