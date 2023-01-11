package com.jiale.thesis.service;

import com.alibaba.fastjson.JSONObject;
import com.jiale.thesis.entity.roleManagement.DefaultRole;
import com.jiale.thesis.entity.roleManagement.RoleSerialNumber;
import com.jiale.thesis.publicConstant.DefaultRoleConstant;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest
class StudentServiceTest {

    @Accessors
    StudentService studentService;

    @Test
    void findStudentCandidateByTeacherId() {
        Iterator<Map.Entry<String, Number>> defaultRoleIterator =  DefaultRoleConstant.DefaultRoleMap.entrySet().iterator();
        Iterator<Map.Entry<Number, String>> defatRoleFillNameIterator = DefaultRoleConstant.DefaultRoleFullName.entrySet().iterator();
        List<DefaultRole> defaultRoleList = new ArrayList<>();
        while (defaultRoleIterator.hasNext()) {
            Map.Entry<String, Number> defaultRoleMapEntry = defaultRoleIterator.next();
            DefaultRole defaultRole = new DefaultRole();
            defaultRole.setDefaultRoleId(defaultRoleMapEntry.getValue());
            defaultRole.setKey(defaultRoleMapEntry.getKey());
            defaultRole.setFullName(DefaultRoleConstant.DefaultRoleFullName.get(defaultRoleMapEntry.getValue()));
            defaultRoleList.add(defaultRole);
            System.out.println(defaultRole);
        }
        System.out.println(defaultRoleList.toString());
        RoleSerialNumber roleSerialNumber = new RoleSerialNumber();
        System.out.println(JSONObject.toJSON(roleSerialNumber));
    }
}