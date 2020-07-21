package com.company.blockchain;
import com.company.crypto.SHA256;

import java.security.NoSuchAlgorithmException;

// Defines a transaction object

public class Transaction {
    private String from;
    private String target;
    private String hash;

    public Transaction(String _from,String _target){
        from = _from;
        target = _target;

        try {
            hash = SHA256.hash(from + target);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    public String getFrom(){
        return from;
    }

    public String getTarget(){
        return target;
    }

    public String getHash(){
        return hash;
    }
}
