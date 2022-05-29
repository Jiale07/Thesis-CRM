package com.jiale.thesis.entity;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;


@Data
//@AllArgsConstructor
@TableName("student_info")
public class StudentInfo {
    private BigInteger id;
    private String studentName;
    private Integer collegeId;
    private Integer majorId;
    private Integer classId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
