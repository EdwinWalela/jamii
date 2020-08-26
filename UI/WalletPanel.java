package com.company.UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WalletPanel extends JPanel {

    JLabel bal_label = new JLabel("Wallet Balance:");
    JLabel bal_value = new JLabel("0 Coins");

    JLabel send_label = new JLabel("Send:");
    JTextArea send_amount = new JTextArea("0");
    JTextArea send_addr = new JTextArea("");
    JButton send_btn = new JButton("Send");

    JLabel receive_label = new JLabel("Receive");
    JTextArea receive_addr = new JTextArea();


    public WalletPanel(){
        Font font = new Font("San Serif",Font.PLAIN,17);

        bal_label.setBounds(50,50,150,30);
        bal_value.setBounds(200,50,300,30);
        bal_label.setFont(font);
        bal_value.setFont(font);

        send_label.setBounds(50,100,100,30);
        send_amount.setBounds(200,100,100,30);
        send_addr.setBounds(50,150,300,100);
        send_addr.setFont(font);
        send_label.setFont(font);
        send_amount.setFont(font);

        send_addr.setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Target address"));
        receive_addr.setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"My address"));
        send_btn.setBounds(50,270,300,40);

        receive_label.setBounds(50,350,100,30);
        receive_addr.setBounds(50,400,300,100);
        receive_label.setFont(font);
        receive_addr.setFont(font);

        send_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                onSubmitClick();
            }
        });

        add(bal_label);
        add(bal_value);
        add(send_label);
        add(send_amount);
        add(send_addr);
        add(send_btn);
        add(receive_label);
        add(receive_addr);
        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"My Wallet"));
        setLayout(null);
        setVisible(true);
    }
}
