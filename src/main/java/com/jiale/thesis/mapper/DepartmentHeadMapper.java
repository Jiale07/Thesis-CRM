package com.jiale.thesis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiale.thesis.entity.DepartmentHead;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

@Mapper
public interface DepartmentHeadMapper extends BaseMapper<DepartmentHead> {

    DepartmentHead findDepartmentHeadByRoleId(BigInteger teacherId);
}
