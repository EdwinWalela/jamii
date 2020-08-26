package com.company.UI;

import javax.swing.*;
import java.awt.*;

public class IndexFrame extends JFrame {

    public IndexFrame(){

        LoginPanel login = new LoginPanel();
        ClientMainPanel client = new ClientMainPanel();

        add(client);
        setTitle("Jamii");
        setLayout(null);
        setSize(890,650);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
