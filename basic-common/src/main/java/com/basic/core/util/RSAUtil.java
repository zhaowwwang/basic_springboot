package com.basic.core.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangzw
 * @Date: 2017/9/7 10:46
 * @Description: rsa工具类
 * @Version: 1.0
 */
public final class RSAUtil {

    public static final String ENCODING = "UTF-8";
    //签名函数
    private static final String SIGNATURE_ALGORITHM_256 = "SHA256withRSA";
    //签名函数
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
    //加密类型
    public static final String KEY_ALGORITHM = "RSA";
    //RSA最大加密明文大小
    private static final int MAX_ENCRYPT_BLOCK = 117;
    //RSA最大解密密文大小
    private static final int MAX_DECRYPT_BLOCK = 128;
    //获取公钥的key
    private static final String PUBLIC_KEY = "RSAPublicKey";
    //获取私钥的key
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * @Author: wangzw
     * @Description: SHA256WithRSA签名
     * @Version: 1.0
     * @Date: 2017/9/7 10:52
     */
    public static String SHA256withRSA(String data, String privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM_256);
        signature.initSign(getPrivateKey(privateKey));
        signature.update(getContentBytes(data,ENCODING));
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * @Author: wangzw
     * @Description: 验证签名
     * @Version: 1.0
     * @Date: 2017/9/7 10:53
     */
    public static boolean verifySHA256withRSA(String data, byte[] sign, String publicKey){
        try {
            Signature signetcheck = Signature.getInstance(SIGNATURE_ALGORITHM_256);
            signetcheck.initVerify(getPublicKey(publicKey));
            signetcheck.update(getContentBytes(data,ENCODING));
            return signetcheck.verify(sign);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @Author: wangzw
     * @Description: 得到PrivateKey
     * @Version: 1.0
     * @Date: 2017/9/7 11:37
     */
    private static PrivateKey getPrivateKey(String privateKey) throws Exception{
        //base64解密
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(pkcs8KeySpec);
    }

    /**
     * @Author: wangzw
     * @Description: 得到PrivateKey
     * @Version: 1.0
     * @Date: 2017/9/7 11:37
     */
    private static PublicKey getPublicKey(String publicKey) throws Exception{
        //base64解密
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * @Author: wangzw
     * @Description: SHA1withRSA签名
     * @param text 需要签名的字符串
     * @param privateKey 私钥(BASE64编码)
     * @return 签名结果(BASE64编码)
     * @Version: 1.0
     * @Date: 2017/9/7 10:54
     */
    public static String SHA1withRSA(String text, String privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(getPrivateKey(privateKey));
        signature.update(getContentBytes(text, ENCODING));
        byte[] result = signature.sign();
        return Base64.encodeBase64String(result);
    }

    /**
     * @Author: wangzw
     * @Description: SHA1withRSA验证签名
     * @param text 需要签名的字符串
     * @param sign 客户签名结果
     * @param publicKey 公钥(BASE64编码)
     * @return 验签结果
     * @Version: 1.0
     * @Date: 2017/9/7 11:16
     */
    public static boolean verifySHA1withRSA(String text, String sign, String publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(getPublicKey(publicKey));
        signature.update(getContentBytes(text, ENCODING));
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * @Author: wangzw
     * @Description: 生成密钥对(公钥和私钥)
     * @Version: 1.0
     * @Date: 2017/9/7 10:53
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> genKeyPair = genKeyPair();

        String base64publicKey = getPublicKey(genKeyPair);
        System.out.println("公钥 \n" + base64publicKey);
        String base64privateKey = getPrivateKey(genKeyPair);
        System.out.println("私钥\n" + base64privateKey);

        String passwd = "cat123113";
        String charsetName = "utf-8";

        String encryptByPublicKey = Base64.encodeBase64String((encryptByPublicKey(passwd.getBytes(charsetName),
                base64publicKey)));
        System.out.println("加密\n" + encryptByPublicKey);

        byte[] decryptByPrivateKey = decryptByPrivateKey(Base64.decodeBase64(encryptByPublicKey), base64privateKey);
        String string = new String(decryptByPrivateKey, "utf-8");
        System.out.println("解密后\n" + string);

    }

    /**
     * @Author: wangzw
     * @Description: 私钥加密
     * @Version: 1.0
     * @Date: 2017/9/7 11:18
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /**
     * @Author: wangzw
     * @Description: 私钥解密
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @Version: 1.0
     * @Date: 2017/9/7 11:17
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;

    }

    /**
     * @Author: wangzw
     * @Description: 公钥解密
     * @param encryptedData 已加密数据
     * @param publicKey 私钥(BASE64编码)
     * @Version: 1.0
     * @Date: 2017/9/7 11:17
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;

    }

    /**
     * @Author: wangzw
     * @Description: 公钥加密
     * @Version: 1.0
     * @Date: 2017/9/7 11:54
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;

    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }

    /**
     * @Author: wangzw
     * @Description: 获取私钥并进行base64编码
     * @param keyMap 密钥对
     * @Version: 1.0
     * @Date: 2017/9/7 11:18
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * @Author: wangzw
     * @Description: 获取公钥并进行base64编码
     * @param keyMap 密钥对
     * @Version: 1.0
     * @Date: 2017/9/7 11:19
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }
}
