package com.company.UI;

import javax.swing.*;
import java.awt.*;

public class IndexFrame extends JFrame {
    public IndexFrame(){
        LoginPanel login = new LoginPanel();
        add(login, BorderLayout.CENTER);
        setSize(700,600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
