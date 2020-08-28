package com.company.UI;

import com.company.crypto.SHA256;
import com.company.primitives.Chain;
import com.company.primitives.Wallets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class IndexFrame extends JFrame{
    Chain jamii;
    Wallets wallets;
    LoginPanel login;
    CardLayout card;
    Container c;
    ClientMainPanel client;

    public IndexFrame(){
        card = new CardLayout();
        setLayout(card);

        login = new LoginPanel();
        client = new ClientMainPanel();

        add("login",login);
        add("client",client);

        login.submit_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmitClick();
            }
        });

        setTitle("Jamii");
        setSize(890,650);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void onSubmitClick(){
        try{
            String username = login.username_input.getText();
            char[] passphrase = login.phrase_input.getPassword();

            String wallet_seed = SHA256.hash(username+passphrase);

            wallets = new Wallets(wallet_seed);
            client.initWallets(wallets);

            card.show(getContentPane(),"client");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
