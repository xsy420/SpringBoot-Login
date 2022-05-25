package com.example.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TokenUtil {
    //设置过期时间
    private static final long EXPIRE_DATE = 24 * 60 * 60 * 1000; //以毫秒为单位
    //token秘钥
    @SuppressWarnings("SpellCheckingInspection")
    private static final String TOKEN_SECRET = "abcdefghijklmnopqrstuvwxyz";

    @SuppressWarnings("unused")
    private static String randomToken() {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for (int i = 0; i < rd.nextInt(30); i++) {
            sb.append((char) (rd.nextInt(26) + 97));
        }
        return sb.toString();
    }

    public static String generateToken(String username, String password) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String u = "123", p = "33";
        String t = generateToken(u, p);
        System.err.println(t);
        System.out.println(JWT.decode(t).getClaims());
    }
}
