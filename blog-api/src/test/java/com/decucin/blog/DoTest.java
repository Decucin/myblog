package com.decucin.blog;

import com.decucin.blog.utils.AESEncryptUtil;
import com.decucin.blog.utils.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/22 22:04
 * @description：测试类
 * @modified By：
 * @version: 1.0$
 */
@SpringBootTest
public class DoTest {

    @Test
    public void codeTest() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encrypt = AESEncryptUtil.encrypt("Decucin123");
        System.out.println(encrypt);
//        String decrypt = AESEncryptUtil.decrypt(encrypt);
//        System.out.println(decrypt);
//        String encode1 = encoder.encode("test");
//        String encode2 = encoder.encode("test");
//        String encode3 = encoder.encode("test");
//        System.out.println(encode1);
//        System.out.println(encode2);
//        System.out.println(encode3);
//        System.out.println(encoder.matches("test", encode1));
//        System.out.println(encoder.matches("test", encode2));
//        System.out.println(encoder.matches("test", encode3));
//        System.out.println(" ");
//        String dbPass = PasswordUtils.formToDB(encrypt);
//        System.out.println(dbPass);
//        System.out.println(PasswordUtils.formCompareDB(encrypt, dbPass));
//        $2a$10$08geOg3K9zCoEE2P7e1XyupDhHSqimAJMuk7ENgewG/ytHUdvm3DG
//        $2a$10$NV6g3.XhBCuwvBUpbR/.VezwTkyb8IMS1RxWrH1kqidxtZNL7hrQi
//        $2a$10$MtVs/ZlTjU3qbL7uN/PNVezSqh6OnfXRspz4NYH7SKzK2Vr7LIWwi
    }

}
