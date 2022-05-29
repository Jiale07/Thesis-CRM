package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

    int addPermission(SysPermission sysPermission);

    int deletePermissionById(Integer permissionId);

    int updatePermission(SysPermission sysPermission);

    SysPermission findPermissionById(Integer permissionId);


    List<SysPermission> findPermission();

    List<SysPermission> findPermissionByRoleId(Integer roleId);

    Page<SysPermission> findPermissionByPage(Page<SysPermission> page);

    Integer getPermissionId();
}
