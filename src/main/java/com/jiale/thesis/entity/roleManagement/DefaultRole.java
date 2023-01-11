package com.jiale.thesis.entity.roleManagement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
public class DefaultRole extends Role {
    private Number defaultRoleId;
}
