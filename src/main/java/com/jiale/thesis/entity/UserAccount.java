package com.jiale.thesis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class UserAccount {
    private BigInteger id;
    private String password;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Integer isDeleted;
}
