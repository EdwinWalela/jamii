package com.company.blockchain;

import com.company.crypto.SHA256;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Block {
    private int DIFF = 5;
    private long timestamp;
    private int nonce = 0;
    private int height;
    private String prev_hash = "";
    private String hash = "";
    private String merkle_root = "";
    private List<Transaction> tx;

    public Block(){
        height = 0;
        timestamp = System.currentTimeMillis();
        tx = new ArrayList<>();
    }

    public void hash(){
        String tx_hash = "";

        for(int i = 0; i < tx.size(); i++){
            tx_hash+=tx.get(i).getHash();
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
        tx.add(_tx);
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
        return tx.get(index);
    }

    public void setPrev_hash(String prev_hash) {
        this.prev_hash = prev_hash;
    }
}


