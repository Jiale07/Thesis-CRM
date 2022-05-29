package com.jiale.thesis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnnouncementVisibleMapperTest {

    @Resource
    AnnouncementVisibleMapper avMapper;

    @Test
    void findAVAndRoleInfo() {
        System.out.println(avMapper.findAVAndRoleInfo(new Long("1512032322168696833")).toString());
    }
}