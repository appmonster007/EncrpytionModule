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

public class setKeyGui {

    private JFrame mainView = new JFrame("RSA-Encypt-Decrypt");
    private JPanel encryptorPanel = new JPanel();
    private JLabel publicKeyJLabel = new JLabel();
    private JLabel privateKeyJLabel = new JLabel();
    private JTextField publicKey = new JTextField(2048);
    private JTextField privateKey = new JTextField(2048);
    private JButton encryptText = new JButton("Set Keys");
    private JButton back = new JButton("Go Back");

    public setKeyGui() {
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainView.setSize(512, 256);
        // mainView.setLayout(new GridLayout(1, 1));

        publicKeyJLabel.setText("Public Key: ");
        privateKeyJLabel.setText("Private Key: ");
        encryptorPanel.setLayout(new GridLayout(4, 3));
        encryptorPanel.add(publicKeyJLabel);
        encryptorPanel.add(publicKey);
        encryptorPanel.add(privateKeyJLabel);
        encryptorPanel.add(privateKey);
        encryptorPanel.add(encryptText);
        encryptorPanel.add(back);

        mainView.setSize(512, 512);
        mainView.add(encryptorPanel);
        mainView.setVisible(true);
        encryptText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                String publicKeytext = (publicKey.getText()).replaceAll("\\s", "");
                String privateKeytext = privateKey.getText();
                try {
                    RSASystem keys = new RSASystem(publicKeytext, privateKeytext);
                    new encryptDecryptGui(keys);

                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
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
