package com.jiale.thesis.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public final class Decrypt {

    public DecodedJWT deToken(final String token){
        DecodedJWT jwt = null;
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("jiale"))
                    .withIssuer("auth0")
                    .build();
            jwt = verifier.verify(token);
        }catch (JWTVerificationException | IllegalArgumentException exception){
            exception.printStackTrace();
        }
        return jwt;
    }
}
