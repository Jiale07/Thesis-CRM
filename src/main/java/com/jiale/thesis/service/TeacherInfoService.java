package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.TeacherInfo;
import com.jiale.thesis.entity.vo.*;

import java.math.BigInteger;
import java.util.List;

public interface TeacherInfoService {

    TeacherInformationVO findTeacherInformationById(BigInteger teacherId);

    TeacherBasicInformationVO findTeacherBasicInformationVOById(BigInteger teacherId);

    int addTeacherInfo(TeacherInfo teacherInfo);

    TeacherInfo findOneTeacherInfoById(BigInteger teacherId);

    int updateTeacherInfoByTIObject(TeacherInfo teacherInfo);

    int deletedTeacherInfoLogicById(BigInteger teacherId);

    BigInteger findOneTeacherId(Integer start,Integer end);

    Page<TeacherInfoResVO> findTeacherByPage(Page<TeacherInfoResVO> page, Integer collegeId);

    Page<TutorsInformationVO> findNotTutorPage(Page<TutorsInformationVO> page,Integer collegeId);

    Page<TutorsInformationVO> findTutorsList(Integer currentPage,Integer pageSize,Integer roleId,Integer collegeId);

    int batchAddSysUserRole(BigInteger[] teacherIdArray,Integer roleIdt);
}
