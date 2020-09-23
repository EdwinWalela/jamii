package com.company.util;

import com.company.primitives.Block;
import com.company.primitives.Transaction;
import org.apache.commons.codec.DecoderException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileWriter {

    public static void initNode(){
        // Create Blocks directory
        String PATH = Values.BLOCK_DIR;
        File directory = new File(PATH);
        if(!directory.exists()) {
            directory.mkdir();
        }
    }

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
        block_dump.createNewFile();

        java.io.FileWriter f = new java.io.FileWriter(block_dump);

        f.write(blockObj.toJSONString());
        f.close();
    }

    public static List<Block> readBlock() throws FileNotFoundException, ParseException, DecoderException, NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException, InvalidKeyException, InvalidKeySpecException {
        File folder = new File(Values.BLOCK_DIR);
        File[] listOfFiles = folder.listFiles();
        List<Block> blocks = new ArrayList<>();
        JSONParser parser = new JSONParser();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    Object obj = parser.parse(data);
                    JSONObject jsonObj = (JSONObject) obj;

                    long timestamp = (long) jsonObj.get("timestamp");
                    long nonce = (long) jsonObj.get("nonce");
                    long volume = (long) jsonObj.get("volume");
                    String prev_hash = (String) jsonObj.get("prev-hash");
                    String hash = (String) jsonObj.get("hash");

                    List<Transaction> txs = new ArrayList<>();

                    JSONArray txs_json = (JSONArray) jsonObj.get("transactions");

                    for(int i = 0; i < txs_json.size(); i++){
                        JSONObject tx_json = (JSONObject) txs_json.get(i);

                        String signature = (String) tx_json.get("signature");
                        String from = (String) tx_json.get("from");
                        double value = (double) tx_json.get("value");
                        String tx_hash = (String) tx_json.get("hash");
                        String target = (String) tx_json.get("target");

                        Transaction tx = new Transaction(from,target,value,tx_hash,signature);

                        if(tx.isValid()){
                            txs.add(tx);
                        }

                    }

                    blocks.add(new Block(timestamp,nonce,volume,prev_hash,hash,txs));
                }
                reader.close();
            }
        }
        return  blocks;
    }
}
