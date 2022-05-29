package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class SelectionResultVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;
    private String topicName;
    private String description;
    private BigInteger creatorId;
    private String teacherName;
    private String categoryName;
    private Integer isPassed;
    @TableField(fill = FieldFill.INSERT)
    private Date GDTSCreateTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date GDTSUpdateTime;
}
