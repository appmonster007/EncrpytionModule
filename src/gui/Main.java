package gui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

import cryptoModules.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        RSASystem keyPairGenerator = new RSASystem();
        System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
        System.out.println(keyPairGenerator.getPublicKey());
        System.out.println();
        System.out.println(keyPairGenerator.getPrivateKey().getEncoded());
        System.out.println(keyPairGenerator.getPublicKey().getModulus());
        System.out.println();
        System.out.println(keyPairGenerator.getPublicKey().getPublicExponent());

        System.out.println();
        byte[] enr = keyPairGenerator.encryptMessage("hello");
        byte[] dec = keyPairGenerator.decryptMessage(enr);
        System.out.println(new String(dec, StandardCharsets.UTF_8));
        byte[] deec;
        try {
            deec = RSASystem.decryptMessage(enr, keyPairGenerator.getPrivateKey().getEncoded());
            System.out.println(new String(deec, StandardCharsets.UTF_8));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        easyRSA rsa = new easyRSA();
        Scanner input = new Scanner(System.in);
        String inputString;
        System.out.println("Enter message you wish to send.");
        inputString = input.nextLine();
        System.out.println("Encrypting the message: " + inputString);
        System.out.println("The message in bytes is:: "
                + easyRSA.bToS(inputString.getBytes()));
        // encryption
        byte[] cipher = rsa.encryptMessage(inputString.getBytes());
        // decryption
        byte[] plain = rsa.decryptMessage(cipher);
        System.out.println("Decrypting Bytes: " + easyRSA.bToS(plain));
        System.out.println("Plain message is: " + new String(plain));
        input.close();
    }

}
