package finalproject;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable {
    private static int counter = 1;

    private int receiptNumber;
    private Order order;
    private String paymentInfo;
    private Date date;

    // Constructor: Creates a receipt for an order with payment information and
    // generates unique number.
    public Receipt(Order order, String paymentInfo) throws StoreException {
        if (order == null)
            throw new StoreException("Order cannot be null");

        this.receiptNumber = counter++;
        this.order = order;
        this.paymentInfo = paymentInfo;
        this.date = new Date();
    }

    // Returns a formatted receipt with number, date, order details, and payment
    // info.
    public String toString() {
        return "===== RECEIPT #" + receiptNumber + " =====\n"
                + "Date: " + date + "\n"
                + order + "\n"
                + paymentInfo;
    }
}
