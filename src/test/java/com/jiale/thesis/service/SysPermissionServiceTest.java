package com.jiale.thesis.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SysPermissionServiceTest {
    @Resource
    SysPermissionService permissionService;

    @Test
    void getPermissionId() {
        System.out.println(permissionService.getPermissionId());
    }
}