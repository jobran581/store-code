package finalproject;

import java.io.Serializable;

public abstract class PaymentMethod implements Serializable {
    // Abstract method: Processes payment and returns confirmation message.
    public abstract String pay(double amount) throws StoreException;
}
