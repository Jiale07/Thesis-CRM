package com.jiale.thesis.util.jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DecryptTest {
    Decrypt decrypt = new Decrypt();
    @Test
    void deToken() {
        String tokenStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTY2NTI0NDQxNCwidXNlcklkIjoiMTAwMDEifQ.bAr1hIUGCEcfZzN7HMUGsoK3SacG1AcbUkmL6LfGdNo";

        System.out.println("token");
        DecodedJWT jwt = decrypt.deToken(tokenStr);
        System.out.println(jwt.getClaim("userId").asString());
    }
}