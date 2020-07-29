package com.company.primitives;

import com.company.crypto.EC;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Wallet extends EC {
    private double balance;

    public Wallet(String seed) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException {
       gen_pair(seed);

    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
