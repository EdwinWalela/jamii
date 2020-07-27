package com.company.crypto;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.util.encoders.HexEncoder;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class EC {

    ECGenParameterSpec ecSpec;
    KeyPairGenerator g;
    KeyPair keypair;
    PublicKey publicKey;
    PrivateKey privateKey;
    KeyFactory kf;
    EncodedKeySpec publicKeySpec;
    KeyFactory keyFactory;
    Signature ecdsaVerify;
    byte[] signature_bytes;

    public String pubKey;
    public String privKey;
    public String signature;

    Signature ecdsaSign;

    public EC() {

    }

    protected void gen_pair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ecSpec = new ECGenParameterSpec("secp256k1");
        g = KeyPairGenerator.getInstance("EC");
        g.initialize(ecSpec, new SecureRandom());
        keypair = g.generateKeyPair();

        publicKey = keypair.getPublic();
        privateKey = keypair.getPrivate();

        pubKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        privKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public void sign(String tx_hash) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(tx_hash.getBytes("UTF-8"));
        signature_bytes = ecdsaSign.sign();
        signature = Base64.getEncoder().encodeToString(signature_bytes);
    }

    public boolean verify(String _pubkey,String _signature,String tx_hash) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, SignatureException, DecoderException {
        ecdsaVerify = Signature.getInstance("SHA256withECDSA");
        kf = KeyFactory.getInstance("EC");


        byte[] decodedHex = Hex.decodeHex(_pubkey);

        publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(decodedHex));
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(tx_hash.getBytes("UTF-8"));
        return ecdsaVerify.verify(Base64.getDecoder().decode(_signature));
    }

    public String getPublicKeyHex(){
        return toHex(pubKey.getBytes());
    }

    public String getPublicKey64() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public String getSignatureHex(){
        return toHex(signature.getBytes());
    }

    public String getSignature64() {
        return Base64.getEncoder().encodeToString(signature_bytes);
    }

    public String getPrivateKeyHex(){
        return toHex(privKey.getBytes());
    }

    public String getPrivateKey64(){
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }


    private String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b: data) {
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }
}
