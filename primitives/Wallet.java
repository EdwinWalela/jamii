package com.company.primitives;

import com.company.crypto.EC;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

public class Wallet extends EC {
    private double balance;

    public Wallet() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        gen_pair();
        balance = 0.0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
