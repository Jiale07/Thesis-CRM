package com.jiale.thesis.controller.admin.roleManagement;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysPermission;
import com.jiale.thesis.service.SysPermissionService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/roleAbout")
public class PermissionController {

    @Autowired
    SysPermissionService permissionService;

    @RequestMapping("/getPermission")
    @ResponseBody
    public Result<List<SysPermission>> getPermission(){
        Result<List<SysPermission>> result = new Result<>();
        List<SysPermission> permissionList = permissionService.findPermission();
        if (null==permissionList){
            result.setResultCode(500);
            result.setMessage("没有找到更多数据");
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(permissionList);
        return result;
    }

    @RequestMapping("/getPermissionByPage")
    @ResponseBody
    public Result<Page<SysPermission>> getPermissionByPage(Integer currentPage, Integer pageSize){
        Result<Page<SysPermission>> result = new Result<>();

        Page<SysPermission> pageMode = new Page<>(currentPage,pageSize);
        Page<SysPermission> permissionPage = permissionService.findPermissionByPage(pageMode);
        if (permissionPage.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有找到更多数据");
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(permissionPage);

        return result;
    }

    @RequestMapping("/addPermission")
    @ResponseBody
    public Result addPermission(SysPermission permission){
        Result result = new Result();
        System.out.println(permission.toString());
        if (null==permission.getPermissionName() || null==permission.getDescription()){
            result.setResultCode(500);
            result.setMessage("服务器接受信息错误，请重新尝试");
        }else{
            permission.setId(permissionService.getPermissionId()+1);
            int isAdd = permissionService.addPermission(permission);
            if (isAdd==1){
                result.setResultCode(200);
                result.setMessage("添加成功");
            }else{
                result.setResultCode(500);
                result.setMessage("添加失败");
            }
        }
        return result;
    }

    @RequestMapping("/deletedPermission")
    @ResponseBody
    public Result deletedPermission(Integer permissionId){
        Result result = new Result();
        if (null==permissionId){
            result.setResultCode(500);
            result.setMessage("服务器接受信息错误，请重新尝试");
        }else{
            int isAdd =  permissionService.deletePermissionById(permissionId);
            if (isAdd==1){
                result.setResultCode(200);
                result.setMessage("删除成功");
            }else{
                result.setResultCode(500);
                result.setMessage("删除失败");
            }
        }
        return result;
    }

    @RequestMapping("/updatePermission")
    @ResponseBody
    public Result updatePermission(SysPermission permission){
        Result result = new Result();
        if (null==permission){
            result.setResultCode(500);
            result.setMessage("服务器接受信息错误，请重新尝试");
        }else{
            int isAdd = permissionService.updatePermission(permission);
            if (isAdd==1){
                result.setResultCode(200);
                result.setMessage("添加成功");
            }else{
                result.setResultCode(500);
                result.setMessage("添加失败");
            }
        }
        return result;
    }
}
