package com.company;

import com.company.blockchain.Block;
import com.company.blockchain.Transaction;

public class Main {

    public static void main(String[] args) {
	// write your code
        Block bl = new Block();
        System.out.println(bl.getHash());
    }
}
