package com.jiale.thesis.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
/*
    PT privileged time
 */
@Data
public class SysPermissionPTVO {
    private Integer id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Date privilegedTime;
    private int isDeleted;
}
