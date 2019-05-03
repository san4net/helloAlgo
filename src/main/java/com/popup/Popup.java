package com.popup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Popup {
    public static void showPopup() {
        final JFrame frame = new JFrame("Eye Care");
        Timer timer = new Timer(20 * 60 * 1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Hey, enter the temp!!");
            }
        });

        timer.start();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public static void main(String[] args) {
        showPopup();
    }
}
