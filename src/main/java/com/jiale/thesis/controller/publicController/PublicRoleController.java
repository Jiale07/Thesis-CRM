package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.entity.roleManagement.CustomRole;
import com.jiale.thesis.entity.roleManagement.DefaultRole;
import com.jiale.thesis.publicConstant.DefaultRoleConstant;
import com.jiale.thesis.service.SysRoleService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;
import java.util.*;

@RequestMapping("/public")
@Controller
public class PublicRoleController {

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping("/getRoleList")
    @ResponseBody
    public Result<List<SysRole>> getRoleList(){
        Result<List<SysRole>> result = new Result<>();
        List<SysRole> sysRoleList = sysRoleService.findSysRoleInfoList();
        if (sysRoleList.size()>0){
            result.setResultCode(200);
            result.setData(sysRoleList);
            result.setMessage("获取成功");
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }
//    改变角色权限管理，后端通过返回map值给到所有的权限对象，当角色拥有权限的时候，将他的角色权限存储到数据库中作为存储
//    如何校验：
//    1.后端校验：当用户访问需要进行角色校验的接口时首先获取该角色所有的权限，查找该用户的权限list中是否存在接口要求的对应权限，作为判断依据（可以考虑放在接口拦截上）
//    2.前端校验，在登录成功后，获取该用户的所有权限的list，通过查询该list中是否存在指定的权限作为判断
//
//    如何存储用户的角色权限：权限名称、用户id，例如：Permission = ”AddRole“, userId = 10001
//
    /*
    能够创建也就可以更新、删除 总的名称可以成为edit
        {
            Permission
            Name: "Role"
            Permission: "Edit,Read,ReadAll",
        }
    */
//    多角色管理：用户-角色-权限
//    默认角色：

//    获取所有角色权限列表：
    @RequestMapping("/getDefaultRoleList")
    @ResponseBody
    public Result<List<DefaultRole>> getDefaultRoleList() {
        Result<List<DefaultRole>> result = new Result<List<DefaultRole>>();
        Iterator<Map.Entry<String, Number>> defaultRoleIterator =  DefaultRoleConstant.DefaultRoleMap.entrySet().iterator();
        Iterator<Map.Entry<Number, String>> defatRoleFillNameIterator = DefaultRoleConstant.DefaultRoleFullName.entrySet().iterator();
        List<DefaultRole> defaultRoleList = new ArrayList<>();
        while (defaultRoleIterator.hasNext()) {
            Map.Entry<String, Number> defaultRoleMapEntry = defaultRoleIterator.next();
            DefaultRole defaultRole = new DefaultRole();
            defaultRole.setDefaultRoleId(defaultRoleMapEntry.getValue());
            defaultRole.setKey(defaultRoleMapEntry.getKey());
            defaultRole.setFullName(DefaultRoleConstant.DefaultRoleFullName.get(defaultRoleMapEntry.getValue()));
            defaultRoleList.add(defaultRole);
        }
        result.setMessage("获取成功");
        result.setResultCode(200);
        result.setData(defaultRoleList);
        return result;
    }
}
