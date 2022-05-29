package com.jiale.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CollegeServiceTest {
    @Autowired
    CollegeService collegeService;

    @Test
    void findOneCollegeIdDesc() {
        System.out.println(collegeService.findOneCollegeIdDesc());
    }
}