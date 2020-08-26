package com.company.UI;

import javax.swing.*;
import java.awt.*;

public class ClientMainPanel extends JPanel {

    WalletPanel wallet;
    PastTransactions pastTransactions;

    public ClientMainPanel(){
        wallet = new WalletPanel();
        pastTransactions = new PastTransactions();

        wallet.setBounds(20,10,400,550);
        pastTransactions.setBounds(450,10,400,550);


        add(wallet);
        add(pastTransactions);
        setLayout(null);

        setSize(890,600);
        setVisible(true);
    }

}
