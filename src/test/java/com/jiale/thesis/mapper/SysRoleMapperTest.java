package com.jiale.thesis.mapper;

import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.entity.vo.SysRolePermissionVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SysRoleMapperTest {

    @Resource
    SysRoleMapper sysRoleMapper;

    @Test
    void findRolePermission() {

//        List<SysRolePermissionVO> rolePermissionVOList = sysRoleMapper.findRolePermission();
//        System.out.println(rolePermissionVOList);

    }

    @Test
    void findRolePermissionByRoleId() {
        List rolePermissionList  = sysRoleMapper.findRolePermissionByRoleId(1004);
        System.out.println(rolePermissionList.toString());
    }

    @Test
    void fuzzyFindRoleListById() {
        List<SysRole> roleList = sysRoleMapper.fuzzyFindRoleListByIdMatches("2");
        System.out.println(roleList);
    }

}