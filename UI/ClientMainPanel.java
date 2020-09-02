package com.company.UI;

import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallets;
import org.apache.commons.codec.DecoderException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class ClientMainPanel extends JPanel {
    Chain jamii;
    WalletPanel wallet;
    PastTransactions pastTransactions;
    PendingTxPanel pendingTxsPanel;

    public ClientMainPanel(){
        jamii = new Chain();
        wallet = new WalletPanel();
        pastTransactions = new PastTransactions();
        pendingTxsPanel = new PendingTxPanel(jamii.getPending_tx());

        wallet.setBounds(20,10,400,600);
        pastTransactions.setBounds(450,10,400,290);
        pendingTxsPanel.setBounds(450,310,400,300);

        wallet.send_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initTransaction();
            }
        });

        pendingTxsPanel.refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pendingTxsPanel.updateTx(jamii.getPending_tx());
                pendingTxsPanel.buildTxList();
            }
        });

        add(wallet);
        add(pastTransactions);
        add(pendingTxsPanel);
        setLayout(null);

        setSize(890,600);
        setVisible(true);
    }

    public void initTransaction(){
        wallet.initTransaction(jamii);
    }

    public void initWallets(Wallets _wallets){
        wallet.initWallets(_wallets,jamii);
    }

}
