package finalproject;

public class PayPalPayment extends PaymentMethod {
    private String email;

    // Constructor: Initializes PayPal payment with email validation.
    public PayPalPayment(String email) throws StoreException {
        if (email == null || email.trim().isEmpty())
            throw new StoreException("PayPal email cannot be empty");

        this.email = email;
    }

    // Returns a confirmation message for PayPal payment with email.
    public String pay(double amount) {
        return "Paid " + amount + " using PayPal: " + email;
    }
}
