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
@RequestMapping("/admin/webViewSetting/GraduationDesignThesis")
public class GraduationDesignThesisSettingController {

    @Autowired
    GDThesisInputSettingService gdThesisInputSettingService;

    /*
    GD:GraduationDesign
     */
    @RequestMapping("/getGDThesisSettingList")
    @ResponseBody
    public Result<List<GDThesisInputSetting>> getGraduationDesignThesisSettingList(){
        Result<List<GDThesisInputSetting>> result = new Result<>();
        List<GDThesisInputSetting> gdThesisInputSettingsList = gdThesisInputSettingService.findGDThesisInputSettingList();
        if (gdThesisInputSettingsList.size()>0){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(gdThesisInputSettingsList);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }

    @RequestMapping("/postAddGDThesisSetting")
    @ResponseBody
    public Result postAddGDThesisSetting(GDThesisInputSetting gdThesisInputSetting){
        Result result = new Result();
        int isAdd = gdThesisInputSettingService.AddGDThesisInputSetting(gdThesisInputSetting);
        if (isAdd==1){
            result.setResultCode(200);
            result.setMessage("添加成功");
        }else{
            result.setResultCode(500);
            result.setMessage("添加失败");
        }
        return result;
    }

    @RequestMapping("/postUpdateGDThesisSetting")
    @ResponseBody
    public Result postUpdateGDThesisSetting(GDThesisInputSetting gdThesisInputSetting){
        Result result = new Result();
        int isUpdate = gdThesisInputSettingService.updateGDThesisInputSetting(gdThesisInputSetting);
        if (isUpdate==1){
            result.setResultCode(200);
            result.setMessage("更新成功");
        }else{
            result.setResultCode(500);
            result.setMessage("更新失败");
        }
        return result;
    }

    @RequestMapping("/postDeletedGDThesisSettingById")
    @ResponseBody
    public Result postDeletedGDThesisSettingById(Long gdThesisInputSettingId){
        Result result = new Result();
        int isDeleted = gdThesisInputSettingService.deletedGDThesisInputSettingById(gdThesisInputSettingId);
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
