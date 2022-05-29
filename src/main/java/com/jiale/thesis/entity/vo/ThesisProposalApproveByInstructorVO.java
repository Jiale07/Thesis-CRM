package com.jiale.thesis.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;

@Data
public class ThesisProposalApproveByInstructorVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisProposalId;
    private String thesisName;
    private BigInteger studentId;
    private String studentName;
}
