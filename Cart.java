package finalproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<CartItem> items;

    // Constructor: Initializes an empty cart.
    public Cart() {
        items = new ArrayList<CartItem>();
    }

    // Adds a product with specified quantity to the cart.
    public void addItem(Product product, int quantity) throws StoreException {
        items.add(new CartItem(product, quantity));
    }

    // Returns the list of all items in the cart.
    public ArrayList<CartItem> getItems() {
        return items;
    }

    // Checks if the cart is empty.
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Calculates and returns the total price of all items in the cart.
    public double getTotalPrice() {
        double total = 0;

        for (CartItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    // Returns a formatted string representation of the cart with all items and
    // total price.
    public String toString() {
        if (items.isEmpty())
            return "Cart is empty";

        String result = "===== CART =====\n";

        for (CartItem item : items) {
            result += item + "\n";
        }

        result += "Total: " + getTotalPrice();
        return result;
    }
}
