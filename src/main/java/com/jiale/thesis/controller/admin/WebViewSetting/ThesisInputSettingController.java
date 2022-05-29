package com.jiale.thesis.controller.admin.WebViewSetting;

import com.jiale.thesis.entity.GD.GDThesisInputSetting;
import com.jiale.thesis.service.GDThesisInputSettingService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/webViewSetting/thesis")
public class ThesisInputSettingController {

    @Autowired
    GDThesisInputSettingService service;

    @RequestMapping("/getThesisInputSettingList")
    @ResponseBody
    public Result<List<GDThesisInputSetting>> getThesisInputSettingList(){
        Result<List<GDThesisInputSetting>> result = new Result<>();

        List<GDThesisInputSetting> list =  service.findGDThesisInputSettingList();
        if (list.size()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(list);
        }else{
            result.setResultCode(200);
            result.setMessage("没有找到更多信息");
        }

        return result;
    }

    @RequestMapping("/addThesisInputSetting")
    @ResponseBody
    public Result addThesisInputSetting(GDThesisInputSetting GDTISObject){
        Result result = new Result();

        int isAdd = service.AddGDThesisInputSetting(GDTISObject);
        if (isAdd==1){
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("添加失败");
        }

        return result;
    }

    @RequestMapping("/updateThesisInputSetting")
    @ResponseBody
    public Result updateThesisInputSetting(GDThesisInputSetting GDTISObject){
        Result result = new Result();

        int isUpdate = service.updateGDThesisInputSetting(GDTISObject);
        if (isUpdate==1){
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }

        return result;
    }

    @RequestMapping("/deletedThesisInputSetting")
    @ResponseBody
    public Result deletedThesisInputSetting(Long GDTISId){
        Result result = new Result();

        int isDeleted = service.deletedGDThesisInputSettingById(GDTISId);
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
