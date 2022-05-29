package com.jiale.thesis.entity.ThesisProposal;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
//@AllArgsConstructor
@TableName("thesis_proposal")
public class ThesisProposal {
    @TableId(value = "id",type = IdType.NONE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String thesisName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long thesisCategoryId;
    private BigInteger studentId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accessoryFileId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
