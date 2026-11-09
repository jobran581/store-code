package finalproject;

import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private Customer customer;
    private ArrayList<CartItem> items;
    private double totalPrice;
    private OrderStatus status;

    // Constructor: Initializes order with customer and cart items, validates inputs
    // and calculates total price.
    public Order(Customer customer, Cart cart) throws StoreException {
        if (customer == null)
            throw new StoreException("Customer cannot be null");

        if (cart == null || cart.isEmpty())
            throw new StoreException("Cart cannot be empty");

        this.customer = customer;
        this.items = new ArrayList<CartItem>();
        this.status = OrderStatus.PENDING;

        for (CartItem item : cart.getItems()) {
            items.add(item);
            totalPrice += item.getTotalPrice();
        }
    }

    // Returns the total price of the order.
    public double getTotalPrice() {
        return totalPrice;
    }

    // Updates the order status (PENDING, PAID, SHIPPED, DELIVERED, REFUNDED).
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    // Returns a formatted string representation of the order with all items and
    // total price.
    public String toString() {
        String result = "Order for " + customer.getName() + "\n";
        result += "Status: " + status + "\n";

        for (CartItem item : items) {
            result += item + "\n";
        }

        result += "Total: " + totalPrice;
        return result;
    }
}
