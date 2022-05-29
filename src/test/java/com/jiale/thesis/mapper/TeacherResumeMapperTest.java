package com.jiale.thesis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherResumeMapperTest {

    @Resource
    TeacherResumeMapper teacherResumeMapper;

    @Test
    void findTeacherResumeVO() {
        BigInteger teacherId = new BigInteger("110101001");
        System.out.println( teacherResumeMapper.findTeacherResumeVO(teacherId));
    }
}