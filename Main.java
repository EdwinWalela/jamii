package com.company;

import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallet;
import com.company.primitives.Wallets;

public class Main {

    public static void main(String[] args) throws Exception {
        Chain ch = new Chain();

        Wallets wals = new Wallets("edwin"); // Wallet passphrase

        // Transfer Funds from Receiving wallet to Base wallet
        Transaction tx = wals.transfer(wals.RECIEVEING_WALLET,wals.BASE_WALLET,10);

        ch.add_tx(tx); // Submit new transaction
        ch.mine_block(""); // Verifying pending transactions
        System.out.println(ch.latestBlock().getTx(0).getValue()); // Print out latest Transaction
    }
}
