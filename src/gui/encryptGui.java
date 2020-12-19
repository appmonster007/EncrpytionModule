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

public class encryptGui {

    private JFrame mainView = new JFrame("RSA-Encypt-Decrypt");
    private JPanel encryptorPanel = new JPanel();
    private JLabel keyJLabel = new JLabel();
    private JLabel textJLabel = new JLabel();
    private JTextField Key = new JTextField(2048);
    private JTextField text = new JTextField(2048);
    private JTextField output = new JTextField(2048);
    private JButton encryptText = new JButton("Encrypt Text");
    private JButton back = new JButton("Go Back");

    public encryptGui() {
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setSize(512, 256);
        // mainView.setLayout(new GridLayout(1, 1));

        keyJLabel.setText("Public Key: ");
        textJLabel.setText("Message: ");
        encryptorPanel.setLayout(new GridLayout(4, 3));
        encryptorPanel.add(keyJLabel);
        encryptorPanel.add(Key);
        encryptorPanel.add(textJLabel);
        encryptorPanel.add(text);
        encryptorPanel.add(encryptText);
        encryptorPanel.add(output);
        encryptorPanel.add(back);

        mainView.add(encryptorPanel);
        mainView.setVisible(true);
        encryptText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                String publicKey = (Key.getText()).replaceAll("\\s", "");
                String textMessage = text.getText();
                try {
                    String encryptedText = new String(Base64.getEncoder().encode(RSASystem.encryptMessage(publicKey, textMessage)), StandardCharsets.UTF_8);
                    output.setText(encryptedText);
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
