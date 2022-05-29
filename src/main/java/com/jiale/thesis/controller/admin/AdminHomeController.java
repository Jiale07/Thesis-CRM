package com.jiale.thesis.controller.admin;

import com.jiale.thesis.entity.AdminInfo;
import com.jiale.thesis.service.AdminInfoService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping("/admin/home")
public class AdminHomeController {

    @Autowired
    AdminInfoService adminInfoService;

    @RequestMapping("/postAdminInfo")
    @ResponseBody
    public Result<AdminInfo> postAdminInfo(BigInteger adminId){
        Result<AdminInfo> result = new Result<>();
        AdminInfo adminInfo =  adminInfoService.findAdminInfoByUserId(adminId);
        result.setMessage("查询成功");
        result.setResultCode(200);
        result.setData(adminInfo);
        return result;
    }
}