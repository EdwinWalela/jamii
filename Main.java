package com.company;

import com.company.blockchain.Transaction;

public class Main {

    public static void main(String[] args) {
	// write your code
        Transaction tx = new Transaction("edwin","joy");
        System.out.println(tx.getHash());
    }
}
