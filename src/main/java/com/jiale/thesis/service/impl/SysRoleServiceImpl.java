package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysPermissionRole;
import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.entity.vo.SysRolePermissionVO;
import com.jiale.thesis.entity.vo.SysRolePermissionVO2;
import com.jiale.thesis.mapper.SysPermissionMapper;
import com.jiale.thesis.mapper.SysPermissionRoleMapper;
import com.jiale.thesis.mapper.SysRoleMapper;
import com.jiale.thesis.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    SysPermissionRoleMapper sysPermissionRoleMapper;

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    SysPermissionMapper permissionMapper;

    @Override
    public int addSysRole(SysRole sysRole){
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public int deleteSysRoleById(Integer sysRoleId){
        QueryWrapper<SysPermissionRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",sysRoleId);
        sysPermissionRoleMapper.delete(queryWrapper);
        return sysRoleMapper.deleteById(sysRoleId);
    }

    @Override
    public int deleteSysRoleByIdLogic(Integer roleId) {
        SysRole role = new SysRole();
        role.setId(roleId);
        role.setIsDeleted(1);

        QueryWrapper<SysPermissionRole> queryWrapper = new QueryWrapper<SysPermissionRole>();
        queryWrapper.eq("role_id",roleId);
        SysPermissionRole sysPermissionRole = new SysPermissionRole();
        sysPermissionRole.setIsDeleted(1);

        sysPermissionRoleMapper.update(sysPermissionRole,queryWrapper);
        return sysRoleMapper.updateById(role);
    }

    @Override
    public int updateSysRoleInfo(SysRole sysRole){
        return sysRoleMapper.updateById(sysRole);
    }

    @Override
    public Page<SysRole> findRolePage(Page<SysRole> page){
        return sysRoleMapper.selectPage(page,null);
    }

    @Override
    public SysRole findSysRoleInfoById(Integer sysRoleId){
        return sysRoleMapper.selectById(sysRoleId);
    }

    @Override
    public Integer findOneSysRoleIdOrder(String sorTord, Integer start, Integer end) {
        return sysRoleMapper.getOneTeacherId(sorTord,start,end);
    }

    @Override
    public List<SysRole> findSysRoleInfoList(){
        return sysRoleMapper.selectList(null);
    }

    @Override
    public Page<List<SysRolePermissionVO>> findSysRoleInfoByPage(Page<List<SysRolePermissionVO>> page){
        return sysRoleMapper.findRolePermission(page);
    }

    @Override
    public List<SysRolePermissionVO2>  findRolePermissionById(Integer roleId) {
        return sysRoleMapper.findRolePermissionByRoleId(roleId);
    }

    @Override
    public List<SysRole> fuzzyFindRoleListByIdMatches(String matches) {
        return sysRoleMapper.fuzzyFindRoleListByIdMatches(matches);
    }

}
