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

    JLabel fromHeader = new JLabel("From");
    JLabel toHeader = new JLabel("Target");
    JLabel signatureHeader = new JLabel("Signature");

    JButton mineBtn = new JButton("Verify Pending Transactions");
    JButton refreshBtn = new JButton("Refresh");

    public PendingTxPanel(List<Transaction> _pendingTxs){
        pendingTxs = _pendingTxs;
        Font font = new Font("San Serif",Font.PLAIN,13);

        fromHeader.setBounds(20,0,100,100);
        toHeader.setBounds(160, 0, 100,100);
        signatureHeader.setBounds(300,0,100,100);
        mineBtn.setBounds(50,520,200,30);
        refreshBtn.setBounds(270,520,80,30);

        mineBtn.setFont(font);
        refreshBtn.setFont(font);
        fromHeader.setFont(font);
        toHeader.setFont(font);
        signatureHeader.setFont(font);

        add(fromHeader);
        add(toHeader);
        add(signatureHeader);
        add(mineBtn);
        add(refreshBtn);


        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Pending Votes"));
        setLayout(null);
        setVisible(true);
    }

    public void updateTx(List<Transaction> _tx){
        pendingTxs = _tx;
    }

    public void buildTxList(){
        int size = pendingTxs.size();
        int y = 20;
        for(int i = 0; i<size; i++){
            Transaction tx = pendingTxs.get(i);

            String fromAddress = tx.getFrom();
            String toAddress = tx.getTarget();

            String fromHead = fromAddress.substring(0,3);
            String fromTail = fromAddress.substring(fromAddress.length()-3,fromAddress.length());

            String toHead = toAddress.substring(0,3);
            String toTail = toAddress.substring(toAddress.length()-3,toAddress.length());

            JLabel from = new JLabel(fromHead+"..."+fromTail);
            JLabel to = new JLabel(toHead+"..."+toTail);
            JLabel signature = new JLabel(tx.getSignature());



            from.setBounds(20,y+50,60,50);
            to.setBounds(140, y+50 , 60,50);
            signature.setBounds(260,y+50,60,50);

            add(from);
            add(to);
            add(signature);
            y+=20;
        }
        revalidate();
        repaint();
    }

}
