package com.decucin.blog.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/23 10:30
 * @description：这个类用以实现AES的加密和解密功能
 * @modified By：
 * @version: 1.0$
 */
public class AESEncryptUtil {

    //可配置到Constant中，并读取配置文件注入,16位,自己定义
    private static final String KEY = "Decucin123456789";

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     * @param content 加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {
        /**
         *  TODO 将content通过encryptKey进行加密
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        /**
         *  TODO 将content通过encryptKey进行解密
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
    *  @param content
    *  @return java.lang.String
    *  @author decucin
    *  @date 2021/10/25 12:39
    **/
    public static String encrypt(String content) throws Exception {
        /**
         *  TODO 将content通过默认Key值进行加密
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        return encrypt(content, KEY);
    }

    /**
    *  @param encryptStr
    *  @return java.lang.String
    *  @author decucin
    *  @date 2021/10/25 12:40
    **/
    public static String decrypt(String encryptStr) throws Exception {
        /**
         *  TODO 将content通过默认Key值进行解密
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        return decrypt(encryptStr, KEY);
    }

}
