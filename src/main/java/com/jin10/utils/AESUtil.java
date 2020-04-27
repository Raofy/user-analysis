package com.jin10.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
public class AESUtil {

    public static final String INIT_VECTOR = "RandomInitVector";

    /**
     * 加密
     *
     * @param key       密钥
     * @param plainText 加密数据
     * @return
     */
    public static String encrypt(String key, String plainText) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));

            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(plainText.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param key           密钥
     * @param encryptedText 解密数据
     * @return
     */
    public static String decrypt(String key, String encryptedText) {
        try {
            log.info("key:  " + key + ", encryptedText= " + encryptedText);
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encryptedText));
            return new String(original);
        } catch (Exception ex) {
            log.error("解密数据异常: " + ex);
            ex.printStackTrace();
        }
        return null;
    }

}
