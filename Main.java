package com.company;

import com.company.blockchain.Block;
import com.company.blockchain.Chain;
import com.company.blockchain.Transaction;

public class Main {

    public static void main(String[] args) {

        Chain jamii = new Chain();
        jamii.add_tx(new Transaction("edwin","joy"));
        jamii.mine_block("edwin");

    }
}
