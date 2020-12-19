package gui;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cryptoModules.RSASystem;

import java.awt.*;
import java.security.NoSuchAlgorithmException;

public class createPairGui {
    private JFrame mainView = new JFrame("RSA-Encypt-Decrypt");
    private JPanel createPairPanel = new JPanel();
    private JButton createKeyPair = new JButton("Create Key Pair");
    private JButton back = new JButton("Go Back");

    public createPairGui() {
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setSize(512, 512);
        mainView.setLayout(new GridLayout(1, 1));

        createPairPanel.setLayout(new GridLayout(5, 5));
        createPairPanel.add(createKeyPair);
        createPairPanel.add(back);

        mainView.add(createPairPanel);
        mainView.setVisible(true);
        createKeyPair.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RSASystem keyPairGenerator = new RSASystem();
                try {
                    keyPairGenerator.makeKey();
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
