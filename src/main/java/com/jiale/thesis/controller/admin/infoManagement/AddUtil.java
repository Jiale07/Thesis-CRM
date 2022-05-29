package com.jiale.thesis.controller.admin.infoManagement;

import com.jiale.thesis.entity.SysUserRole;
import com.jiale.thesis.entity.UserAccount;

import java.math.BigInteger;

public class AddUtil {

    public UserAccount addUserAccount(BigInteger teacherId,String password){
        int isDeleted = 0;
        UserAccount userAccount = new UserAccount();
        userAccount.setId(teacherId);
        userAccount.setPassword(password);
        userAccount.setIsDeleted(isDeleted);
        return userAccount;
    }

    public SysUserRole addSysUserRole(BigInteger teacherId,Integer roleId){
        int isDeleted = 0;
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(teacherId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setIsDeleted(isDeleted);
        return sysUserRole;
    }
}
