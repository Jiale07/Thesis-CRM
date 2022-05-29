package com.jiale.thesis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MajorMapperTest {
    @Resource
    MajorMapper majorMapper;

    @Test
    void updateMajorId() {
        System.out.println(majorMapper.updateMajorId(321321,123123,"test2",115));
    }
}