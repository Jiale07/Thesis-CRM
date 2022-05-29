package com.jiale.thesis.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SnowflakeUtilTest {

    @Test
    void getSnowflakeId() {
        SnowflakeUtil snowflakeUtil = new SnowflakeUtil();
        System.out.println(snowflakeUtil.getSnowflakeId());
    }
}