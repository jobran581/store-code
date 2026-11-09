package finalproject;

public class CreditCardPayment extends PaymentMethod {
    private PaymentCard card;

    // Constructor: Initializes credit card payment with card validation.
    public CreditCardPayment(PaymentCard card) throws StoreException {
        if (card == null)
            throw new StoreException("Card cannot be null");

        this.card = card;
    }

    // Returns a confirmation message for credit card payment with card details.
    public String pay(double amount) {
        return "Paid " + amount + " using credit card: " + card;
    }
}
