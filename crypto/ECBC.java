package com.company.crypto;

import java.lang.reflect.Array;
import java.math.BigInteger;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.*;
import java.math.BigInteger;
import java.security.*;

public class ECBC {
    String publicKey;
    String privateKey;
    ECKeyPair keyPair;
    BigInteger privKey;
    BigInteger pubKey;
    String signatureHex;

    public ECBC(){

    }

    public static String compressPubKey(BigInteger pubKey) {
        String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
        String pubKeyHex = pubKey.toString(16);
        String pubKeyX = pubKeyHex.substring(0, 64);
        return pubKeyYPrefix + pubKeyX;
    }

    public void genKeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        privKey = Keys.createEcKeyPair().getPrivateKey();
//      privKey = new BigInteger("97ddae0f3a25b92268175400149d65d6887b9cefaf28ea2c078e05cdc15a3c0a", 16);
        pubKey = Sign.publicKeyFromPrivate(privKey);
        keyPair = new ECKeyPair(privKey, pubKey);
        privateKey = privKey.toString(16);
        publicKey = pubKey.toString(16);
//      compressPubKey(pubKey);

    }

    public Sign.SignatureData sign(String msg) {
        byte[] msgHash = Hash.sha3(msg.getBytes());
        Sign.SignatureData signature = Sign.signMessage(msgHash,keyPair);
        signatureHex =  Hex.toHexString(signature.getR()) + Hex.toHexString(signature.getS());
        return signature;
    }

    public boolean verify(String msg, Sign.SignatureData signature,BigInteger pubKey) throws SignatureException {
        BigInteger pubKeyRecovered = Sign.signedMessageToKey(msg.getBytes(), signature);
        return pubKey.equals(pubKeyRecovered);
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public BigInteger getPubKey() {
        return pubKey;
    }

    public BigInteger getPrivKey() {
        return privKey;
    }
}
