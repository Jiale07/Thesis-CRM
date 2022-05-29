package com.jiale.thesis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.DepartmentHead;
import com.jiale.thesis.mapper.DepartmentHeadMapper;
import com.jiale.thesis.service.DepartmentHeadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class DepartmentHeadServiceImpl implements DepartmentHeadService {

    @Resource
    DepartmentHeadMapper departmentHeadMapper;

    @Override
    public Page<DepartmentHead> findDepartmentHeadPage(Integer currentPage, Integer pageSize) {
        Page<DepartmentHead> page = new Page<>(currentPage,pageSize);
        QueryWrapper<DepartmentHead> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        return departmentHeadMapper.selectPage(page,queryWrapper);
    }

    @Override
    public DepartmentHead findDepartmentHeadByTeacherId(BigInteger teacherId) {
        return departmentHeadMapper.findDepartmentHeadByRoleId(teacherId);
    }

    @Override
    public int addDepartmentHead(DepartmentHead departmentHead) {
        departmentHead.setIsDeleted(0);
        return departmentHeadMapper.insert(departmentHead);
    }

    @Override
    public int updateDepartmentHead(DepartmentHead departmentHead) {
        return departmentHeadMapper.updateById(departmentHead);
    }

    @Override
    public int deletedDepartmentHead(Long departmentHeadId) {
        return departmentHeadMapper.deleteById(departmentHeadId);
    }
}
