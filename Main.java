package com.company;

import com.company.blockchain.Chain;
import com.company.blockchain.Transaction;
import com.company.blockchain.Wallet;
import com.company.crypto.EC;

public class Main {

    public static void main(String[] args) {
        Chain ch = new Chain();
        Wallet wal = new Wallet();

        System.out.println(wal.getPubKey());
        System.out.println(wal.getPrivKey());

        ch.add_tx( new Transaction("",wal.getPubKey(),50)); // new transaction

        ch.mine_block(wal.getPubKey()); // verify new transaction (add it to chain)

        System.out.println(ch.getBalance(wal)); // new balance
    }
}
