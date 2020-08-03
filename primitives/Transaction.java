package com.company.primitives;
import com.company.crypto.EC;
import com.company.crypto.SHA256;
import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

// Defines a transaction object

public class Transaction {
    private String from = "";
    private String target = "";
    private double value;
    private String hash;
    private String signature;

    public Transaction(String _from,String _target,double _value){
        from = _from;
        target = _target;
        value = _value;
        signature = "";

        try {
            hash = SHA256.hash(from + target + value);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    public void sign(Wallet wal) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Only allow signing of own transactions
        if(!wal.getPublicKeyHex().equals(from)){
            System.out.println(wal.getPublicKeyHex());
            System.out.println(from);
            throw new Error("Invalid signing key");
        }
        wal.sign(hash);
        signature = wal.getSignature64();
    }

    public boolean isValid() throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, DecoderException {

        if(from == null){
            return true;
        }
        if(from.length() == 0){ // Coinbase transactions are valid
            return true;
        }
        if(signature.length() == 0) {
            return false;
        }
            // transaction must be signed

        EC ec = new EC();

        // Check if the transaction has been signed by the initiator
        return ec.verify(from,signature,hash);

    }

    public String getFrom(){
        return from;
    }

    public double getValue() {
        return value;
    }

    public String getTarget(){
        return target;
    }

    public String getSignature() {
        return signature;
    }

    public String getHash(){
        return hash;
    }
}
