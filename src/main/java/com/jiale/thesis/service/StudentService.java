package com.jiale.thesis.service;

import com.jiale.thesis.entity.StudentInfo;
import com.jiale.thesis.entity.UserAccount;

import java.math.BigInteger;

public interface StudentService {
    StudentInfo getStudentInfoById(BigInteger studentId);




    int deletedStudentById(BigInteger studentId);
//    更新账号信息
    int updateStudent(UserAccount userAccount,StudentInfo studentInfo);

}
