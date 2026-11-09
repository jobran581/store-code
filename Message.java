package finalproject;

import java.io.*;

public class Message implements Serializable {

    private String sender;
    private String encryptedText;

    // Constructor: Creates a message from sender with encrypted text content.
    public Message(String sender, String text) throws StoreException {
        if (sender == null || sender.trim().isEmpty())
            throw new StoreException("Sender empty");

        if (text == null || text.trim().isEmpty())
            throw new StoreException("Message empty");

        this.sender = sender;
        this.encryptedText = CryptoUtil.encrypt(text);
    }

    // Returns the sender of this message.
    public String getSender() {
        return sender;
    }

    // Returns the decrypted message text.
    public String getText() {
        return CryptoUtil.decrypt(encryptedText);
    }

    // Returns a formatted message in format "sender: message".
    public String toString() {
        return sender + ": " + getText();
    }
}
