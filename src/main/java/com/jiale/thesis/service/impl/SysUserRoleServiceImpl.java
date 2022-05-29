package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.vo.UserRoleVO;
import com.jiale.thesis.mapper.SysUserRoleMapper;
import com.jiale.thesis.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service

public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUserRole findSysUserRoleByUserId(BigInteger userId) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.orderByAsc("role_id");
        queryWrapper.last("limit 1");
        return sysUserRoleMapper.selectOne(queryWrapper);
    }

    @Override
    public int addSysUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public int deletedSysUserRoleLogic(BigInteger userId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setIsDeleted(1);
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
        queryWrapper.eq("user_id",userId);
        return sysUserRoleMapper.update(sysUserRole,queryWrapper);
    }

    @Override
    public int deletedSysUserRoleLogic(BigInteger userId, Integer roleId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setIsDeleted(1);
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("role_id",roleId);
        queryWrapper.eq("is_deleted",0);
        return sysUserRoleMapper.update(sysUserRole,queryWrapper);
    }

    @Override
    public int updateSysUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.updateById(sysUserRole);
    }

    @Override
    public List<UserRoleVO> findUserRoleByUserId(BigInteger userId) {
        return sysUserRoleMapper.findUserRoleByUserId(userId);
    }

    @Override
    public SysUserRole VerifyUserHasRoleId(BigInteger userId, Integer roleId) {
        return sysUserRoleMapper.VerifyUserHasRoleId(userId,roleId);
    }

}
