package com.jiale.thesis.entity.ThesisProposal;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
//@AllArgsConstructor
@TableName("thesis_proposal_input")
public class ThesisProposalInput {
    @TableId(value = "id",type = IdType.NONE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisProposalId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tpInputSettingId;
    private String inputContent;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
