package com.jiale.thesis.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposal;
import com.jiale.thesis.entity.ThesisProposal.ThesisProposalInput;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class ThesisProposalAuditVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisProposalId;
    private BigInteger studentId;
    private String studentName;
    private BigInteger teacherId;
    private String teacherName;
    private String collegeName;
    private String majorName;
    private String thesisName;
    private String thesisCategoryName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fileId;
    private List<ThesisProposalInput> thesisProposalInputsList;
}
