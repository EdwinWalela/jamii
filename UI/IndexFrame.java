package com.company.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class IndexFrame extends JFrame{
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void onSubmitClick( ){
        try{
            String username = login.username_input.getText();
            char[] passphrase = login.phrase_input.getPassword();
            card.show(getContentPane(),"client");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
