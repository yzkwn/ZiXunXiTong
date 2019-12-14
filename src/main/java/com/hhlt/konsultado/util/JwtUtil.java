package com.hhlt.konsultado.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    /**
     * 设置过期时间 30分钟
     */
    private static final long EXPIRE_TIME = 1000 * 60 * 1000;

    /**
     * 设置token私钥
     */
    private static final String TOKEN_SECRET = "bf86ee4f42584fa9aedf8daae35a1172";


    /**
     * 生成token
     */
    public static String sign(String username,Integer userId){
        try {
            // 过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String,Object> map = new HashMap<>(2);
            map.put("typ","JWT");
            map.put("alg","HS256");
            return JWT.create()
                    .withHeader(map)
                    .withClaim("loginName",username)
                    .withClaim("userId",userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    /**
     * 验证token
     */
    public static boolean verify(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
