package erha.fun.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ackerven
 * @version 1.0
 * Copyright (c) 2022 Ackerven All rights reserved.
 * @date 3/1/22 1:22 AM
 */

@Slf4j
public class TokenUtils {
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "14uWiYWehIxKhhVC1HUip7Ts7fJ5pFeTp5BTKhcDDe";

    /**
     * 生成 Token
     * @param userName 用户名
     * @return token
     */
    public static String sign(String userName, Integer role) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        Map<String, Object> herders = new HashMap<>(2);
        herders.put("typ", "JWT");
        herders.put("alg", "HS256");
        return JWT.create()
                .withHeader(herders)
                .withClaim("userName", userName)
                .withClaim("role", role)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 验证 Token
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }
}
