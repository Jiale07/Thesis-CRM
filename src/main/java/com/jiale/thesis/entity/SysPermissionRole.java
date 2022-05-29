package com.jiale.thesis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_permission_role")
public class SysPermissionRole {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
