package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.entity.vo.InstructorInfoVO;
import com.jiale.thesis.entity.vo.TeacherInformationVO;

import java.math.BigInteger;

public interface TeacherService {

    TeacherInfo findTeacherInfoById(BigInteger id);


    Page<InstructorInfoVO> findAllInstructor(Page<InstructorInfoVO> page,Integer roleId, Integer collegeId,BigInteger topicCategoryId);
}
