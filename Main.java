package com.company;

import com.company.crypto.ECBC;
import com.company.primitives.Block;
import com.company.primitives.Chain;
import com.company.primitives.Transaction;
import com.company.primitives.Wallet;
import io.github.novacrypto.bip39.MnemonicGenerator;
import io.github.novacrypto.bip39.Words;
import io.github.novacrypto.bip39.wordlists.English;
import org.apache.commons.codec.DecoderException;
import org.web3j.crypto.Sign;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class Main {

    public static void main(String[] args) throws Exception {
        Chain ch = new Chain(); // New chain instance
        Wallet wal1 = new Wallet(false,".privkey"); // Wallet instances
    }
}
