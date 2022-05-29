package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.College;

import java.math.BigInteger;
import java.util.List;

public interface CollegeService {
    int addCollegeInfo(College college);

    int deleteCollegeById(Integer collegeId);

    int logicToDeleteDeleteCollege(Integer collegeId);

    int updateCollegeName(College college);

    int updateCollegeId(int oldId, int newId);

    List<College> findCollegeList();

    Integer findOneCollegeIdDesc();

    College findCollegeById(Integer collegeId);

    Page<College> findCollegePage(Integer currentPage,Integer pageSize);
}
