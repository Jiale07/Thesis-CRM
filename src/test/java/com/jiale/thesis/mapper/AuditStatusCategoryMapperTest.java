package com.jiale.thesis.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuditStatusCategoryMapperTest {
    @Resource
    AuditStatusCategoryMapper auditStatusCategoryMapper;
    @Test
    void findAuditStatusCategory() {
//        System.out.println(auditStatusCategoryMapper.findAuditStatusCategory(Long.valueOf("1518441002773450754")));
    }
}