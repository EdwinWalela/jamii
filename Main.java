package com.company;

import com.company.crypto.EC;

public class Main {

    public static void main(String[] args) {

        EC curve = new EC();

        curve.gen();
        String pub = curve.getPubKey();
        String priv = curve.getPrivKey();
        curve.calc();

        System.out.println("public key:"+pub+"\n");
        System.out.println("private key:"+priv+"\n");
        System.out.println(curve.keyMatch());
    }
}
