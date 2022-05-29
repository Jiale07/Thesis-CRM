package com.jiale.thesis.entity.vo;


import lombok.Data;

import java.math.BigInteger;

@Data
public class StudentBasicInformationVO {
    private BigInteger studentId;
    private String studentName;
    private String collegeName;
    private String majorName;
    private Integer classId;
}
