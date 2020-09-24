package com.company.UI;

import com.company.primitives.*;
import com.company.util.FileWriter;
import com.company.util.SocketConn;
import com.company.util.Values;
import com.sun.jdi.Value;
import org.apache.commons.codec.DecoderException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClientMainPanel extends JPanel {
    Chain jamii;
    WalletPanel wallet;
    Wallets wallets;
    PastTransactions pastTransactions;
    PendingTxPanel pendingTxsPanel;
    JTabbedPane tabbedPane;
    JSeparator separator;
    JLabel status;
    JButton syncBtn;



    public ClientMainPanel(){

        List<Block> blocks = new ArrayList<>();
        try {
            blocks = FileWriter.readBlock();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        if(blocks.size() > 0){
            jamii = new Chain(blocks);
        }else{
            jamii = new Chain();
        }

        wallet = new WalletPanel();
        tabbedPane = new JTabbedPane();
        pastTransactions = new PastTransactions();
        pendingTxsPanel = new PendingTxPanel(jamii.getPending_tx());
        separator = new JSeparator();
        status = new JLabel(" Status: Idle");
        syncBtn = new JButton("Sync");

        syncBtn.setBounds(350,15,70,20);
        tabbedPane.setBounds(20,15,400,570);
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
                    // TODO:
                }catch(Error er){
                    status.setBackground(Values.ERROR_STATUS_BACKGROUND);
                    status.setText("Status: "+er.getMessage());
                }
            }
        });

        pendingTxsPanel.refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pendingTxsPanel.txList.removeAll();
                pendingTxsPanel.updateTx(jamii.getPending_tx());
                pendingTxsPanel.buildTxList();
            }
        });

        pendingTxsPanel.mineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyTxs();
            }
        });

        syncBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renitWallets();
                syncPastTx();
                //TODO: Discover peers and request blocks from them
            }
        });

        tabbedPane.add("Wallet",wallet);
        tabbedPane.add("History",pastTransactions);
        tabbedPane.add("Pending",pendingTxsPanel);

        add(syncBtn);
        add(tabbedPane);
        add(status);
        setLayout(null);

        setSize(890,610);
        setVisible(true);
    }

    public void verifyTxs(){
//        SocketConn conn = null;
//
//        try {
//            conn = new SocketConn();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        conn.start();
        status.setText("Mining block...");
        String miner_address = wallets.getWallet(wallets.BASE_WALLET).getPublicKeyHex();
        Block blk = null;
        try {
            blk = jamii.mine_block(miner_address);
            status.setBackground(Values.SUCCESS_STATUS_BACKGROUND);
            status.setText(" Block mined ["+blk.getHash()+"]");
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return;
        }catch (Error er){
            status.setBackground(Values.ERROR_STATUS_BACKGROUND);
            status.setText("Status: "+er.getMessage());
            return;
        }
//
//        DataOutputStream out = conn.bind("localhost",Values.SOCKET_PORT_SECONDARY);
//
//        // Convert new block to JSON string
//        String JSONblock = FileWriter.blockToJSON(blk).toJSONString();
//
//        try {
//            out.writeUTF(JSONblock);
//        } catch (IOException e) {
//            status.setBackground(Values.ERROR_STATUS_BACKGROUND);
//            status.setText("Status: "+e.getMessage());
//        }

    }

    public void initTransaction() throws DecoderException, NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException, InvalidKeyException, InvalidKeySpecException {
        wallet.initTransaction(jamii);
    }

    public void initWallets(Wallets _wallets){
        wallets = _wallets;
        wallet.initWallets(_wallets,jamii);
    }

    public void renitWallets(){
        wallet.reinitWallets(jamii);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();

        status.setText("Status: Last sync [ " +dtf.format(now)+ " ]");
        status.setBackground(Values.IDLE_STATUS_BACKGROUND);
        status.setForeground(Values.STATUS_FOREGROUND);
    }

    public void syncPastTx(){
        pastTransactions.updateHistory(jamii.getPastTx(wallets.getWallet(wallets.BASE_WALLET).getPublicKeyHex()));
        pastTransactions.buildTxList();
    }



}
