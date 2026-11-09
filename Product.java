package finalproject;

import java.io.*;
import java.io.Serializable;

public class Product implements Discountable, Cloneable, Serializable {

    private String name;
    private double price;
    private double cost;
    private int quantity;
    private int soldQuantity;
    private Category category;

    // Constructor: Initializes a product with validation for price, cost, quantity,
    // and category.
    public Product(String name, double price, double cost, int quantity, Category category)
            throws StoreException {

        if (name == null || name.trim().isEmpty())
            throw new StoreException("Product name cannot be empty");

        if (price <= 0)
            throw new StoreException("Price must be positive");

        if (cost < 0)
            throw new StoreException("Cost cannot be negative");

        if (cost > price)
            throw new StoreException("Cost cannot be bigger than price");

        if (quantity < 0)
            throw new StoreException("Quantity cannot be negative");

        if (category == null)
            throw new StoreException("Category cannot be null");

        this.name = name;
        this.price = price;
        this.cost = cost;
        this.quantity = quantity;
        this.soldQuantity = 0;
        this.category = category;
    }

    // Returns the product name.
    public String getName() {
        return name;
    }

    // Returns the selling price of the product.
    public double getPrice() {
        return price;
    }

    // Returns the cost of the product.
    public double getCost() {
        return cost;
    }

    // Returns the current quantity of product in stock.
    public int getQuantity() {
        return quantity;
    }

    // Returns the total quantity of this product that has been sold.
    public int getSoldQuantity() {
        return soldQuantity;
    }

    // Returns the category of this product.
    public Category getCategory() {
        return category;
    }

    // Calculates and returns the total revenue from sold units (soldQuantity *
    // price).
    public double getRevenue() {
        return soldQuantity * price;
    }

    // Calculates and returns the total cost of sold units (soldQuantity * cost).
    public double getTotalCost() {
        return soldQuantity * cost;
    }

    // Calculates and returns the profit (revenue - total cost).
    public double getProfit() {
        return getRevenue() - getTotalCost();
    }

    // Reduces stock by amount and increments sold quantity, validates sufficient
    // stock.
    public void reduceQuantity(int amount) throws StoreException {
        if (amount <= 0)
            throw new StoreException("Amount must be positive");

        if (amount > quantity)
            throw new StoreException("Not enough stock");

        quantity -= amount;
        soldQuantity += amount;
    }

    // Increases stock quantity by the specified amount.
    public void addQuantity(int amount) throws StoreException {
        if (amount <= 0)
            throw new StoreException("Amount must be positive");

        quantity += amount;
    }

    // Applies a discount percentage to reduce the product price.
    public void applyDiscount(double percent) throws StoreException {
        if (percent <= 0 || percent >= 100)
            throw new StoreException("Discount must be between 0 and 100");

        price = price - price * percent / 100;
    }

    // Creates and returns a copy of this product with 'COPY' appended to the name.
    public Product clone() {
        try {
            return new Product(name + " COPY", price, cost, quantity, category);
        } catch (StoreException e) {
            return null;
        }
    }

    // Returns a formatted string with product details (name, price, quantity,
    // category).
    public String toString() {
        return "Product: " + name +
                ", price: " + price +
                ", quantity: " + quantity +
                ", category: " + category;
    }

    // Returns detailed financial information including revenue, cost, and profit.
    public String financialInfo() {
        return "Product: " + name +
                "\nSelling price: " + price +
                "\nCost: " + cost +
                "\nSold quantity: " + soldQuantity +
                "\nRevenue: " + getRevenue() +
                "\nTotal cost: " + getTotalCost() +
                "\nProfit: " + getProfit();
    }
}
