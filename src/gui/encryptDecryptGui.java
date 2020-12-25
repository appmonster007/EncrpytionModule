package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cryptoModules.RSASystem;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class encryptDecryptGui {

    private JFrame mainView = new JFrame("RSA-Encypt-Decrypt");
    private JPanel encryptorPanel = new JPanel();
    private JLabel publicKeyJLabel = new JLabel();
    private JLabel privateKeyJLabel = new JLabel();
    private JTextField cipher = new JTextField(2048);
    private JTextField message = new JTextField(2048);
    private JButton encryptText = new JButton("Process");
    private JButton back = new JButton("Go Back");

    public encryptDecryptGui(RSASystem keys) {
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainView.setSize(512, 256);
        // mainView.setLayout(new GridLayout(1, 1));

        publicKeyJLabel.setText("Message: ");
        privateKeyJLabel.setText("Cipher: ");
        encryptorPanel.setLayout(new GridLayout(3, 2));
        encryptorPanel.add(publicKeyJLabel);
        encryptorPanel.add(cipher);
        encryptorPanel.add(privateKeyJLabel);
        encryptorPanel.add(message);
        encryptorPanel.add(encryptText);
        encryptorPanel.add(back);

        mainView.add(encryptorPanel);
        mainView.setVisible(true);
        encryptText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                String cipherText = (cipher.getText()).replaceAll("\\s", "");
                String textMessage = (message.getText()).replaceAll("\\s", "");
                String encryptedText = "", decryptedText = "";
                if(cipher.getText() == "")
                    encryptedText = new String(Base64.getEncoder().encode(keys.encryptMessage(textMessage)), StandardCharsets.UTF_8);
                else if(message.getText() == "")
                    decryptedText = new String(keys.decryptMessage(cipherText.getBytes()), StandardCharsets.UTF_8);
            
                message.setText(decryptedText);
                cipher.setText(encryptedText);
            }
        });

        back.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                mainView.setVisible(false);
                new rsaView();

            }
        });
    }

}
