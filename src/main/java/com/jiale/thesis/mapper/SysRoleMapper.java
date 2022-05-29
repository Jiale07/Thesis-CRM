package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.entity.vo.SysRolePermissionVO;
import com.jiale.thesis.entity.vo.SysRolePermissionVO2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    Page<List<SysRolePermissionVO>> findRolePermission(Page<List<SysRolePermissionVO>> page);

    List<SysRolePermissionVO2> findRolePermissionByRoleId(Integer roleId);

    List<SysRole> fuzzyFindRoleListByIdMatches(String matches);

    @Select("SELECT id FROM sys_role sr WHERE sr.is_deleted = 0 ORDER BY id ${sorTord} LIMIT ${start},${end}")
    Integer getOneTeacherId(String sorTord, Integer start, Integer end);
}
