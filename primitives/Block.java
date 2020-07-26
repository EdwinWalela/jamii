package com.company.primitives;

import com.company.crypto.SHA256;
import org.apache.commons.codec.DecoderException;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class Block {
    private int DIFF = 1;
    private long timestamp;
    private int nonce = 0;
    private int height;
    private String prev_hash = "";
    private String hash = "";
    private String merkle_root = "";
    private List<Transaction> txs;

    public Block(){
        height = 0;
        timestamp = System.currentTimeMillis();
        txs = new ArrayList<>();
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
        height++;
    }

    public String getHash() {
        return hash;
    }

    public int getHeight() {
        return height;
    }

    public int getNonce() {
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


