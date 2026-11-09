package finalproject;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Product product;
    private int quantity;

    // Constructor: Creates a cart item with product and quantity, validates stock
    // availability.
    public CartItem(Product product, int quantity) throws StoreException {
        if (product == null)
            throw new StoreException("Product cannot be null");
        if (quantity <= 0)
            throw new StoreException("Quantity must be positive");
        if (product.getQuantity() < quantity)
            throw new StoreException("Not enough stock");

        this.product = product;
        this.quantity = quantity;
    }

    // Returns the product contained in this cart item.
    public Product getProduct() {
        return product;
    }

    // Returns the quantity of this product in the cart.
    public int getQuantity() {
        return quantity;
    }

    // Calculates and returns the total price for this item (price * quantity).
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    // Returns a formatted string showing product name, quantity, and total price.
    public String toString() {
        return product.getName() + " x" + quantity + " = " + getTotalPrice();
    }
}
