package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.DepartmentHead;

import java.math.BigInteger;

public interface DepartmentHeadService {
    Page<DepartmentHead> findDepartmentHeadPage(Integer currentPage,Integer pageSize);

    DepartmentHead findDepartmentHeadByTeacherId(BigInteger teacherId);

    int addDepartmentHead(DepartmentHead departmentHead);

    int updateDepartmentHead(DepartmentHead departmentHead);

    int deletedDepartmentHead(Long departmentHeadId);
}
