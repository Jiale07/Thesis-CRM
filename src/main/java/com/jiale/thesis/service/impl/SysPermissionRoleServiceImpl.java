package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.SysPermissionRole;
import com.jiale.thesis.mapper.SysPermissionRoleMapper;
import com.jiale.thesis.service.SysPermissionRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysPermissionRoleServiceImpl implements SysPermissionRoleService {

    @Resource
    SysPermissionRoleMapper sysPermissionRoleMapper;

    @Override
    public int addPermissionToSysRole(Integer sysRoleId,Integer sysPermissionId){
        SysPermissionRole sysPermissionRole = new com.jiale.thesis.entity.SysPermissionRole();
        sysPermissionRole.setRoleId(sysRoleId);
        sysPermissionRole.setPermissionId(sysPermissionId);
        return sysPermissionRoleMapper.insert(sysPermissionRole);
    }

    @Override
    public int addPermissionListToSysRole(Integer sysRoleId, Integer[] permissionIdArray) {
        SysPermissionRole permissionRole = new SysPermissionRole();
        permissionRole.setRoleId(sysRoleId);
        System.out.println("[PRlength]"+permissionIdArray.length);
        int PRlength = permissionIdArray.length;

        for (int i = 0;i<PRlength;i++){
            permissionRole.setPermissionId(permissionIdArray[i]);
            int isAdd = sysPermissionRoleMapper.insert(permissionRole);

            //出错回退删除
            if(isAdd!=1){
                for(int j = 0;j<i;j++){
                    QueryWrapper<SysPermissionRole> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("rold_id",sysRoleId);
                    queryWrapper.eq("permission_id",permissionIdArray[j]);
                    sysPermissionRoleMapper.delete(queryWrapper);
                }
                return 0;
            }

        }
        return 1;
    }

    @Override
    public int deleteRolePermissionById(Integer roleId,Integer permissionId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id",roleId);
        queryWrapper.eq("permission_id",permissionId);
        return sysPermissionRoleMapper.delete(queryWrapper);
    }

    @Override
    public int updateRolePermission(SysPermissionRole sysPermissionRole) {
        return sysPermissionRoleMapper.updateById(sysPermissionRole);
    }
}
