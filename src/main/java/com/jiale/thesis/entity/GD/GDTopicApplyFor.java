package com.jiale.thesis.entity.GD;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class GDTopicApplyFor {
    private BigInteger id;
    private BigInteger topicId;
    private String applicantId;
    private BigInteger adviserId;
    private int isApprove;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
