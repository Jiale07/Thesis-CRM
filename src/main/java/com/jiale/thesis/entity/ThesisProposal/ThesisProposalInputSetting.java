package com.jiale.thesis.entity.ThesisProposal;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
//@AllArgsConstructor
@TableName("thesis_proposal_input_setting")
public class ThesisProposalInputSetting {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.NONE)
    private Long id;
    private Integer serialNumber;
    private String titleName;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
