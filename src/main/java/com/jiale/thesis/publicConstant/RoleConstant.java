package com.jiale.thesis.publicConstant;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

//@Configuration(value="RoleMap", proxyBeanMethods = true)
public class RoleConstant {
    // 接口-权限控制：

    // 权限名称-值
    public final static HashMap<String, Number> PERMISSION_CONSTANT_TYPE = new HashMap<String, Number>() {
        {
            put("ADD_ROLE", 1);
            put("DELETE_ROLE", 2);
            put("UPDATE_ROLE", 3);
            put("SELECT_ROLE", 4);
            put("ADD_PERMISSION", 5);
            put("DELETE_PERMISSION", 6);
            put("UPDATE_PERMISSION", 7);
            put("SELECT_PERMISSION", 8);
            put("ADD_ROLE_PERMISSION", 9);
            put("DELETE_ROLE_PERMISSION", 10);
            put("UPDATE_ROLE_PERMISSION", 11);
            put("SELECT_ROLE_PERMISSION", 12);
            put("ADD_USER_ROLE", 13);
            put("DELETE_USER_ROLE", 14);
            put("UPDATE_USER_ROLE", 15);
            put("SELECT_USER_ROLE", 16);
        }
    };
    // 值-操作描述
    public final static HashMap<Number, String> PERMISSION_CONSTANT_DESC = new HashMap<Number, String>() {
        {
            put(PERMISSION_CONSTANT_TYPE.get("ADD_ROLE"), "新建角色");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_ROLE"), "删除角色");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_ROLE"), "更新角色信息");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_ROLE"), "角色查询");
            put(PERMISSION_CONSTANT_TYPE.get("ADD_PERMISSION"), "新建权限");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_PERMISSION"), "删除权限");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_PERMISSION"), "更新权限信息");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_PERMISSION"), "权限查询");
            put(PERMISSION_CONSTANT_TYPE.get("ADD_ROLE_PERMISSION"), "角色关联权限");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_ROLE_PERMISSION"), "角色取消关联权限");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_ROLE_PERMISSION"), "更新角色权限信息");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_ROLE_PERMISSION"), "橘色权限信息查询");
            put(PERMISSION_CONSTANT_TYPE.get("ADD_USER_ROLE"), "用户添加角色");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_USER_ROLE"), "删除用户角色");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_USER_ROLE"), "更新用户角色");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_USER_ROLE"), "查询用户角色");
        }
    };
    // 值-权限名称
    public final static HashMap<Number, String> PERMISSION_CONSTANT_NAME = new HashMap<Number, String>() {
        {
            put(PERMISSION_CONSTANT_TYPE.get("ADD_ROLE"), "ADD_ROLE");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_ROLE"), "DELETE_ROLE");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_ROLE"), "UPDATE_ROLE");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_ROLE"), "SELECT_ROLE");
            put(PERMISSION_CONSTANT_TYPE.get("ADD_PERMISSION"), "ADD_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_PERMISSION"), "DELETE_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_PERMISSION"), "UPDATE_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_PERMISSION"), "SELECT_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("ADD_ROLE_PERMISSION"), "ADD_ROLE_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_ROLE_PERMISSION"), "DELETE_ROLE_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_ROLE_PERMISSION"), "UPDATE_ROLE_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_ROLE_PERMISSION"), "SELECT_ROLE_PERMISSION");
            put(PERMISSION_CONSTANT_TYPE.get("ADD_USER_ROLE"), "ADD_USER_ROLE");
            put(PERMISSION_CONSTANT_TYPE.get("DELETE_USER_ROLE"), "DELETE_USER_ROLE");
            put(PERMISSION_CONSTANT_TYPE.get("UPDATE_USER_ROLE"), "UPDATE_USER_ROLE");
            put(PERMISSION_CONSTANT_TYPE.get("SELECT_USER_ROLE"), "SELECT_USER_ROLE");
        }
    };
    // 权限名称-接口
    public final static HashMap<String, Number> PERMISSION_CONSTANT_API = new HashMap<String, Number>() {
        {
            put("/addRole", PERMISSION_CONSTANT_TYPE.get("ADD_ROLE"));
            put("/deleteRole", PERMISSION_CONSTANT_TYPE.get("DELETE_ROLE"));
        }
    };
}
