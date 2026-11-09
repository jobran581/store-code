package finalproject;

public interface Discountable {
    // Applies a discount percentage to reduce the price of a discountable item.
    void applyDiscount(double percent) throws StoreException;
}
