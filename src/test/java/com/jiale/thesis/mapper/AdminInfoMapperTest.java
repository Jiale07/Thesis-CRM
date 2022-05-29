package com.jiale.thesis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AdminInfoMapperTest {

    @Resource
    AdminInfoMapper adminMapper;

    @Resource
    StudentAccountMapper studentAccountMapper;

    @Test
    void getStudentAccountList() {

    }

    @Test
    void addStduentAccount(){
    }
}