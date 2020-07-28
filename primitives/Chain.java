package com.company.primitives;

import org.apache.commons.codec.DecoderException;

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
        chain.add(genesis());
    }

    public Block genesis(){
        Block blk = new Block();
        blk.addTx(new Transaction("","4d465977454159484b6f5a497a6a3043415159464b34454541416f44516741456837314958797a306c75307333754a59304d4a44487a7370564a7a4f574c4c747568564d46506169672f3664566f4f37354e56324732746b376f695075376f43705338714d615879592b377868787632366b4e4a73673d3d",50));
        blk.hash();
        return blk;
    }

    public void add_tx(Transaction _tx) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, SignatureException, DecoderException {

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
        return chain.get(chain.size()-1);
    }

    public String mine_block(String miner_address){
        Block blk = new Block();
        Transaction coinBase = new Transaction(null,miner_address,0);
        if(pending_tx.size()>0) {
            blk.addTx(coinBase);
            for (int i = 0; i < pending_tx.size(); i++) {
                blk.addTx(pending_tx.get(i));
            }
            blk.setPrev_hash(latestBlock().getHash());
            blk.hash();
            chain.add(blk);
            pending_tx.clear();
            return blk.getHash();
        }else{
            throw new Error("No pending transactions");
        }
    }

    public boolean isValid() throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, DecoderException {
        for(int i = 0; i < chain.size()-1; i++){

            if(!chain.get(i).hasValidTxs()){
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
            for(int k = 0; k < blk.getHeight(); k++){
                if(blk.getTx(k).getTarget().equals(pubkey)){
                    bal+=blk.getTx(k).getValue();
                }
            }
        }
        return bal;
    }
}
