package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class guiView{
    private JPanel controlPanel = new JPanel();
    private JFrame mainView = new JFrame("Crpyto-Hasher");


    public void prepareMainGUI(){

        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setSize(512, 512);
        mainView.setLayout(new GridLayout(1, 1));
        controlPanel.setLayout(new GridLayout(1, 1));


        JButton rsaButton = new JButton("RSA Encrypt/Decrypt");
        controlPanel.add(rsaButton);

        // controlPanel.add(new JPanel());s
        mainView.getContentPane().add(controlPanel);
        // mainView.getContentPane().add(rsaView);
        mainView.setVisible(true);
        rsaButton.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                mainView.setVisible(false);
                new rsaView();
            }
        });

    }

    public guiView(){
       prepareMainGUI();
    }

    public static void main (String [] args){
        guiView gui = new guiView();
    }
}
