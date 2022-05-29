package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jiale.thesis.entity.SysPermission;
import lombok.Data;

import java.util.Date;
@Data
public class SysRolePermissionVO2 {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    private Date createTime;
    private Date updateTime;
    private int isDeleted;
    private SysPermission sysPermission;
}
