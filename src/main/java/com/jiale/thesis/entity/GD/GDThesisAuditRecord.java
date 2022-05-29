package com.jiale.thesis.entity.GD;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName("graduation_design_thesis_audit_record")
public class GDThesisAuditRecord {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.NONE)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long gdthesisId;
    private BigInteger teacherId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long auditStatusId;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
