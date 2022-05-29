package com.jiale.thesis.entity.GD.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ThesisBasicInformationVO {

    private BigInteger studentId;
    private String studentName;
    private String collegeName;
    private String majorName;
    private BigInteger teacherId;
    private String teacherName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisTopicId;
    private String thesisTopicName;
    private String thesisTopicCategoryName;
}
