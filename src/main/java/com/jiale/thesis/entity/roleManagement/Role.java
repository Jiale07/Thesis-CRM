package com.jiale.thesis.entity.roleManagement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Role {
    public String key;
    public String fullName;
}
