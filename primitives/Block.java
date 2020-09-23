package com.company.primitives;

import com.company.crypto.SHA256;
import com.company.util.Values;
import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class Block {
    private int DIFF = Values.DIFFICULTY;
    private long timestamp;
    private long nonce = 0;
    private long volume;
    private String prev_hash = "";
    private String hash = "";
    private String merkle_root = "";
    private List<Transaction> txs;

    public Block(){
        volume = 0;
        timestamp = System.currentTimeMillis();
        txs = new ArrayList<>();
    }

    public Block(long _timestamp,long _nonce,long _volume,String _prev_hash, String _hash,List<Transaction> _txs){
        timestamp = _timestamp;
        nonce = _nonce;
        volume = _volume;
        prev_hash = _prev_hash;
        hash = _hash;
        txs = _txs;
    }

    public void hash(){
        String tx_hash = "";

        for(int i = 0; i < txs.size(); i++){
            tx_hash+=txs.get(i).getHash();
        }
        try {
            do{
                hash = SHA256.hash(tx_hash+nonce+prev_hash);
                nonce++;
            }while(!hashVald());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    public boolean hashVald(){
        for(int i = 0; i < DIFF; i++){
            if(hash.charAt(i) == '0'){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }

    public void addTx(Transaction _tx){
        txs.add(_tx);
        volume++;
    }

    public String getHash() {
        return hash;
    }

    public long getVolume() {
        return volume;
    }

    public long getNonce() {
        return nonce;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPrev_hash() {
        return prev_hash;
    }

    public int getDIFF() {
        return DIFF;
    }

    public Transaction getTx(int index) {
        if(index >= txs.size()) {
         throw new Error("Transaction index out of range");
        }
        return txs.get(index);
    }

    public void setPrev_hash(String prev_hash) {
        this.prev_hash = prev_hash;
    }

    public boolean hasValidTxs() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, SignatureException, DecoderException {
        for (Transaction tx :
                txs) {
            if(!tx.isValid()) { // Check validity of all tx in the block
                return false;
            }
        }
        return true;
    }

}


