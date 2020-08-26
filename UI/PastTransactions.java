package com.company.UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class PastTransactions extends JPanel {

    public PastTransactions(){
        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Past Transactions"));
        setLayout(null);
        setVisible(true);
    }
}
