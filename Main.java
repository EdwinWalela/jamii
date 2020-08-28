package com.company;

import com.company.UI.IndexFrame;
import com.company.primitives.*;
import com.company.util.FileWriter;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
//        /*
//            Users are provided by 3 wallets (base,sending & receiving) each consisting of a key pair
//            To create a new wallet set, a passphrase is provided to the wallet generator
//            Similarly to unlock a previously generated wallet set, the same passphrase will be required
//        */
//        Wallets my_wallets = new Wallets("edwin");
//
//        String base_wall_pubKey = my_wallets.getWallet(my_wallets.BASE_WALLET).getPublicKeyHex();
//
//        /*
//            The use of 3 wallets per user improves anonymity
//        */
//
//        Chain ch = new Chain(); // Blockchain instance
//
//        int blockHeight = ch.getBlockHeight();
//
//        System.out.println("Current block height: "+blockHeight); // Height = number of blocks in the blockchain
//
//        /*
//            Genesis (First) Transactions includes a hardcoded transaction of 50 coins to a predefined address
//        */
//
//        /*
//            To create a transaction, the sender's and recipient's public keys are required,
//            plus the amount to be transferred
//        */
//        for(int i = 0; i < 5; i++) {
//            Transaction tx = new Transaction(base_wall_pubKey, "random address", 5);
//        /*
//            To ensure that the transaction is being initiated by the actual wallet owner,
//            it needs to be signed using the private key of the wallet which contains the funds
//        */
//            tx.sign(my_wallets.getWallet(my_wallets.BASE_WALLET));
//        /*
//            New transactions submitted to the blockchain are treated as pending transactions
//        */
//
//            ch.add_tx(tx);
//        }
//        /*
//            Mining (proof of work) verifies and bundles all pending transactions into a block
//            Miner's public key is provided incase of mining rewards
//        */
//            ch.mine_block(base_wall_pubKey);
        IndexFrame index = new IndexFrame();
        FileWriter.initNode();

    }

}
