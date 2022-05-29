package com.jiale.thesis.entity.toolEntity;

import lombok.Data;

import java.math.BigInteger;

/*
    changesTREntity==>changesTeacherRoleEntity
 */
@Data
public class ChangesTREntity {
    private Integer roleId;
    private String[] teacherIdArray;
}
