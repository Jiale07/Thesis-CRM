package com.jiale.thesis.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Token {
    private BigInteger id;
    private BigInteger userId;
    private String buildTime;
    private String token;
}
