/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.security;

import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.*;
/**
 *
 * @author Andrew
 */
public class Crypto {
    
    Cipher aesCipher;

    public static void main(String[] args) throws Exception {
        //String FileName = "encryptedtext.txt";
        //String FileName2 = "decryptedtext.txt";

        KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
        KeyGen.init(128);

        SecretKey SecKey = KeyGen.generateKey();

        Cipher AesCipher = Cipher.getInstance("AES");

        byte[] byteText = "Test".getBytes();

        AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
        byte[] byteCipherText = AesCipher.doFinal(byteText);
        
        
        System.out.println("Encrypted " + byteCipherText.toString());
        
        byte[] cipherText = byteText;
        AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
        byte[] bytePlainText = AesCipher.doFinal(cipherText);
        
        System.out.println("Decrypted " + bytePlainText.toString());
        /**Files.write(Paths.get(FileName), byteCipherText);

        byte[] cipherText = Files.readAllBytes(Paths.get(FileName));

        AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
        byte[] bytePlainText = AesCipher.doFinal(cipherText);
        Files.write(Paths.get(FileName2), bytePlainText);
        **/
        
    }
    
}
