package com.jiale.thesis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

@Data
//@AllArgsConstructor
@TableName("teacher_resume")
public class TeacherResume {
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.NONE)
    private Long id;
    private BigInteger teacherId;
    private String phone;
    private String email;
    private String birthdate;
    private String politicsStatus;
    private String education;
    private String birthplace;
    private String graduateSchool;
    private String graduateMajor;
    private String researchDirection;
    private String workExperience;
    private String educationBackground;
    private String other;
    private String imageUrl;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
