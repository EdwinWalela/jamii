package com.company.primitives;

import com.company.util.FileWriter;
import com.company.util.Values;
import com.sun.jdi.Value;
import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class Chain {
    private List<Block> chain;
    private List<Transaction> pending_tx;

    public Chain(){
        chain = new ArrayList<>();
        pending_tx = new ArrayList<>();
        genesis();
    }

    public Chain(List<Block> blks){
        chain = blks;
        pending_tx = new ArrayList<>();
    }

    private void genesis(){
        Block blk = new Block();
        Transaction tx = new Transaction(null, Values.GENESIS_ADDRESS, Values.GENESIS_VALUE);
        pending_tx.add(tx);
        try {
            mine_block(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add_tx(Transaction _tx) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, SignatureException, DecoderException, Error {

        if(_tx.getFrom().length() == 0 || _tx.getTarget().length() == 0 ){
            throw new Error("From & Target address is required");
        }

        if(!_tx.isValid()){
            throw new Error("Invalid transaction");
        }

        if(getBalance(_tx.getFrom()) < _tx.getValue()){
            throw new Error("Insufficient Balance");
        }

        pending_tx.add(_tx);
    }

    public Block latestBlock(){
        if(chain.size() != 0){
            return chain.get(chain.size()-1);
        }else{
            return new Block();
        }

    }

    public String mine_block(String miner_address) throws IOException {
        Block blk = new Block();
        Transaction coinBase = new Transaction(null,miner_address,Values.MINER_REWARD);
        if(pending_tx.size()>0) {
            blk.addTx(coinBase);
            for (int i = 0; i < pending_tx.size(); i++) {
                blk.addTx(pending_tx.get(i));
            }
            blk.setPrev_hash(latestBlock().getHash());
            blk.hash();
            chain.add(blk);
            pending_tx.clear();
        }else{
            throw new Error("No pending transactions");
        }
        FileWriter.writeJSON(blk);
        return blk.getHash();
    }

    public boolean isValid() throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, DecoderException {
        for(int i = 0; i < chain.size()-1; i++){

            if(!chain.get(i).hasValidTxs() || !chain.get(i+1).hasValidTxs()){
                return false;
            }

            if(chain.get(i+1).getPrev_hash() == chain.get(i).getHash()){ // Check chains' integrity
                continue;
            }else{
                return false;
            }
        }
        return true;
    }

    public double getBalance(String pubkey){
        double bal = 0;
        for(int i = 0; i < chain.size(); i++){
            Block blk = chain.get(i);
            for(int k = 0; k < blk.getVolume(); k++){
                if(blk.getTx(k).getTarget() != null && blk.getTx(k).getTarget().equals(pubkey)){
                    bal+=blk.getTx(k).getValue();
                }
                if(blk.getTx(k).getFrom().equals(pubkey)){
                    bal-=blk.getTx(k).getValue();
                }
            }
        }
        for(int i = 0; i < pending_tx.size(); i++){
            Transaction tx = pending_tx.get(i);
            if(tx.getFrom().equals(pubkey)){
                bal-=tx.getValue();
            }
        }
        return bal;
    }

    public int getBlockHeight(){
        return chain.size();
    }

    public List<Transaction> getPending_tx(){
        return pending_tx;
    }

    public List<Transaction> getPastTx(String pubkey){
        List<Transaction> txs = new ArrayList<>();

        for(int i = 0; i < chain.size(); i++){
            Block blk = chain.get(i);
            for(int k = 0; k < blk.getVolume(); k++){
                Transaction tx = blk.getTx(k);
                if(tx.getFrom().equals(pubkey)){
                   txs.add(tx);
                }
            }
        }
        return txs;
    }
}
