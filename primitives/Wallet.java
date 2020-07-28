package com.company.primitives;

import com.company.crypto.EC;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Wallet extends EC {
    private double balance;

    public Wallet(boolean existing_key,String path) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidAlgorithmParameterException {
        if(existing_key){
            readFromFile();
        }else{
            gen_pair();
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
