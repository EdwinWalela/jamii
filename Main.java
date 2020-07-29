package com.company;

import com.company.primitives.*;
import com.company.util.FileWriter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        /*
            Users are provided by 3 wallets (base,sending & receiving) each consisting of a key pair
            To create a new wallet set, a passphrase is provided to the wallet generator
            Similarly to unlock a previously generated wallet set, the same passphrase will be required
        */
        Wallets my_wallets = new Wallets("edwin");

        String base_wall_pubKey = my_wallets.getWallet(my_wallets.BASE_WALLET).getPublicKeyHex();
        String base_wall_privKey = my_wallets.getWallet(my_wallets.BASE_WALLET).getPrivateKeyHex();

        String sending_wall_pubKey = my_wallets.getWallet(my_wallets.SENDING_WALLET).getPublicKeyHex();
        String sending_wall_privKey = my_wallets.getWallet(my_wallets.SENDING_WALLET).getPrivateKeyHex();

        String recieving_wall_pubKey = my_wallets.getWallet(my_wallets.RECIEVEING_WALLET).getPublicKeyHex();
        String recieving_wall_privKey = my_wallets.getWallet(my_wallets.RECIEVEING_WALLET).getPrivateKeyHex();

        System.out.println("-------------------------------"+
                "\nbase wallet keypair:\n" +
                "private: "+base_wall_privKey+"\n" +
                "public: "+base_wall_pubKey+"\n");
        System.out.println("sending wallet keypair:\n" +
                "private: "+sending_wall_privKey+"\n" +
                "public: "+sending_wall_pubKey+"\n");
        System.out.println("receiving wallet keypair:\n" +
                "private: "+recieving_wall_privKey+"\n" +
                "public: "+recieving_wall_pubKey+"\n" +
                "-------------------------------\n");
        /*
            The use of 3 wallets per user improves anonymity
        */

        Chain ch = new Chain(); // Blockchain instance

        int blockHeight = ch.getBlockHeight();

        System.out.println("Current block height: "+blockHeight); // Height = number of blocks in the blockchain

        Block genesis = ch.latestBlock(); // First block in the chain

        /*
            Retrieve genesis block's contents
        */

        int nonce = genesis.getNonce();
        String hash = genesis.getHash();
        boolean valid_Tx = genesis.hasValidTxs();
        String to = genesis.getTx(0).getTarget();
        int volume = genesis.getVolume();
        double value = genesis.getTx(0).getValue();

        /*
            Genesis (First) Transactions includes a hardcoded transaction of 50 coins to a predefined address
        */
        System.out.println("---------------------------\n" +
                "Genesis block contents\n" +
                "nonce: "+nonce+"\n" +
                "hash: "+hash+"\n" +
                "tx_valid: "+valid_Tx+"\n" +
                "to_address: "+to+"\n" +
                "height: "+volume+"\n" +
                "value: "+value+"\n" +
                "---------------------------\n");

        double base_balance = ch.getBalance(base_wall_pubKey);
        double sending_balance = ch.getBalance(sending_wall_pubKey);
        double receiving_balance = ch.getBalance(recieving_wall_pubKey);

        System.out.println("My wallets balance\n" +
                "base (primary): "+base_balance+"\n" +
                "sending (secondary): "+sending_balance+"\n" +
                "receiving (secondary): "+ receiving_balance+"\n");

        /*
            To create a transaction, the sender's and recipient's public keys are required,
            plus the amount to be transferred
        */
        Transaction tx = new Transaction(base_wall_pubKey,"random address",5);
        /*
            To ensure that the transaction is being initiated by the actual wallet owner,
            it needs to be signed using the private key of the wallet which contains the funds
        */
        tx.sign(my_wallets.getWallet(my_wallets.BASE_WALLET));
        /*
            New transactions submitted to the blockchain are treated as pending transactions
        */

        ch.add_tx(tx);

        /*
            Mining (proof of work) verifies and bundles all pending transactions into a block
            Miner's public key is provided incase of mining rewards
        */
        ch.mine_block(base_wall_pubKey);

        Block latest = ch.latestBlock();
        nonce = latest.getNonce();
        hash = latest.getHash();
        valid_Tx = latest.hasValidTxs();
        to = latest.getTx(0).getTarget();
        volume = latest.getVolume();
        value = latest.getTx(0).getValue();

        System.out.println("---------------------------\n" +
                "Block contents\n" +
                "nonce: "+nonce+"\n" +
                "hash: "+hash+"\n" +
                "tx_valid: "+valid_Tx+"\n" +
                "to_address: "+to+"\n" +
                "height: "+volume+"\n" +
                "value: "+value+"\n" +
                "---------------------------\n");

        FileWriter.writeJSON(latest);

    }

}
