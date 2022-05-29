package com.jiale.thesis.controller.admin.roleManagement;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.entity.vo.SysRolePermissionVO;
import com.jiale.thesis.entity.vo.SysRolePermissionVO2;
import com.jiale.thesis.service.SysRoleService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/roleAbout")
public class RoleController {

    @Autowired
    SysRoleService roleService;

    @RequestMapping("/getRolePermissionPage")
    @ResponseBody
    public Result<Page<List<SysRolePermissionVO>> > getRolePermissionPage(Integer currentPage, Integer pageSize){
        Result<Page<List<SysRolePermissionVO>>> result = new Result<>();

        Page<List<SysRolePermissionVO>> page = new Page<>(currentPage,pageSize);
        Page<List<SysRolePermissionVO>>  rolePage = roleService.findSysRoleInfoByPage(page);
        if (rolePage.getTotal() <=0){
            result.setResultCode(500);
            result.setMessage("没有更多信息了");
            return result;
        }
        result.setData(rolePage);
        result.setResultCode(200);
        result.setMessage("success");

        return result;
    }

//    通过roleId查询对应的roleInfo和它的所有权限
    @RequestMapping("/findRolePermissionByRoleId")
    @ResponseBody
    public Result<List<SysRolePermissionVO2>> getRolePermissionByRoleId(Integer roleId){
        Result<List<SysRolePermissionVO2>> result = new Result<>();
        List<SysRolePermissionVO2>  sysRolePermissionVO2 = roleService.findRolePermissionById(roleId);
        if(sysRolePermissionVO2 ==null){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
            return result;
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(sysRolePermissionVO2);
        return result;
    }

    @RequestMapping("findOneRoleByRoleId")
    @ResponseBody
    public Result<SysRole> findOneRoleByRoleId(Integer roleId){
        Result<SysRole> result = new Result<>();
        SysRole sysRole = roleService.findSysRoleInfoById(roleId);
        if(sysRole ==null){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
            return result;
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(sysRole);
        return result;
    }

    @RequestMapping("/getRolePage")
    @ResponseBody
    public Result<Page<SysRole>> getRolePage(Integer currentPage, Integer pageSize){
        Result<Page<SysRole>> result = new Result<>();
        Page<SysRole> page = new Page<>(currentPage,pageSize);
        Page<SysRole> sysRole = roleService.findRolePage(page);

        if (sysRole.getTotal()<=0){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
        }
        result.setResultCode(200);
        result.setMessage("success");
        result.setData(sysRole);

        return result;
    }

    //获取所有角色信息List
    @RequestMapping("/findAllRoleList")
    @ResponseBody
    public Result<List<SysRole>> findAllRoleList(){
        Result<List<SysRole>> result = new Result<>();
        List<SysRole>  sysRoleList = roleService.findSysRoleInfoList();
        if (null == sysRoleList){
            result.setMessage("没有找到更多数据");
            result.setResultCode(500);
        }
        result.setData(sysRoleList);
        result.setMessage("success");
        result.setResultCode(200);
        return result;
    }

    @RequestMapping("/fFRoleListByMatches")
    @ResponseBody
    public Result<List<SysRole>> fFRoleListByMatches(String matches){
        Result<List<SysRole>> result = new Result<>();
        List<SysRole> roleList = roleService.fuzzyFindRoleListByIdMatches(matches);
        if (null ==roleList){
            result.setResultCode(500);
            result.setMessage("没有找到更多信息");
        }
        result.setResultCode(200);
        result.setData(roleList);
        result.setMessage("success");
        return result;
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public Result addRole(SysRole sysRole){
        Result result = new Result();
        int isAdd = roleService.addSysRole(sysRole);
        if (isAdd!=1){
            result.setResultCode(500);
            result.setMessage("添加失败，请重新确认数据再添加");
        }else{
            result.setResultCode(200);
            result.setMessage("添加成功");
        }
        return result;
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public Result deleteRole(Integer roleId){
        Result result = new Result();
        int isDeleted = roleService.deleteSysRoleById(roleId);
        if (isDeleted!=1){
            result.setResultCode(500);
            result.setMessage("删除失败");
        }else{
            result.setResultCode(200);
            result.setMessage("删除成功");
        }
        return result;
    }

    @RequestMapping("/getNewRoleId")
    @ResponseBody
    public Result<String> getNewRoleId(Integer amount){
        Result<String> result = new Result<>();

        if(null == amount){
            result.setData(JSON.toJSONString(getOneNewRoleId()));
        }else{
            result.setData(JSON.toJSONString(getNewRoleIdByAmount(amount)));
        }
        result.setResultCode(200);
        result.setMessage("获取成功");
        return result;
    }

    public Map<String, Integer> getOneNewRoleId(){
        Integer roleId = roleService.findOneSysRoleIdOrder("DESC",0,1);
        if (null == roleId){
            roleId = new Integer("1001");
        }
        Map<String,Integer> newRoleIdMap = new HashMap<>();
        newRoleIdMap.put("key1",roleId+1);
        return newRoleIdMap;
    }

    public Map<String,Integer> getNewRoleIdByAmount(Integer amount){
        Integer roleId = roleService.findOneSysRoleIdOrder("DESC",0,1);
        if (null==roleId){
            roleId = new Integer("1001");
        }
        Map<String,Integer> newRoleIdMap = new HashMap<>();
        for(int i=0;i<amount;i++){
            String key = "key"+i;
            newRoleIdMap.put(key,roleId+i);
        }
        return newRoleIdMap;
    }
}
