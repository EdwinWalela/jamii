package com.company.UI;

import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallet;
import com.company.primitives.Wallets;
import org.apache.commons.codec.DecoderException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class ClientMainPanel extends JPanel {
    Chain jamii;
    WalletPanel wallet;
    Wallets wallets;
    PastTransactions pastTransactions;
    PendingTxPanel pendingTxsPanel;
    JTabbedPane tabbedPane;

    public ClientMainPanel(){
        jamii = new Chain();
        wallet = new WalletPanel();
        tabbedPane = new JTabbedPane();
        pastTransactions = new PastTransactions();
        pendingTxsPanel = new PendingTxPanel(jamii.getPending_tx());

        tabbedPane.setBounds(20,10,400,570);
        wallet.setBounds(20,10,400,550);
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

        pendingTxsPanel.mineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String miner_address = wallets.getWallet(wallets.BASE_WALLET).getPublicKeyHex();
                try {
                    jamii.mine_block(miner_address);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        tabbedPane.add("Wallet",wallet);
        tabbedPane.add("History",pastTransactions);
        tabbedPane.add("Pending",pendingTxsPanel);
        add(tabbedPane);
        setLayout(null);

        setSize(890,610);
        setVisible(true);
    }

    public void initTransaction(){
        wallet.initTransaction(jamii);
    }

    public void initWallets(Wallets _wallets){
        wallets = _wallets;
        wallet.initWallets(_wallets,jamii);
    }

}
