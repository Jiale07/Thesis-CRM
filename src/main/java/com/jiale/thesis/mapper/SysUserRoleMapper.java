package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.vo.UserRoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<UserRoleVO> findUserRoleByUserId(BigInteger userId);

    SysUserRole VerifyUserHasRoleId(BigInteger userId,Integer roleId);
}
