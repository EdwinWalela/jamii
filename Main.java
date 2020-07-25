package com.company;

import com.company.crypto.SHA256;
import com.company.crypto.EC;
import com.company.primitives.Chain;
import com.company.primitives.Wallet;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class Main {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException {
        Chain ch = new Chain();
        Wallet wal = new Wallet();

        EC s = new EC();

        s.gen_pair();
        String hash = SHA256.hash("edwin");
        s.sign(hash);
        System.out.println(s.getSignatureHex());
    }
}
