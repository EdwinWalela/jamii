package com.company.UI;

import com.company.primitives.Transaction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PastTransactions extends JPanel {
    public PastTransactions(){
        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Past Transactions"));
        setLayout(null);
        setVisible(true);
    }
}
