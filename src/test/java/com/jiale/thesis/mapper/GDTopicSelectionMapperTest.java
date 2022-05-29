package com.jiale.thesis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GDTopicSelectionMapperTest {

    @Resource
    GDTopicSelectionMapper gdTopicSelectionMapper;

    @Test
    void findTeacherTopicSelected() {
//        System.out.println(gdTopicSelectionMapper.findTeacherTopicSelected(new BigInteger("110101001")));
    }
}