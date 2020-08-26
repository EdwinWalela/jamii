package com.company.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    JLabel username_label = new JLabel("Username");
    JLabel phrase_label = new JLabel("Passphrase");

    JTextField username_input = new JTextField();
    JPasswordField phrase_input = new JPasswordField();
    JButton submit_btn = new JButton("Unlock Wallet");

    public LoginPanel(){
        Font font = new Font("San Serif",Font.PLAIN,18);

        username_label.setBounds(50,50,100,30);
        username_input.setBounds(200,50,300,30);
        username_input.setFont(font);
        username_label.setFont(font);

        phrase_label.setBounds(50,150,100,30);
        phrase_input.setBounds(200,150,300,30);
        phrase_input.setFont(font);
        phrase_label.setFont(font);
        submit_btn.setBounds(200,250,300,40);

        add(username_label);
        add(username_input);
        add(phrase_label);
        add(phrase_input);
        add(submit_btn);
        setSize(700,600);
        setLayout(null);
        setVisible(true);

    }

}
