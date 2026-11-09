package finalproject;

import java.io.Serializable;

public class Review implements Serializable {
    private Customer customer;
    private Product product;
    private int rating;
    private String comment;

    // Constructor: Creates a product review with customer, product, rating (1-5),
    // and comment.
    public Review(Customer customer, Product product, int rating, String comment)
            throws StoreException {

        if (customer == null)
            throw new StoreException("Customer cannot be null");

        if (product == null)
            throw new StoreException("Product cannot be null");

        if (rating < 1 || rating > 5)
            throw new StoreException("Rating must be between 1 and 5");

        if (comment == null || comment.trim().isEmpty())
            throw new StoreException("Comment cannot be empty");

        this.customer = customer;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
    }

    // Returns a formatted review with product name, customer, rating, and comment.
    public String toString() {
        return product.getName()
                + " review by " + customer.getName()
                + "\nRating: " + rating + "/5"
                + "\nComment: " + comment;
    }
}
