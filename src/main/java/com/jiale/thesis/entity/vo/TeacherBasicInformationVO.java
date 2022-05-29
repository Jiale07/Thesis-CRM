package com.jiale.thesis.entity.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class TeacherBasicInformationVO {
    private BigInteger teacherId;
    private String teacherName;
    private String collegeName;
}
