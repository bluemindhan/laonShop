package com.laonworks.shop.api.controller.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

public class CryptoUtils {

    public static String encryptPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return pbkdf2(password, salt, 1000, 128);
    }

    public static String pbkdf2(String password, String salt, int iterations, int keyLength) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(chars, salt.getBytes(), iterations, keyLength * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return toHex(hash);
    }

    private static String toHex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(Integer.toString((array[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String generateSalt() throws NoSuchAlgorithmException {
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        mdMD5.update(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        return toHex(mdMD5.digest());
    }
}
