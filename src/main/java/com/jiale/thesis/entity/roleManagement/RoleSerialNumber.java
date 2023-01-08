package com.jiale.thesis.entity.roleManagement;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.jiale.thesis.entity.BaseEntityClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_role_serial_number")
@EqualsAndHashCode(callSuper = true)
public class RoleSerialNumber extends BaseEntityClass {
    private String serialNO; // 角色序列号
}
