package com.jiale.thesis.controller.student.processDocumentation;

import com.jiale.thesis.entity.GD.GDThesisAuditRecord;
import com.jiale.thesis.entity.GD.GDThesisInput;
import com.jiale.thesis.entity.GD.GDThesisInputSetting;
import com.jiale.thesis.entity.GD.vo.ThesisAuditResultVO;
import com.jiale.thesis.service.GDThesisAuditRecordService;
import com.jiale.thesis.service.GDThesisInputService;
import com.jiale.thesis.service.GDThesisInputSettingService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/student/ThesisDetailView")
public class ThesisDetailViewController {

    @Autowired
    GDThesisInputSettingService gdThesisInputSettingService;
    @Autowired
    GDThesisInputService gdThesisInputService;

    @Autowired
    GDThesisAuditRecordService gdThesisAuditRecordService;

    @RequestMapping("/getThesisInputSettingList")
    @ResponseBody
    public Result<List<GDThesisInputSetting>> getThesisInputSettingList(){
        Result<List<GDThesisInputSetting>> result = new Result<List<GDThesisInputSetting>>();
        List<GDThesisInputSetting> list = gdThesisInputSettingService.findGDThesisInputSettingList();
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

    @RequestMapping("/postThesisInputListByThesisId")
    @ResponseBody
    public Result<List<GDThesisInput>> postThesisInputListByThesisId(Long gdThesisId){
        Result<List<GDThesisInput>> result = new Result<>();
        List<GDThesisInput> list = gdThesisInputService.findGDThesisInputListByGDThesisId(gdThesisId);
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

    @RequestMapping("/postThesisAuditRecordByThesisId")
    @ResponseBody
    public Result<ThesisAuditResultVO> postThesisAuditRecordByThesisId(Long gdThesisId){
        Result<ThesisAuditResultVO> result = new Result<>();
        ThesisAuditResultVO thesisAuditResultVO = gdThesisAuditRecordService.findThesisAuditResultVOByGDThesisId(gdThesisId);
        if (thesisAuditResultVO!=null){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(thesisAuditResultVO);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }
}
