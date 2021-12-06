package com.decucin.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/23 16:22
 * @description：用于进行前端AES加密的密码与后端经过BCript加密的密码的比对工具类
 * @modified By：
 * @version: 1.0$
 */
public class PasswordUtils {

    /**
    *  @param formPass
    *  @param DBPass
    *  @return boolean
    *  @author decucin
    *  @date 2021/10/25 12:42
    **/
    public static boolean formCompareDB(String formPass, String DBPass){
        /**
         *  TODO 判断表单密码（AES加密）与数据库中密码（BCript加密）是否相同
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        String decrypt = null;
        try {
            // 前端密码解密
            decrypt = AESEncryptUtil.decrypt(formPass);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return new BCryptPasswordEncoder().matches(decrypt, DBPass);

    }

    /**
    *  @param formPass
    *  @return java.lang.String
    *  @author decucin
    *  @date 2021/10/25 12:43
    **/
    public static String formToDB(String formPass){
        /**
         *  TODO 将表单密码（AES加密）转化为数据库密码（BCript加密）
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        String decrypt = null;
        try {
            // 前端密码解密
            decrypt = AESEncryptUtil.decrypt(formPass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new BCryptPasswordEncoder().encode(decrypt);
    }
}
