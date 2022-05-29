package com.jiale.thesis.entity.ThesisProposal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class ThesisProposalAuditInputSetting {
    @TableId(value = "id",type = IdType.NONE)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Integer serialNumber;
    private String titleName;
    private Integer roleId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
