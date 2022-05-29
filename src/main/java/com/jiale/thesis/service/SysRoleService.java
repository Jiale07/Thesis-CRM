package com.jiale.thesis.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.entity.vo.SysRolePermissionVO;
import com.jiale.thesis.entity.vo.SysRolePermissionVO2;

import java.util.List;

public interface SysRoleService {

    int addSysRole(SysRole sysRole);

    int deleteSysRoleById(Integer roleId);

    int deleteSysRoleByIdLogic(Integer roleId);

    int updateSysRoleInfo(SysRole sysRole);

    SysRole findSysRoleInfoById(Integer roleId);

    Integer findOneSysRoleIdOrder(String orderingRule, Integer start, Integer end);

    List<SysRole> findSysRoleInfoList();

    Page<SysRole> findRolePage(Page<SysRole> page);

    Page<List<SysRolePermissionVO>> findSysRoleInfoByPage(Page<List<SysRolePermissionVO>> page);

    //查询SysRole有用过的权限
    List<SysRolePermissionVO2>  findRolePermissionById(Integer sysRoleId);

    List<SysRole> fuzzyFindRoleListByIdMatches(String matches);


}
