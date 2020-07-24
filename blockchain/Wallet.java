package com.company.blockchain;

import com.company.crypto.EC;

public class Wallet extends EC{
    private double balance;

    public Wallet() {
        gen(); // Generate key pair
        balance = 0.0;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
