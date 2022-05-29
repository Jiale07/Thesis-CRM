package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ThesisTableViewVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisTopicId;
    private String thesisTopicName;
    private String thesisTopicCategory;
    private BigInteger studentId;
    private String studentName;
    private boolean submitStatus;
    private Integer submitTheNumber;
    private String auditStatus;
}
