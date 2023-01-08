package com.jiale.thesis.service.impl.roleManagement;

import com.jiale.thesis.entity.roleManagement.RoleSerialNumber;
import com.jiale.thesis.mapper.roleManagement.RoleSerialNumberMapper;
import com.jiale.thesis.service.RoleSerialNumberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleManagementImpl implements RoleSerialNumberService {

    @Resource
    RoleSerialNumberMapper roleSerialNumberMapper;

    @Override
    public int createdRoleSerialNumber(RoleSerialNumber roleSerialNumber) {
        return roleSerialNumberMapper.insert(roleSerialNumber);
    }
}
