package com.jiale.thesis.entity.vo;

import com.jiale.thesis.entity.TeacherResume;
import lombok.Data;

import java.math.BigInteger;

@Data
public class TeacherResumeVO {
    private BigInteger teacherId;
    private String teacherName;
    private String collegeName;
    private TeacherResume teacherResume;

}
