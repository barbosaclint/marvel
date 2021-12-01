package com.clint.marvel.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class MarvelUtil {

    public static Instant instant = Instant.now();
    public static long timeStamp = instant.toEpochMilli();

    public static String MD5hash(String privateKey, String publicKey, Long timestamp) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String toHash = timestamp + privateKey + publicKey;

            return new BigInteger(1, md.digest(toHash.getBytes())).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            ex.getMessage();
        }

        return null;
    }

}
