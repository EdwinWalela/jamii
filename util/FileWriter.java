package com.company.util;

import com.company.primitives.Block;
import com.company.primitives.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;


public class FileWriter {
    public static void writeJSON(Block block) throws IOException {
        JSONObject blockObj = new JSONObject();
        blockObj.put("timestamp",block.getTimestamp());
        blockObj.put("nonce",block.getNonce());
        blockObj.put("volume",block.getVolume());
        blockObj.put("prev-hash",block.getPrev_hash());
        blockObj.put("hash",block.getHash());

        JSONArray txs = new JSONArray();

        for(int i = 0; i < block.getVolume(); i++){
            Transaction tx = block.getTx(i);
            JSONObject tx_obj = new JSONObject();
            tx_obj.put("from",tx.getFrom());
            tx_obj.put("target",tx.getTarget());
            tx_obj.put("value",tx.getValue());
            tx_obj.put("hash",tx.getHash());
            tx_obj.put("signature",tx.getSignature());

            txs.add(tx_obj);

        }

        blockObj.put("transactions",txs);
        String FILE_NAME = block.getTimestamp()+Values.BLOCK_FORMAT;

        File block_dump = new File(Values.BLOCK_DIR+FILE_NAME);
        System.out.println(Values.BLOCK_DIR+FILE_NAME);
        block_dump.createNewFile();

        java.io.FileWriter f = new java.io.FileWriter(block_dump);

        f.write(blockObj.toJSONString());
        f.close();
    }
}
