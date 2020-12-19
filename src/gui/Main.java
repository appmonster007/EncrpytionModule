package gui;

import java.io.IOException;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Scanner;

import cryptoModules.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        RSASystem keyPairGenerator = new RSASystem();
        keyPairGenerator.makeKey();
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

        File f = new File("RSA/privateKey.cert");
        Scanner ff = new Scanner(f);
        long lines = 0;
        Path path = Paths.get("RSA/privateKey.cert");
        try {
            lines = Files.lines(path).count();  
        } catch (IOException e) {
            e.printStackTrace();
        }
        ff.nextLine();
        String key = "";
        for(long i = 0; i<lines-2; i++){
            key += ff.nextLine();
        }


        try {
            System.out.println("------------------------------------------");
            deec = RSASystem.decryptMessage(enr, key);
            System.out.println(new String(deec, StandardCharsets.UTF_8));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("------------------------------------------");
            String textMessage = "hello";
            Base64.Decoder decoder = Base64.getDecoder();  
            Base64.Encoder encoder = Base64.getEncoder();  

            byte[] encryptedText = RSASystem.decryptMessage("AIa2/xm9p4wCL0/0AQB8ZUuzQkQzKj+TJjFcKB7Rf6XD0nvHaav125jjIdEdN1+wOFqm9WdJImmJnREXC/cdPm4=",
                                                            "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA1W1jhQoPcyxZg5FYMQYkfvuQQ7JY5d3KMDr73DELjB8Bg9yXvdAp2T5PW4306koL0oNomuo5OZBsbBZVIQnA0QIDAQABAkA9mF5abPbchHYpFG9hZqZtx2hxQS2K2aGanctGVxlADMPVeE9tNXIwBgWOmm5b2//2jk/SmvAQDAsqRrVX6y7pAiEA6fwqQJKHbV6PywT1E8zNpOojLN56zdWhyKEFB4IM8tMCIQDpghBsXw6ZKQJNz6B5FOZiDf/VExpNfer4DreLacLPSwIga1C2aEtMRcM7HBg3907Veq/QVkHr/P/gZc5EKj9hhPUCIF5mzbryRxWWZa8NdIPe9jDIFhiPS3/umxLD6GI9VJu3AiEAxFw+IpTluPNzWKTsaX7ZAjsLxfB7SWAKSZnPXS+8bVA=");
            
            // byte[] encryptedText = RSASystem.decryptMessage(new String(encoder.encode(keyPairGenerator.encryptMessage("hello"))),
            //                                                 new String(encoder.encode(keyPairGenerator.getPrivateKey().getEncoded())));
            System.out.println(Base64.getEncoder().encodeToString(encryptedText));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println(key);

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
