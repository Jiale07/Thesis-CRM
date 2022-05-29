package com.jiale.thesis.entity.vo;

import lombok.Data;

@Data
public class AddRolePermissionVO {
    private Integer roleId;
    private Integer[] permissionArray;
}
