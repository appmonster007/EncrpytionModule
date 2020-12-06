package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class guiView{
    private JPanel controlPanel;
    private JFrame mainView;
    public void prepareGUI(){
        mainView = new JFrame("Crpyto-Hasher");
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainView.setSize(1024, 1024);
        mainView.setLayout(new GridLayout(1, 1));
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 5));
        JButton button = new JButton("Press");
        JTextField tf1 = new JTextField(100);
        JTextField tf2 = new JTextField(100);
        controlPanel.add(button);
        controlPanel.add(tf1);
        controlPanel.add(tf2);
        controlPanel.add(new JPanel());
        mainView.getContentPane().add(controlPanel);
        mainView.setVisible(true);
    }

    public guiView(){
       prepareGUI();
    }

    public static void main (String [] args){
        guiView gui = new guiView();
    }
}
