package com.company.UI;

import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallet;
import com.company.primitives.Wallets;
import com.company.util.Values;
import com.sun.jdi.Value;
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
    JSeparator separator;
    JLabel status;

    public ClientMainPanel(){
        jamii = new Chain();
        wallet = new WalletPanel();
        tabbedPane = new JTabbedPane();
        pastTransactions = new PastTransactions();
        pendingTxsPanel = new PendingTxPanel(jamii.getPending_tx());
        separator = new JSeparator();
        status = new JLabel(" Status: Idle");

        tabbedPane.setBounds(20,10,400,570);
        wallet.setBounds(20,10,400,550);
        pastTransactions.setBounds(450,10,400,290);
        pendingTxsPanel.setBounds(450,310,400,300);
        separator.setBounds(0,593,450,10);
        status.setBounds(0,590,450,25);

        status.setOpaque(true);
        status.setBackground(Values.IDLE_STATUS_BACKGROUND);
        status.setForeground(Values.STATUS_FOREGROUND);

        wallet.send_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    initTransaction();
                    status.setText("Status: Transaction sent");
                    status.setBackground(Values.SUCCESS_STATUS_BACKGROUND);
                }catch(Exception ex){
                    System.out.println("message : "+ex.getMessage());
                    status.setBackground(Values.ERROR_STATUS_BACKGROUND);
                    status.setText("Status: Failed to create transaction");
                }catch(Error er){
                    System.out.println("message : "+er.getMessage());
                    status.setBackground(Values.ERROR_STATUS_BACKGROUND);
                    status.setText("Status: Failed to create transaction");
                }
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
        add(status);
        setLayout(null);

        setSize(890,610);
        setVisible(true);
    }

    public void initTransaction() throws DecoderException, NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException, InvalidKeyException, InvalidKeySpecException {
        wallet.initTransaction(jamii);;
    }

    public void initWallets(Wallets _wallets){
        wallets = _wallets;
        wallet.initWallets(_wallets,jamii);
    }

}
