package com.decucin.blog.utils;

import com.decucin.blog.dao.pojo.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 21:35
 * @description：放置jwt以及token相关工具包
 * @modified By：
 * @version: 1.0$
 */
public class JWTTokenUtils {
    // token头
    public static final String TOKEN_HEADER = "Authorization";
    // token前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 加密,这个特别重要
    private static final String SECRET = "decucin_blog";
    // jwt的发行人
    private static final String ISS = "decucin";

    // 过期时间是3600秒，既是1个小时
    private static final long EXPIRATION = 604800L;

    // 选择了记住我之后的过期时间为7天
//    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
    *  @param user
    *  @param isRememberMe
    *  @return java.lang.String
    *  @author decucin
    *  @date 2021/10/25 12:40
    **/
    public static String createToken(SysUser user, boolean isRememberMe) {
        /**
         *  TODO 创建token值（这里的rememberMe值其实并未使用）
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        // 根据是否记住我选择过期时间
        long expiration = EXPIRATION;
        return Jwts.builder()
                // 这里设置了签名算法
                .signWith(SignatureAlgorithm.HS512, SECRET)
                // 这里设置了签名的发行人
                .setIssuer(ISS)
                // 这里设置了加密的东西
                .setClaims(claims)
                // 这里设置了签名的时间
                .setIssuedAt(new Date())
                // 这里设置了过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
    *  @param token
    *  @return java.util.Map<java.lang.String,java.lang.Object>
    *  @author decucin
    *  @date 2021/10/25 12:41
    **/
    public static Map<String, Object> getTokenBody(String token){
        /**
         *  TODO 从token值中获取对象
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        return (Map<String, Object>) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
