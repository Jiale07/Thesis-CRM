package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jiale.thesis.entity.SysPermission;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysRolePermissionVO{
    private Integer id;
    private String name;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
    private List<SysPermissionPTVO> sysPermissions;


}
