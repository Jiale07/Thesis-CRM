package com.jiale.thesis.mapper;

import com.jiale.thesis.entity.GD.GDTeam;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GDTeamMapperTest {
    @Resource
    GDTeamMapper gdTeamMapper;

    @Test
    void findGDTeamByTeacherId() {
        System.out.println(gdTeamMapper.findGDTeamByTeacherId(BigInteger.valueOf(110101002)));
    }
}