package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysPermission;
import com.jiale.thesis.mapper.SysPermissionMapper;
import com.jiale.thesis.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    SysPermissionMapper permissionMapper;

    @Override
    public int addPermission(SysPermission sysPermission) {
        return permissionMapper.insert(sysPermission);
    }

    @Override
    public int deletePermissionById(Integer permissionId) {
        return permissionMapper.deleteById(permissionId);
    }

    @Override
    public int updatePermission(SysPermission sysPermission) {
        return permissionMapper.updateById(sysPermission);
    }

    @Override
    public SysPermission findPermissionById(Integer permissionId) {
        return null;
    }


    @Override
    public List<SysPermission> findPermission() {
        return permissionMapper.selectList(null);
    }

    @Override
    public List<SysPermission> findPermissionByRoleId(Integer roleId) {
        return  permissionMapper.findPermissionByRoleId(roleId);
    }

    @Override
    public Page<SysPermission> findPermissionByPage(Page<SysPermission> page) {
        return permissionMapper.selectPage(page,null);
    }

    @Override
    public Integer getPermissionId() {
        return permissionMapper.findOnePermission().getId();
    }
}
