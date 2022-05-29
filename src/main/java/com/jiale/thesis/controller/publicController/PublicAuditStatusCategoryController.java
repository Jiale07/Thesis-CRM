package com.jiale.thesis.controller.publicController;

import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.service.AuditStatusCategoryService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/public")
@Controller
public class PublicAuditStatusCategoryController {

    @Autowired
    AuditStatusCategoryService auditStatusCategoryService;

    @RequestMapping("/postAuditStatusCategoryList")
    @ResponseBody
    public Result<List<AuditStatusCategory>> postAuditStatusCategoryList(){
        Result<List<AuditStatusCategory>> result = new Result<>();
        List<AuditStatusCategory> list = auditStatusCategoryService.findAuditStatusCategoryList();
        if (list.size()>0){
            result.setResultCode(200);
            result.setToken("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setToken("没有找到更多信息");
        }
        return result;
    }
}
