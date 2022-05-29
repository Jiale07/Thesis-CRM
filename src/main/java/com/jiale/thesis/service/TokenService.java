package com.jiale.thesis.service;


import java.math.BigInteger;

public interface TokenService {
    String getToken(BigInteger userId);
}
