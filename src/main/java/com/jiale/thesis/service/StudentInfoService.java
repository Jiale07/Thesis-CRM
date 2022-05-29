package com.jiale.thesis.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.entity.vo.StudentBasicInformationVO;
import com.jiale.thesis.entity.vo.StudentInfoResVO;
import com.jiale.thesis.entity.vo.StudentInformationVO;

import java.math.BigInteger;

public interface StudentInfoService {

    StudentInfo findStudentInfoById(BigInteger studentId);

    StudentInformationVO findStudentInformationVOById(BigInteger studentId);

    int addStudentInfo(StudentInfo studentInfo);

    int deletedStudentInfoLogic(BigInteger studentId);

    int updateStudentInfo(StudentInfo studentInfo);

    StudentInfo findOneStudentInfoById(BigInteger studentId);

    //    admin management
    Page<StudentInfoResVO> findStudentInfoByPage(Page<StudentInfoResVO> page, Integer collegeId, Integer majorId, Integer classId);


    BigInteger findOneStudentId(Integer start,Integer end);
    StudentBasicInformationVO findStudentBasicInformationVOById(BigInteger studentId);

}
