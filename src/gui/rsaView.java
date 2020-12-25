package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;

public class rsaView {

    private JFrame mainView = new JFrame("RSA-Encypt-Decrypt");
    private JPanel rsaView = new JPanel();
    private JButton createKeyPair = new JButton("Create Key Pair");
    private JButton Encrpytor = new JButton("Encrypt text");
    private JButton Decrpytor = new JButton("Decrypt text");
    private JButton setPair = new JButton("Set Key Pair");

    public rsaView () {
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setSize(512, 512);
        mainView.setLayout(new GridLayout(1, 1));

        rsaView.setLayout(new GridLayout(5, 5));
        rsaView.add(createKeyPair);
        rsaView.add(Encrpytor);
        rsaView.add(Decrpytor);
        rsaView.add(setPair);

        mainView.add(rsaView);
        mainView.setVisible(true);
        createKeyPair.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                mainView.setVisible(false);
                new createPairGui();
            }
        });

        Encrpytor.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                mainView.setVisible(false);
                new encryptGui();
            }
        });

        Decrpytor.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                mainView.setVisible(false);
                new decryptGui();
            }
        });

        setPair.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                mainView.setVisible(false);
                new setKeyGui();
            }
        });
    }
    
}
