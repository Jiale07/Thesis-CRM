package com.jiale.thesis.service;

import com.jiale.thesis.entity.SysPermissionRole;

public interface SysPermissionRoleService {

    int addPermissionToSysRole(Integer sysRoleId,Integer permissionId);

    int addPermissionListToSysRole(Integer sysRoleId,Integer[] permissionIdArray);

    int deleteRolePermissionById(Integer roleId,Integer permissionId);

    int updateRolePermission(SysPermissionRole sysPermissionRole);


}
