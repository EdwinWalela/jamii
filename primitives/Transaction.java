package com.company.primitives;
import com.company.crypto.SHA256;

import java.security.NoSuchAlgorithmException;

// Defines a transaction object

public class Transaction {
    private String from;
    private String target;
    private double value;
    private String hash;

    public Transaction(String _from,String _target,double _value){
        from = _from;
        target = _target;
        value = _value;
        try {
            hash = SHA256.hash(from + target + value);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    public String getFrom(){
        return from;
    }

    public double getValue() {
        return value;
    }

    public String getTarget(){
        return target;
    }

    public String getHash(){
        return hash;
    }
}
