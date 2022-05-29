package com.jiale.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GDThesisAuditRecordServiceTest {
    @Autowired
    GDThesisAuditRecordService gdThesisAuditRecordService;

    @Test
    void findGDThesisAuditRecordByGDThesisID() {
        System.out.println(gdThesisAuditRecordService.findGDThesisAuditRecordByGDThesisID(Long.parseLong("1519973704710406144")));
    }
}