package com.jiale.thesis.entity.GD.vo;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ThesisAuditResultVO {
    private BigInteger teacherId;
    private String teacherName;
    private String auditStatusName;
    private String content;
}
