package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class UserRoleVO {
    private BigInteger userId;
    private Integer roleId;
    private String roleName;
    @TableField(fill = FieldFill.INSERT)
    private Date surCreateTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date surUpdateTime;
}
