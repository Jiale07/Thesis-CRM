package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jiale.thesis.entity.StudentInfo;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class StudentInfoResVO {

    private BigInteger id;
    private String password;
    private Integer roleId;
    @TableField(fill = FieldFill.INSERT)
    private Date CreateTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date UpdateTime;
    private String studentName;
    private Integer collegeId;
    private String collegeName;
    private Integer majorId;
    private String majorName;
    private Integer classId;
}
