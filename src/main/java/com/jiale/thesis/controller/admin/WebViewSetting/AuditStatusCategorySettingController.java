package com.jiale.thesis.controller.admin.WebViewSetting;

import com.jiale.thesis.entity.AuditStatusCategory;
import com.jiale.thesis.service.AuditStatusCategoryService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/webViewSetting/auditStatusCategory")
public class AuditStatusCategorySettingController {

    @Autowired
    AuditStatusCategoryService auditStatusCategoryService;

    @RequestMapping("/getAuditStatusCategoryList")
    @ResponseBody
    public Result<List<AuditStatusCategory>> getAuditStatusCategoryList(){
        Result<List<AuditStatusCategory>> result = new Result<>();

        List<AuditStatusCategory> list = auditStatusCategoryService.findAuditStatusCategoryList();
        if (list.size()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }

        return result;
    }

    @RequestMapping("/postAddAuditStatusCategory")
    @ResponseBody
    public Result postAddAuditStatusCategory(AuditStatusCategory auditStatusCategory){
        Result result = new Result();

        int isAdd = auditStatusCategoryService.addAuditStatusCategory(auditStatusCategory);
        if (isAdd==1){
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("添加失败");
        }

        return result;
    }

    @RequestMapping("/postUpdateAuditStatusCategory")
    @ResponseBody
    public Result postUpdateAuditStatusCategory(AuditStatusCategory auditStatusCategory){
        Result result = new Result();

        int isUpdate = auditStatusCategoryService.updateAuditStatusCategory(auditStatusCategory);
        if (isUpdate==1){
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }

        return result;
    }


    @RequestMapping("/postDeletedAuditStatusCategory")
    @ResponseBody
    public Result postDeletedAuditStatusCategory(Long auditStatusCategoryId){
        Result result = new Result();
        int isDeleted = auditStatusCategoryService.deletedAuditStatusCategory(auditStatusCategoryId);
        if (isDeleted==1){
            result.setResultCode(200);
            result.setMessage("删除成功");
        }else{
            result.setResultCode(500);
            result.setMessage("删除失败");
        }
        return result;
    }
}
