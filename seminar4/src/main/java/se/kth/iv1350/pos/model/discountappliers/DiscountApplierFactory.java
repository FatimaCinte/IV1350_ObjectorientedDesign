package se.kth.iv1350.pos.model.discountappliers;

/**
 * DiscountApplierFactory
 */
public class DiscountApplierFactory {
    private static final DiscountApplierFactory DISCOUNT_APPLIER_FACTORY = new DiscountApplierFactory();

    private NetPriceDiscountApplier netPriceDiscountApplier = new NetPriceDiscountApplier();
    private CustomerIDDiscountApplier customerIDDiscountApplier = new CustomerIDDiscountApplier();
    private ItemListDiscountApplier itemListDiscountApplier = new ItemListDiscountApplier();
    private CompositeDiscountApplier compositeDiscountApplier = new CompositeDiscountApplier();

    public static DiscountApplierFactory getDiscountApplierFactory(){
        return DISCOUNT_APPLIER_FACTORY;
    }

    private DiscountApplierFactory() {    
        compositeDiscountApplier.addApplier(netPriceDiscountApplier);
        compositeDiscountApplier.addApplier(customerIDDiscountApplier);
        compositeDiscountApplier.addApplier(itemListDiscountApplier);
    }

    public DiscountApplier getDefaultDiscountApplier() {
        return compositeDiscountApplier;
    }
}