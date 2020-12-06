package cryptoModules;

// import java.util.Base64;
import java.io.File;
// import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
// import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
// import oracle.security.crypto.core.RSAPrivateKey;
// import oracle.security.crypto.core.*;

public class RSASystem {
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    int keySize = 1024;

    public RSASystem() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
        this.publicKey = (RSAPublicKey) pair.getPublic();
        try {
            this.writeToFile("RSA/publicKey.cert", "public", this.getPublicKey().getEncoded());
            this.writeToFile("RSA/privateKey.cert", "private", this.getPrivateKey().getEncoded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RSASystem(RSAPrivateKey privateKey, RSAPublicKey publicKey, int keySize) throws NoSuchAlgorithmException {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.keySize = keySize;
    }

    public RSASystem(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
        this.publicKey = (RSAPublicKey) pair.getPublic();
    }

    public void writeToFile(String path, String keyType, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileWriter fos = new FileWriter(f);
        fos.write("-----BEGIN RSA " + keyType.toUpperCase() + " KEY-----\n");
        String base64Encode = Base64.getEncoder().encodeToString(key);
        for (int i = 0; i < base64Encode.length(); i++) {
            if (i % 76 == 0 && i != 0) {
                fos.write("\n");
            }
            fos.write(base64Encode.charAt(i));
        }

        fos.write("\n-----END RSA " + keyType.toUpperCase() + " KEY-----");
        fos.flush();
        fos.close();
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    // Encrypting the message
    public byte[] encryptMessage(String message) {
        return (new BigInteger(message.getBytes()))
                .modPow(this.publicKey.getPublicExponent(), this.publicKey.getModulus()).toByteArray();
    }

    // Decrypting the message
    public byte[] decryptMessage(byte[] message) {
        return (new BigInteger(message)).modPow(this.privateKey.getPrivateExponent(), this.privateKey.getModulus())
                .toByteArray();
    }

    // Decrypting the message
    public static byte[] decryptMessage(byte[] message, BigInteger m, BigInteger p) {
        return (new BigInteger(message)).modPow(m, p).toByteArray();
    }

    // Decrypting the message
    public static byte[] decryptMessage(byte[] message, byte[] key)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory;
        PrivateKey privKey;
        RSAPrivateKey rsaKey;
        keyFactory = KeyFactory.getInstance("RSA");
        privKey = keyFactory.generatePrivate((KeySpec) new PKCS8EncodedKeySpec(key));
        rsaKey = (RSAPrivateKey) privKey;
        return (new BigInteger(message)).modPow(rsaKey.getPrivateExponent(), rsaKey.getModulus()).toByteArray();
    }
}