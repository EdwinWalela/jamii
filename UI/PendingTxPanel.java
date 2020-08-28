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

    public PendingTxPanel(List<Transaction> _pendingTxs){
        pendingTxs = _pendingTxs;
        Font font = new Font("San Serif",Font.PLAIN,13);

        fromHeader.setBounds(20,0,100,100);
        toHeader.setBounds(160, 0, 100,100);
        signatureHeader.setBounds(300,0,100,100);
        mineBtn.setBounds(110,250,200,30);

        mineBtn.setFont(font);
        fromHeader.setFont(font);
        toHeader.setFont(font);
        signatureHeader.setFont(font);

        add(fromHeader);
        add(toHeader);
        add(signatureHeader);
        add(mineBtn);

        buildTxList();

        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Pending Votes"));
        setLayout(null);
        setVisible(true);
    }


    private void buildTxList(){
        int size = pendingTxs.size();
        int y = 20;
        for(int i = 0; i<size; i++){
            Transaction tx = pendingTxs.get(i);

            JLabel from = new JLabel(tx.getFrom());
            JLabel to = new JLabel(tx.getTarget());
            JLabel signature = new JLabel(tx.getSignature());

            from.setBounds(20,y+50,100,50);
            to.setBounds(160, y+50 , 100,50);
            signature.setBounds(300,y+50,100,50);

            add(from);
            add(to);
            add(signature);
            y+=20;
        }
    }

}
