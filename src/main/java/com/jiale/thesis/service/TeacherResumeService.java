package com.jiale.thesis.service;

import com.jiale.thesis.entity.TeacherResume;
import com.jiale.thesis.entity.vo.TeacherResumeVO;

import java.math.BigInteger;

public interface TeacherResumeService {

    TeacherResumeVO findTeacherResumeById(BigInteger teacherId);

    int createTeacherResume(TeacherResume teacherResume);

    int deletedTeacherResume(BigInteger teacherID);

    int updateTeacherResume(TeacherResume teacherResume);

}
