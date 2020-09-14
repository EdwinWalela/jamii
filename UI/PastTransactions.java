package com.company.UI;

import com.company.primitives.Transaction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PastTransactions extends JPanel {

    List<Transaction> pastTx;
    JPanel txList = new JPanel();

    public PastTransactions(){

        txList.setBounds(10,10,400,500);
        txList.setLayout(null);
        txList.setVisible(true);
        txList.setOpaque(false);
        add(txList);
        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Past Transactions"));
        setLayout(null);
        setVisible(true);
    }

    public void updateHistory(List<Transaction> _tx){ pastTx = _tx;}

    public void buildTxList(){
        txList.removeAll();
        int size = pastTx.size();
        int y = 0;

        for(int i = 0; i<size; i++){
            Transaction tx = pastTx.get(i);

            JTextArea target = new JTextArea(tx.getTarget());
            JLabel value = new JLabel("Value: "+ tx.getValue());
            JSeparator separator = new JSeparator();

            target.setBounds(20,y+30,350,85);
            value.setBounds(20,y+105,400,50);
            separator.setBounds(20,y+145,350,50);

            target.setLineWrap(true);
            target.setEditable(false);
            txList.add(target);
            txList.add(value);
            txList.add(separator);
            y+=150;
        }
        revalidate();
        repaint();
    }
}
