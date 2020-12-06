
// import java.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
// import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.*;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
// import oracle.security.crypto.core.RSAPrivateKey;
// import oracle.security.crypto.core.*;

public class moduleRSA {
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    int keySize = 1024;

    public moduleRSA() throws NoSuchAlgorithmException {
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

    public moduleRSA(RSAPrivateKey privateKey, RSAPublicKey publicKey, int keySize) throws NoSuchAlgorithmException {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.keySize = keySize;
    }

    public moduleRSA(int keySize) throws NoSuchAlgorithmException {
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
    throws NoSuchAlgorithmException,
            InvalidKeySpecException 
    {
        KeyFactory keyFactory;
        PrivateKey privKey;
        RSAPrivateKey rsaKey;
        keyFactory = KeyFactory.getInstance("RSA");
        privKey = keyFactory.generatePrivate((KeySpec) new PKCS8EncodedKeySpec(key));
        rsaKey = (RSAPrivateKey)privKey;
        return (new BigInteger(message)).modPow(
            rsaKey.getPrivateExponent(), 
            rsaKey.getModulus()
        ).toByteArray();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        moduleRSA keyPairGenerator = new moduleRSA();
        // keyPairGenerator.writeToFile("RSA/publicKey.cert", keyPairGenerator.getPublicKey().getEncoded());
        // keyPairGenerator.writeToFile("RSA/privateKey.cert", keyPairGenerator.getPrivateKey().getEncoded());
        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
        System.out.println(keyPairGenerator.getPublicKey());
        System.out.println();
        System.out.println(keyPairGenerator.getPrivateKey().getEncoded());
        System.out.println(keyPairGenerator.getPublicKey().getModulus());
        System.out.println();
        System.out.println(keyPairGenerator.getPublicKey().getPublicExponent());
        
        System.out.println();
        byte [] enr = keyPairGenerator.encryptMessage("hello");
        byte[] dec = keyPairGenerator.decryptMessage(enr);
        System.out.println(new String(dec, StandardCharsets.UTF_8));
        byte[] deec;
        try {
            deec = moduleRSA.decryptMessage(enr, keyPairGenerator.getPrivateKey().getEncoded());
            System.out.println(new String(deec, StandardCharsets.UTF_8));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
