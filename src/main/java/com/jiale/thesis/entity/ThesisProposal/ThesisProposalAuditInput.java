package com.jiale.thesis.entity.ThesisProposal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class ThesisProposalAuditInput {
    @TableId(value = "id",type = IdType.NONE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private BigInteger teacherId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tpId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tpaiSettingId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long auditStatusId;
    private String inputComment;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;

}
