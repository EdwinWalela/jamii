package com.company;

import com.company.primitives.Block;
import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallets;

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
        int height = genesis.getHeight();
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
                "height: "+height+"\n" +
                "value: "+value+"\n" +
                "---------------------------\n");

    }
}
