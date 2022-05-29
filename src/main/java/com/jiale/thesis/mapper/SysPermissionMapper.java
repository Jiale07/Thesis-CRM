package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> findPermissionByRoleId(Integer roleId);

    SysPermission findOnePermission();
}
