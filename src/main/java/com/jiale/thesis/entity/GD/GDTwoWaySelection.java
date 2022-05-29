package com.jiale.thesis.entity.GD;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName("graduation_design_two_way_selection")
public class GDTwoWaySelection {
    private BigInteger id;
    private BigInteger studentId;
    private BigInteger teacherId;
    private Integer studentIsSelected;
    private Integer teacherIsSelected;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
