package com.company.UI;

import com.company.primitives.Transaction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PendingTxPanel extends JPanel {

    List<Transaction> pendingTxs;

    JButton mineBtn = new JButton("Verify Pending Transactions");
    JButton refreshBtn = new JButton("Refresh");
    JPanel txList = new JPanel();

    public PendingTxPanel(List<Transaction> _pendingTxs){
        pendingTxs = _pendingTxs;
        Font font = new Font("San Serif",Font.PLAIN,13);

        txList.setBounds(5,13,400,450);
        mineBtn.setBounds(50,475,200,30);
        refreshBtn.setBounds(270,475,80,30);

        mineBtn.setFont(font);
        refreshBtn.setFont(font);

        txList.setLayout(null);
        txList.setVisible(true);

        add(txList);
        add(mineBtn);
        add(refreshBtn);

        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Pending Transactions"));
        setLayout(null);
        setVisible(true);
    }

    public void updateTx(List<Transaction> _tx){
        pendingTxs = _tx;
    }

    public void buildTxList(){
        int size = pendingTxs.size();
        int y = 0;
        for(int i = 0; i<size; i++){
            Transaction tx = pendingTxs.get(i);

            JLabel from = new JLabel("From: "+tx.getFrom());
            JLabel to = new JLabel("Target: "+tx.getTarget());
            JLabel signature = new JLabel("Signature: "+tx.getSignature());
            JLabel tx_hash = new JLabel("Hash: "+tx.getHash());
            JSeparator separator = new JSeparator();

            from.setBounds(20,y+30,350,50);
            to.setBounds(20, y+55 , 350,50);
            signature.setBounds(20,y+80,350,50);
            tx_hash.setBounds(20,y+105,350,50);
            separator.setBounds(20,y+150,350,50);

            txList.add(from);
            txList.add(to);
            txList.add(signature);
            txList.add(tx_hash);
            txList.add(separator);
            y+=120;
        }
        revalidate();
        repaint();
    }

}
