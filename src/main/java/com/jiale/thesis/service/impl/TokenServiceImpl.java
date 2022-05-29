package com.jiale.thesis.service.impl;

import com.jiale.thesis.service.TokenService;
import com.jiale.thesis.util.jwt.Encrypt;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    public static final String SECRET = "JKKLJOoasdlfj";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    Encrypt encrypt = new Encrypt();

    @Override
    public String getToken(BigInteger userId){
        String token = encrypt.getToken(userId);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return token;
    }
}
