package com.jiale.thesis.service;

import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.vo.UserRoleVO;

import java.math.BigInteger;
import java.util.List;

public interface SysUserRoleService {

    SysUserRole findSysUserRoleByUserId(BigInteger userId);

    int addSysUserRole(SysUserRole sysUserRole);

    int deletedSysUserRoleLogic(BigInteger userId);

    int deletedSysUserRoleLogic(BigInteger userId,Integer roleId);

    int updateSysUserRole(SysUserRole sysUserRole);

    List<UserRoleVO> findUserRoleByUserId(BigInteger userId);

    SysUserRole VerifyUserHasRoleId(BigInteger userId,Integer roleId);

}
