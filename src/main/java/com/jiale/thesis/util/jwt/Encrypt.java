package com.jiale.thesis.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import java.math.BigInteger;
import java.util.Date;

public final class Encrypt {
    public String getToken(BigInteger userId){
        String token = null;
        try{
//            Date expiresAt = new Date(System.currentTimeMillis()+24L*60L*3600L*1000L);
            Date expiresAt = new Date(System.currentTimeMillis()+1000L*60*60*72);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userId", String.valueOf(userId))
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256("jiale"));
        }catch (JWTCreationException | IllegalArgumentException exception){
            exception.printStackTrace();
        }
        return token;
    }
}
