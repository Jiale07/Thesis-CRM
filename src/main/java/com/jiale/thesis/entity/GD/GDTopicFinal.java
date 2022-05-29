package com.jiale.thesis.entity.GD;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName("graduation_design_topic_final")
public class GDTopicFinal {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.NONE)
    private Long id;
    private String thesisTopicName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicCategoryId;
    private BigInteger studentId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
