package finalproject;

import java.util.Base64;

public class CryptoUtil {

    private static final String KEY = "StoreSecretKey";

    // Encrypts text using XOR with a secret key, then encodes to Base64.
    public static String encrypt(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char k = KEY.charAt(i % KEY.length());
            result += (char) (c ^ k);
        }

        return Base64.getEncoder().encodeToString(result.getBytes());
    }

    // Decrypts Base64-encoded text using XOR with a secret key.
    public static String decrypt(String encrypted) {
        byte[] decoded = Base64.getDecoder().decode(encrypted);
        String text = new String(decoded);

        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            char k = KEY.charAt(i % KEY.length());
            result += (char) (c ^ k);
        }

        return result;
    }

    // Compares plaintext with encrypted text, returns true if they match.
    public static boolean matches(String plainText, String encryptedText) {
        return decrypt(encryptedText).equals(plainText);
    }
}
