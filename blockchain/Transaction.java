package com.company.blockchain;

// Defines a transaction object

public class Transaction {
    private String from;
    private String target;
    private String hash;

    public Transaction(String _from,String _target){
        from = _from;
        target = _target;
    }
}
