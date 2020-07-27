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
        Chain ch = new Chain(); // New chain instance
        Wallet wal1 = new Wallet(); // Wallet instances
        Wallet wal2 = new Wallet();

        String address1 = wal1.getPublicKeyHex(); // Retrieve wallet's address in HEX
        String address2 = wal2.getPublicKeyHex();

        Transaction tx1 = new Transaction(address1,address2,10); // Create transaction-

        tx1.sign(wal1); // Transaction's initator signs the transaction

        ch.add_tx(tx1); // Transaction sent to the chain

        ch.mine_block(address1); // Verification of pending transactions

        double bal = ch.getBalance(address2); // Query wallet's balance

        System.out.println("Balance"+bal);

        boolean valid = ch.isValid(); // Check chain's validity

        System.out.println("Chain valid?:"+valid);

    }
}
