package com.hs.zero.util;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Rsa加密工具类
 */
public class RsAUtils {
    //签名算法名称
    private static final String RSA_KEY_ALGORITHM = "RSA";

    //标准签名算法名称
    private static final String RSA_SIGNATURE_ALGORITHM = "SHA1withRSA";
    private static final String RSA2_SIGNATURE_ALGORITHM = "SHA256withRSA";

    //RSA密钥长度,默认密钥长度是1024,密钥长度必须是64的倍数，在512到65536位之间,不管是RSA还是RSA2长度推荐使用2048
    private static final int KEY_SIZE = 2048;

    private static final String PUBLIC_KEY_ALGORITHM = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyKh7/WLDQS1xIKOAC9VPYXoAipa+bjufjBgMzEWdlX73rFrrGFz0AakNLYMSrvap81sEXkQIbBM6nG9YVP0C+/WfRn8r06EcfVbY6YFmt2zZv+Q3GjWmtAfZgwp0u7tJ9MOyuc6KTZZXpOrjalI0CKUzwn2xzmkiqLjDXiKOmkvSRy48qrC4osYb54yarDquvQeo2a5WKXb8x95RPf+sQKqonH/LYt+2nsyIr3+DuUyA0E/RGxJvoRVeC0S4L+KkwZeCWPq2y96OY6AOSdoRisi79/PwJ90lLynvFTAf8WdJA1wrMJssBwnFKyzTyT/Ckm2JoNL8UFMRg6SYRd8FlwIDAQAB";
    private static final String PRIVATE_KEY_ALGORITHM = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDIqHv9YsNBLXEgo4AL1U9hegCKlr5uO5+MGAzMRZ2VfvesWusYXPQBqQ0tgxKu9qnzWwReRAhsEzqcb1hU/QL79Z9GfyvToRx9VtjpgWa3bNm/5DcaNaa0B9mDCnS7u0n0w7K5zopNllek6uNqUjQIpTPCfbHOaSKouMNeIo6aS9JHLjyqsLiixhvnjJqsOq69B6jZrlYpdvzH3lE9/6xAqqicf8ti37aezIivf4O5TIDQT9EbEm+hFV4LRLgv4qTBl4JY+rbL3o5joA5J2hGKyLv38/An3SUvKe8VMB/xZ0kDXCswmywHCcUrLNPJP8KSbYmg0vxQUxGDpJhF3wWXAgMBAAECggEAXaUv9HG2sUDdZn+Xny4mdVYEUamHZxehXrbMpjqpEB9oxEHMLRsYb3vvX/mnawtIBoBIOhwjGBkjReW7AjLwRRl9A2APWp16l85Dvjo7b0g4GVOPGcbHLYbqTrs+/mwqWDZKv2dyaUJMVfWfO7vwd8ZtsQuLQ0sSt04/MzwKdFww4Xzq0GF6mG5AavxKiwj739yeoxTPsdNQy4TM4hJRDK5FIFiOGC17i1P2gxbX5vSHPwy69wl1Pxn8Jo+GL0PdcQQymceWnoQV+DB+bl8ShJE1z9b9KIr6NwmAdRxOyYV4ZwahQIaZ6VvTyjbRbFt8a/dm2DGszi7dyCCF6v/aOQKBgQD5DMQxn+z4jR9BFxtnRzO7QqwQ2LZvd+WnHcCNcRetDfA9ULkf6MJx/QiUq+5w3lQG1ABWeWXedfSmDMFZ68lHjGcjY+3DkN/Hv0mNVUeASJwtk9wFwbpjkC8zSCp2zEIUV3gV0zzsK49mm/Qw2Z1A4ZE78XYypeN6x1t9lrqTbwKBgQDOQgDYwcSCDR2dC8ViwfNPyrBPfjNPF1rdsMyEreXh5zuE8M4vwzik+eOCZBpiG7I89q6NsjSX9dAznCsq4mzZRdTcaBtfWDef/cNYjwyAkDsGVRZ0f8niy5kUwp/XPXD0ZxB4qA8UDaqla1rfaoPlL2l01KJBIKKjOk0pzeh8WQKBgQCAyHI3ewxN5qOvw+QevrnobmyNdZK5B1XQ2di1CzMEAL4m5rQj5PePfBnQzytIYB8xPLVEAhiENZihuNzOw9dcUSUQLt9HDoY5QxgkpmGH24QvKVNQAsKAM5HEwd97eNn+rNBi8M2DDm9jzucxz4W6hCnWxmCphhCgb7BGvAeUrwKBgAeNozpM3kVk9ZCqva/AKojhg1Nl67sZv9oef17cf26maSeVKffVzl08SXic8dpl6UESR5Io1IQ8HEPHlE0dvZ63T/RCW75CHMdfI6g6lNqgbYjt1MSvaVVE33ZDKldXiKtqms/QV70EQxuLsug7nljmQ5Zjsw1vhIU1vV2qjLkBAoGBALMXb+PVwvJn1mcp9wxn+PLsQ/UvXfZDjoiAzS0mUtczBt69kSaGUowtOHPI3bZnILFvLIQYO5j3JerdkFb+WxGNzfa6gz3sgovk5yWjqJ1foutQFvkctG3NA4XCmVntI4yFnZ5lRmkGVV10rzIAYgkhtD3F33xQkjuYaHU4aYz5";
    public static void main(String[] args) throws Exception {

//
        String privateInfo = "dDbw0SbfCWgp4b5hNMWTYRkmNlmng03DCLY5aw2yvY0mXoYcur4se+/7G3jYZE4qom40R30NiwGuDU8yPdE79JkbBFpe35ekLWvW24YfFem72ip43Gu/s6AgWomJEtl2DltjNCcfMwHl9RAtcxaq0V9yBvVPf4F3HLUXso60jz2k5OowM5idVjUGfs7ppc+JN2qurpLDSH11qLtAw/d3EQezCiUSIJ1bE2oDw1ybX6g8eNEl17t/92koYq97IkWuZIYBG8RbLTusSEhExqQhyAkJ/I4aDcKERtLvmlQm3mGqnlABG1SiNnVguAD4htuvIG5xJMXCIFE+Ms7p2+9Gkg==";


        String res = decryptByPrivateKey(privateInfo, PRIVATE_KEY_ALGORITHM);
        System.out.println(res);
        System.out.println(res.length());

    }

    public static Map<String, String> generateKey() {
        KeyPairGenerator keygen;
        try {
            keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA初始化密钥出现错误,算法异常");
        }
        SecureRandom secrand = new SecureRandom();
        //初始化随机产生器
        secrand.setSeed("Alian".getBytes());
        //初始化密钥生成器
        keygen.initialize(KEY_SIZE, secrand);
        KeyPair keyPair = keygen.genKeyPair();
        //获取公钥并转成base64编码
        byte[] pub_key = keyPair.getPublic().getEncoded();
        String publicKeyStr = Base64.getEncoder().encodeToString(pub_key);
        //获取私钥并转成base64编码
        byte[] pri_key = keyPair.getPrivate().getEncoded();
        String privateKeyStr = Base64.getEncoder().encodeToString(pri_key);
        //创建一个Map返回结果
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKeyStr", publicKeyStr);
        keyPairMap.put("privateKeyStr", privateKeyStr);
        return keyPairMap;
    }

    public static String encryptByPublicKey(String data, String publicKeyStr) throws Exception {
        //Java原生base64解码
        byte[] pubKey = Base64.getDecoder().decode(publicKeyStr);
        //创建X509编码密钥规范
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //根据X509编码密钥规范产生公钥对象
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //用公钥初始化此Cipher对象（加密模式）
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        //对数据加密
        byte[] encrypt = cipher.doFinal(data.getBytes());
        //返回base64编码后的字符串
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public static String decryptByPrivateKey(String data, String privateKeyStr) throws Exception {
        //Java原生base64解码
        byte[] priKey = Base64.getDecoder().decode(privateKeyStr);
        //创建PKCS8编码密钥规范
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        //根据PKCS8编码密钥规范产生私钥对象
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //用私钥初始化此Cipher对象（解密模式）
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        //对数据解密
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
        //返回字符串
        return new String(decrypt);
    }


}
