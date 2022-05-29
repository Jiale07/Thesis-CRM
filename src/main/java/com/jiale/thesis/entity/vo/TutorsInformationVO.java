package com.jiale.thesis.entity.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class TutorsInformationVO {
    private BigInteger teacherId;
    private String teacherName;
    private Integer collegeId;
    private String collegeName;
    private List<UserRoleVO> roleIdList;
}
