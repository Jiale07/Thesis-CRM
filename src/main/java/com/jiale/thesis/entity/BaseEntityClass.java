package com.jiale.thesis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
@Data
public class BaseEntityClass {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.NONE)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    public Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Date updateTime;
    public int isDeleted; // 是否删除
}
