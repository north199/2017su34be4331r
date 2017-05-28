/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.security;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

/**
 *
 * @author Andrew, Originally built by FARHAN KHWAJA and released open source.
 * The code has been modified for increased security and code reuse.
 * 
 * My modifications:
 * Improved encryption standard to use Advanced Encryption Standards (AES)
 * Modified and strengthened 15-bit private key
 * Base64 encoding/decoding post encryption/pre decryption for compatibility and further security
 */
public class Crypto {

    // Neccessary imports
    private static final String encryptionAlgorithm = "AES";
    private static final byte[] keyValue
            = new byte[]{'S', 'u', 'b', 'e', 'r', '2', '6', '0', '5', '=', '=', '=', '=', '=', '=', '='};

    /** 
     * Function for debugging stuff
     * 
     * @param args 
     */
    public static void main(String[] args) throws Exception {
        System.out.println(encryptString("123edsaq"));
    }
    
    /**
     * Function to AES encrypt input
     *
     * @param unencryptedData String to encrypt
     * @return base 64 encoded AES-encrypted string
     * @throws Exception
     */
    public static String encryptString(String unencryptedData) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(unencryptedData.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    /**
     * Function to decrypt AES encrypted input
     *
     * @param encryptedData encrypted and encoded base64 string
     * @return decrypted and decoded data
     * @throws Exception
     */
    public static String decryptString(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    /**
     * Function to generate a new unique algorithm to ensure security
     *
     * @return new key
     * @throws Exception
     */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, encryptionAlgorithm);
        return key;
    }

}
