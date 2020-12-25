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

public class decryptGui {

    private JFrame mainView = new JFrame("RSA-Encrypt-Decrypt");
    private JPanel decryptorPanel = new JPanel();
    private JLabel keyJLabel = new JLabel();
    private JLabel textJLabel = new JLabel();
    private JTextField Key = new JTextField(2048);
    private JTextField text = new JTextField(2048);
    private JTextField output = new JTextField(2048);
    private JButton decryptText = new JButton("Decrypt Text");
    private JButton back = new JButton("Go Back");

    public decryptGui() {
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainView.setSize(512, 256);
        // mainView.setLayout(new GridLayout(1, 1));

        keyJLabel.setText("Private Key: ");
        textJLabel.setText("Cipher: ");
        decryptorPanel.setLayout(new GridLayout(4, 3));
        decryptorPanel.add(keyJLabel);
        decryptorPanel.add(Key);
        decryptorPanel.add(textJLabel);
        decryptorPanel.add(text);
        decryptorPanel.add(decryptText);
        decryptorPanel.add(output);
        decryptorPanel.add(back);

        mainView.add(decryptorPanel);
        mainView.setVisible(true);
        decryptText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                String privateKey = (Key.getText()).replaceAll("\\s", "").replaceAll("\\n", "");
                String cipher = (text.getText()).replaceAll("\\s", "").replaceAll("\\n", "");
                try {
                    String decryptedText = new String(RSASystem.decryptMessage(cipher, privateKey), StandardCharsets.UTF_8);
                    output.setText(decryptedText);
                } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

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
