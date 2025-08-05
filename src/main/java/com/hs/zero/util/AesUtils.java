package com.hs.zero.util;

import cn.hutool.crypto.digest.DigestUtil;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

public class AesUtils {


    /**
     * 解密
     * @param sSrc
     * @param key
     * @param ivs
     * @return
     * @throws Exception
     */
    public static String decrypt(String sSrc, String key, String ivs) throws Exception {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 加密
     * @param sSrc
     * @param key
     * @param vector
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc, String key, String vector) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new String(Base64.getEncoder().encode(encrypted));// 此处使用BASE64做转码。
    }

    /**
     * 这里的G买卖的算法，接口签名，用于接口签名，参数使用TreeMap,参数按TreeMap的遍历顺序排序，在最后最后追加签名密钥，使用MD5进行签名
     * @param req
     * @param md5Key
     * @return
     */
    public static boolean verifyMD5ByQuery(HashMap<String,String> req, String md5Key) {
        String signature = req.get("signature");
        if (signature == null) return false;

        Map<String,String> signMap = new TreeMap<String,String>();

        req.entrySet().forEach(temp -> {
            String key = temp.getKey();
            if(!key.equals("signature") && !key.equals("order_url")){
                signMap.put(key,req.get(key));
            }
        });

        StringBuilder signString = new StringBuilder();
        Set<Map.Entry<String, String>> entries = signMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            signString.append(key).append("=").append(entry.getValue());
        }
        String finalSignStr = signString.toString().concat(md5Key);


        String md5Str = DigestUtil.md5Hex(finalSignStr);

        System.out.println(md5Str);
        System.out.println(signature);
        if(signature.equals(md5Str)){
            return true;
        }
        return false;
    }

}
