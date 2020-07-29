package com.company;

import com.company.primitives.Chain;
import com.company.primitives.Wallet;

public class Main {

    public static void main(String[] args) throws Exception {
        Chain ch = new Chain(); // New chain instance
        Wallet wal1 = new Wallet("edwin"); // Wallet instance
        String pr = wal1.getPrivateKey64();
        String pv = wal1.getPublicKeyHex();
        System.out.println(pr);
        System.out.println(pv);
    }
}
