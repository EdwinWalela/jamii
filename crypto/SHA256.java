package com.company.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    static String sha256(String source) throws NoSuchAlgorithmException {
        // Get algorithim instance
        final MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        // Calculate Digest Value (returns byte array)
        byte[] result = mDigest.digest((source).getBytes());
        // StringBuffer to store Hex Digest
        StringBuffer sb = new StringBuffer();
        // Convert Digest Value(byte array) to Hex
        for(int i = 0; i < result.length;i++){
            sb.append(Integer.toString((result[i] & 0xff) + 0x100,16).substring(1));
        }
        // Return Digest as Hex String
        return sb.toString();
    }
}