package com.jiale.thesis.entity.event;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@TableName("event_roles")
public class EventRoles {
    private BigInteger id;
    private BigInteger eventId;
    private Integer roleId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private int isDeleted;
}
