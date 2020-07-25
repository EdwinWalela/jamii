package com.company.primitives;

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
        blk.addTx(new Transaction("","",0));
        blk.hash();
        return blk;
    }

    public void add_tx(Transaction _tx){
        pending_tx.add(_tx);
    }

    public Block latestBlock(){
        return chain.get(chain.size()-1);
    }

    public String mine_block(String miner_address){
        Block blk = new Block();
        Transaction coinBase = new Transaction(miner_address,"",0);
        if(pending_tx.size()>0){
            blk.addTx(coinBase);
            for(int i = 0; i < pending_tx.size(); i++) {
                blk.addTx(pending_tx.get(i));
            }
        }
        blk.setPrev_hash(latestBlock().getHash());
        blk.hash();
        chain.add(blk);
        pending_tx.clear();
        return blk.getHash();
    }

    public boolean isValid(){
        for(int i = 0; i < chain.size()-1; i++){
            if(chain.get(i+1).getPrev_hash() == chain.get(i).getHash()){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }

    public double getBalance(Wallet wal){
//        String pubkey = wal.getPubKey();
        double bal = 0;
//        for(int i = 0; i < chain.size(); i++){
//            Block blk = chain.get(i);
//            for(int k = 0; k < blk.getHeight(); k++){
//                if(blk.getTx(k).getTarget() == pubkey){
//                    bal+=blk.getTx(k).getValue();
//                }
//            }
//        }
        return bal;
    }
}
