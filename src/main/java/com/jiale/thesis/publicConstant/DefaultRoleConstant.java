package com.jiale.thesis.publicConstant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Configuration(value = "DefaultRoleMap", proxyBeanMethods = true)
@Component
//@ConfigurationProperties(prefix = "constant")
//@Data
public class DefaultRoleConstant {

    /*
        超级管理员: 全局权限、且可以管理其他管理员
        全局管理员: 全局权限。
        模块管理员：部分模块的所有限制
            1.公告管理员
            2.
     */
    public final static HashMap<String, Number> DefaultRoleMap = new HashMap<String, Number>() {
        {
            put("Super", 1);
            put("Global", 2);
            put("Announcement", 3);
        }
    };
    public final static HashMap<Number, String> DefaultRoleFullName = new HashMap<Number, String>() {
        {
            put(DefaultRoleMap.get("Super"), "超级管理员");
            put(DefaultRoleMap.get("Global"), "全局管理员");
            put(DefaultRoleMap.get("Announcement"), "公告管理员");
        }
    };
}
