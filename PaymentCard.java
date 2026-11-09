package finalproject;

import java.io.*;

public class PaymentCard implements Serializable {

    private String encryptedCardHolder;
    private String encryptedLastFourDigits;

    // Constructor: Creates a payment card with encrypted holder name and last four
    // digits.
    public PaymentCard(String cardHolder, String cardNumber) throws StoreException {
        if (cardHolder == null || cardHolder.trim().isEmpty())
            throw new StoreException("Card holder cannot be empty");

        if (cardNumber == null || cardNumber.length() < 4)
            throw new StoreException("Card number must have at least 4 digits");

        String lastFour = cardNumber.substring(cardNumber.length() - 4);

        encryptedCardHolder = CryptoUtil.encrypt(cardHolder);
        encryptedLastFourDigits = CryptoUtil.encrypt(lastFour);
    }

    // Returns a formatted card display with decrypted holder name and masked card
    // number.
    public String toString() {
        return "Card holder: " + CryptoUtil.decrypt(encryptedCardHolder)
                + ", card: **** **** **** "
                + CryptoUtil.decrypt(encryptedLastFourDigits);
    }
}
