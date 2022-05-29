package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class StudentInformationVO {
    private BigInteger id;
    private String studentName;
    private Integer collegeId;
    private Integer majorId;
    private Integer classId;
    private Integer roleId;
    private String password;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
