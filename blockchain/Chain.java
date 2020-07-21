package com.company.blockchain;

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
        blk.addTx(new Transaction("",""));
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
        Transaction coinBase = new Transaction(miner_address,"");
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
}
