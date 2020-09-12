package com.company.UI;

import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallets;
import com.company.util.FileWriter;
import org.apache.commons.codec.DecoderException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class WalletPanel extends JPanel {
    double balance = 0;
    Wallets my_wallets;
    JLabel bal_label = new JLabel("Wallet Balance:");
    JLabel bal_value = new JLabel(balance+" Coins");
    JLabel send_label = new JLabel("Send:");
    JTextArea send_amount = new JTextArea("0");
    JTextArea send_addr = new JTextArea("");
    JButton send_btn = new JButton("Send");
    JTextArea receive_addr = new JTextArea();

    public WalletPanel(){
        Font font = new Font("San Serif",Font.PLAIN,17);
        Font address_font = new Font("San Serif",Font.PLAIN,13);

        bal_label.setBounds(20,50,150,30);
        bal_value.setBounds(150,50,300,30);
        bal_label.setFont(font);
        bal_value.setFont(font);

        send_label.setBounds(20,100,100,30);
        send_amount.setBounds(150,100,100,30);
        send_addr.setBounds(20,150,355,150);
        send_addr.setLineWrap(true);
        send_addr.setFont(address_font);
        send_label.setFont(font);
        send_amount.setFont(font);

        send_addr.setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"Target address"));
        receive_addr.setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"My address"));
        send_btn.setBounds(20,310,355,30);

        receive_addr.setBounds(20,365,355,150);
        receive_addr.setFont(address_font);
        receive_addr.setLineWrap(true);

        add(bal_label);
        add(bal_value);
        add(send_label);
        add(send_amount);
        add(send_addr);
        add(send_btn);
        add(receive_addr);
        setBorder(new TitledBorder(new LineBorder(Color.lightGray,2),"My Wallet"));
        setLayout(null);
        setVisible(true);
    }

    public void initWallets(Wallets wallets,Chain _jamii){
        FileWriter.initNode(); // Create Blocks directory
        my_wallets = wallets;
        receive_addr.setText(my_wallets.getWallet(my_wallets.BASE_WALLET).getPublicKeyHex());

        balance = _jamii.getBalance(my_wallets.getWallet(my_wallets.BASE_WALLET).getPublicKeyHex());
        bal_value.setText(balance+" Coins");
        revalidate();
        repaint();

    }

    public void initTransaction(Chain _jamii){
        String from = my_wallets.getWallet(my_wallets.BASE_WALLET).getPublicKeyHex();
        String to = send_addr.getText();
        Double value = Double.valueOf(send_amount.getText());
        Transaction tx = new Transaction(from,to,value);
        try {
            tx.sign(my_wallets.getWallet(my_wallets.BASE_WALLET));
            _jamii.add_tx(tx);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        }
    }
}
