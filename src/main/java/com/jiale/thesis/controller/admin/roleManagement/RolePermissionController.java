package com.jiale.thesis.controller.admin.roleManagement;

import com.jiale.thesis.entity.SysPermission;
import com.jiale.thesis.entity.vo.AddRolePermissionVO;
import com.jiale.thesis.service.SysPermissionRoleService;
import com.jiale.thesis.service.SysPermissionService;
import com.jiale.thesis.service.TeacherService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("/roleAbout")
public class RolePermissionController {

    @Resource
    SysPermissionService permissionService;

    @Resource
    SysPermissionRoleService permissionRoleService;

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/getPermissionByRoleId")
    @ResponseBody
    public Result<List<SysPermission>> getPermissionByRoleId(Integer roleId){
        Result<List<SysPermission>> result = new Result<>();

        List<SysPermission> permission = permissionService.findPermissionByRoleId(roleId);
        if (null == permission){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(permission);
        return result;
    }

    @RequestMapping("/addRolePermission")
    @ResponseBody
    public Result addRolePermission(@RequestBody AddRolePermissionVO addRolePermissionVO){
        Result result = new Result();
        System.out.println(addRolePermissionVO.toString());
        int isAdd = permissionRoleService.addPermissionListToSysRole(addRolePermissionVO.getRoleId(),addRolePermissionVO.getPermissionArray());

        if (isAdd!=1){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
            return result;
        }
        result.setResultCode(200);
        result.setMessage("success");
        return result;
    }


    @RequestMapping("/deleteRolePermissionById")
    @ResponseBody
    public Result deleteRolePermissionById(Integer roleId,Integer permissionId){
        Result result = new Result();
        int isDeleted = permissionRoleService.deleteRolePermissionById(roleId,permissionId);
        if (isDeleted !=1){
            result.setResultCode(500);
            result.setMessage("删除错误");
        }
        result.setResultCode(200);
        result.setMessage("success");

        return result;
    }

}
