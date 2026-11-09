package finalproject;

public class CashPayment extends PaymentMethod {

    // Returns a confirmation message for cash payment.
    public String pay(double amount) {
        return "Paid " + amount + " using cash";
    }
}
