import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
// import java.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
// import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class moduleRSA {
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    int keySize = 1024;

    public moduleRSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = (RSAPrivateKey)pair.getPrivate();
        this.publicKey = (RSAPublicKey)pair.getPublic();
    }

    public moduleRSA(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = (RSAPrivateKey)pair.getPrivate();
        this.publicKey = (RSAPublicKey)pair.getPublic();
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
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
    public byte[] encryptMessage(String message)
    {
        return (new BigInteger(message.getBytes())).modPow(
            this.publicKey.getPublicExponent(),
            this.publicKey.getModulus()
        ).toByteArray();
    }

    // Decrypting the message
    public byte[] decryptMessage(byte[] message)
    {
        return (new BigInteger(message)).modPow(
            this.privateKey.getPrivateExponent(), 
            this.privateKey.getModulus()
        ).toByteArray();
    }
    
    // public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
    //     moduleRSA keyPairGenerator = new moduleRSA();
    //     keyPairGenerator.writeToFile("RSA/publicKey", keyPairGenerator.getPublicKey().getEncoded());
    //     keyPairGenerator.writeToFile("RSA/privateKey", keyPairGenerator.getPrivateKey().getEncoded());
    //     System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
    //     System.out.println(keyPairGenerator.getPublicKey());
    //     System.out.println();
    //     System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));
    //     System.out.println(keyPairGenerator.getPublicKey().getModulus());
    //     System.out.println();
    //     System.out.println(keyPairGenerator.getPublicKey().getPublicExponent());
        
    //     System.out.println();
    //     byte [] enr = keyPairGenerator.encryptMessage("hello");
    //     byte[] dec = keyPairGenerator.decryptMessage(enr);
    //     System.out.println(new String(dec, StandardCharsets.UTF_8));

    // }
}
