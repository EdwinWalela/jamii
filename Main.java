package com.company;

import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallet;
import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class Main {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, SignatureException, InvalidKeySpecException, DecoderException {
        Chain ch = new Chain();
        Wallet wal1 = new Wallet();
        Wallet wal2 = new Wallet();

        String add1 = wal1.getPublicKeyHex();
        String add2 = wal2.getPublicKeyHex();

        Transaction tx1 = new Transaction(add1,add2,10);
        Transaction tx2 = new Transaction(add1,add2,5.85);

        tx1.sign(wal1);
        tx2.sign(wal1);

        ch.add_tx(tx1);
        ch.add_tx(tx2);

        ch.mine_block(add1);

        double bal = ch.getBalance(add1);

        System.out.println(bal);

    }
}
