package cloud;

import org.bouncycastle.util.Strings;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Encryption {

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        // create keys
        PublicKey pk = loadPublicKey();
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, pk);
        String secretMessage = "9hhWQVqZMTuBJQUisBPfrzxwMcVcuBcf";

        byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);
        System.out.println("encrypted msg " + Strings.fromByteArray(encryptedMessageBytes));
        System.out.println("encrypted msg length" + encryptedMessageBytes.length);

        PrivateKey privateKey = loadPrivatekey();
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

        System.out.println("decrypted msg"+ decryptedMessage);
    }

    private static PublicKey loadPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        byte[] keyBytes = Files.readAllBytes(Path.of("/users/santosh-breaking-wave/Documents/id_webhook_pub"));
        // Strip the "BEGIN" and "END" lines and decode the key
        String publicKeyPEM = new String(keyBytes)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        // Decode the Base64 encoded string
        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);

        // Create the public key from the decoded bytes
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
//       byte[] publicKeyBytes =  Files.readAllBytes(Path.of("/users/santosh-breaking-wave/Documents/id_webhook_pub"));
//
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
//        return keyFactory.generatePublic(publicKeySpec);
    }


    private static PrivateKey loadPrivatekey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        byte[] keyBytes = Files.readAllBytes(Path.of("/users/santosh-breaking-wave/Documents/id_webhook"));
        // Strip the "BEGIN" and "END" lines and decode the key
        String privateKeyPEM = new String(keyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        // Decode the Base64 encoded string
        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);

        // Create the private key from the decoded bytes
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }


}
