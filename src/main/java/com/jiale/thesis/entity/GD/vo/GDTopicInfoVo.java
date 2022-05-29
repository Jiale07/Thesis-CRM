package com.jiale.thesis.entity.GD.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jiale.thesis.entity.GD.GDTopicProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GDTopicInfoVo {
    //对象序列化成JSON时，将Long转成String
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String topicName;
    private String description;
    private Date createTime;
    private Date updateTime;
    private int isDeleted;
    private Integer selectedCount;
    private Integer optionalNumber;
}
