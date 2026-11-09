package finalproject;

import java.util.ArrayList;
import java.io.*;

public class Customer extends user implements Serializable {
    private boolean registered;
    private String username;
    private String encryptedPassword;
    private PaymentCard savedCard;
    private ArrayList<Order> purchaseHistory;

    // Constructor: Creates an unregistered guest customer with name and phone.
    public Customer(String name, String phone) throws StoreException {
        super(name, phone);
        this.registered = false;
        this.purchaseHistory = new ArrayList<Order>();
    }

    // Constructor: Creates a registered customer with credentials, validates
    // username and password.
    public Customer(String name, String phone, String username, String password) throws StoreException {
        super(name, phone);

        if (username == null || username.trim().isEmpty())
            throw new StoreException("username cannot be empty");

        if (password == null || password.trim().isEmpty())
            throw new StoreException("password cannot be empty");

        this.registered = true;
        this.username = username;
        this.encryptedPassword = CryptoUtil.encrypt(password);
        this.purchaseHistory = new ArrayList<Order>();
    }

    // Authenticates customer by checking username and decrypted password match.
    public boolean login(String username, String password) {
        return registered && this.username.equals(username) && CryptoUtil.matches(password, encryptedPassword);
    }

    // Returns whether this customer is registered.
    public boolean isRegistered() {
        return registered;
    }

    // Stores a payment card for future purchases.
    public void saveCard(PaymentCard card) {
        this.savedCard = card;
    }

    // Returns the saved payment card for this customer.
    public PaymentCard getSavedCard() {
        return savedCard;
    }

    // Adds an order to this customer's purchase history.
    public void addOrder(Order order) {
        purchaseHistory.add(order);
    }

    // Returns the list of all orders placed by this customer.
    public ArrayList<Order> getPurchaseHistory() {
        return purchaseHistory;
    }

    // Returns the customer's role (registered or guest).
    public String getRole() {
        if (registered)
            return "registered Customer";
        return "guest Customer";
    }
}
