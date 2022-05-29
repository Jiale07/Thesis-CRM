package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jiale.thesis.entity.SysUserRole;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class AdminInformationVO {
    private BigInteger id;
    private String adminName;
    private Integer roleId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
