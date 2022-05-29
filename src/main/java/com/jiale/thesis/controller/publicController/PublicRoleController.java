package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.SysRole;
import com.jiale.thesis.service.SysRoleService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
}
