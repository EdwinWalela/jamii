package com.company.primitives;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Wallets {
    Wallet[] wallets;
    public int BASE_WALLET = 0;
    public int SENDING_WALLET = 0;
    public int RECIEVEING_WALLET = 0;

    public Wallets(String seed) throws InvalidKeySpecException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException {
        // Wallet instances ( b - base wallet, s - sending wallet, r - receiving wallet )
        Wallet wal1 = new Wallet(seed+"b");
        Wallet wal2 = new Wallet(seed+"s");
        Wallet wal3 = new Wallet(seed+"r");

        wallets = new Wallet[]{wal1, wal2, wal3};
    }

    public Transaction transfer(int from_index,int to_index,double amount ) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Transaction tx = new Transaction(wallets[from_index].getPublicKeyHex(),wallets[to_index].getPublicKeyHex(),amount);
        tx.sign(wallets[from_index]);
        return tx;
    }

    public Wallet getWallet(int index){
        return wallets[index];
    }
}
