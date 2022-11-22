package com.lzu.securingmicroservicesdemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/10/16.
 */

public class TokenUtils {

    // 签名秘钥
    public static final String SECRET = "23348A9305ADFA452D08D824287BE5C8F235006831716051B4CC74BFE0C3A66B";

    // 签发人
    public static final String ISSUER = "lzu";

    // 所有人
    public static final String SUBJECT = "admin";

    /**
     * 生成token
     *
     * @param id 一般传入userName
     * @return
     */
    public static String createJwtToken(String id) {
        return createJwtToken(id, ISSUER, SUBJECT, 2);
    }

    /**
     * 生成Token
     *
     * @param id      编号
     * @param issuer  该JWT的签发者，是否使用是可选的
     * @param subject 该JWT所面向的用户，是否使用是可选的；
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, int hours) {
        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 生成签发时间
        long nowMillis = new Date(System.currentTimeMillis()).getTime();
        // 生成过期时间 ,两小时
        long duration = (long) hours * 60 * 60 * 1000;
        Date endDate = new Date(nowMillis + duration);
        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(id + "")
                .setExpiration(endDate)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    // Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jwt).getBody();
            return claims;
        } catch (Exception e) {
            System.out.println("Token校验失败,已过期");
        }
        return null;
    }

}
