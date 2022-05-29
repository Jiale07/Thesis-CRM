package com.jiale.thesis.controller.teacher.GDTeam;

import com.jiale.thesis.entity.FileInfo;
import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.GDThesisAuditRecord;
import com.jiale.thesis.entity.GD.GDThesisInput;
import com.jiale.thesis.entity.GD.GDThesisInputSetting;
import com.jiale.thesis.service.*;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/teacher/gdTeam/thesisAudit")
public class ThesisAuditController {

    @Autowired
    GDThesisInputSettingService gdThesisInputSettingService;
    @Autowired
    GDThesisInputService gdThesisInputService;
    @Autowired
    GDThesisAuditRecordService gdThesisAuditRecordService;

    @Autowired
    GDThesisService gdThesisService;

    @Autowired
    FileInfoService fileInfoService;


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

    @RequestMapping("/postThesisAccessoryFileInfo")
    @ResponseBody
    public Result<FileInfo> postThesisAccessoryFileInfo(Long gdThesisId){
        Result<FileInfo> result = new Result<>();
        FileInfo fileInfo = fileInfoService.findFileInfoByFileId(
                gdThesisService.findGDTheisById(gdThesisId).getAccessoryFileId()
        );
        if (null!=fileInfo){
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(fileInfo);
        }else{
            result.setResultCode(204);
            result.setMessage("文件丢失");
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


    @RequestMapping("/postThesisAuditRecord")
    @ResponseBody
    public Result<GDThesisAuditRecord> postThesisAuditRecord (Long gdThesisId){
        System.out.println(gdThesisId);
        Result<GDThesisAuditRecord> result = new Result<>();
        GDThesisAuditRecord gdThesisAuditRecord = gdThesisAuditRecordService.findGDThesisAuditRecordByGDThesisID(gdThesisId);
        if (gdThesisAuditRecord==null){
            result.setResultCode(204);
            result.setMessage("没有找到该论文的审核记录");
        }else{
            result.setResultCode(200);
            result.setMessage("获取成功");
            result.setData(gdThesisAuditRecord);
        }
        return result;
    }


    @RequestMapping("/postSubmitAuditStatueOfTheThesis")
    @ResponseBody
    public Result postSubmitAuditStatueOfTheThesis(GDThesisAuditRecord gdThesisAuditRecord){
        Result result = new Result();
        gdThesisAuditRecord.setIsDeleted(0);
        GDThesisAuditRecord gdThesisAuditRecord1 = gdThesisAuditRecordService.findGDThesisAuditRecordByGDThesisID(gdThesisAuditRecord.getGdthesisId());
        if (gdThesisAuditRecord1==null){
            int isAdd = gdThesisAuditRecordService.addGDThesisAuditRecord(gdThesisAuditRecord);
            if (isAdd==1){
                result.setResultCode(200);
                result.setMessage("审核完成");
            }else{
                result.setResultCode(500);
                result.setMessage("审核失败");
            }
        }else{
            int isUpdate = gdThesisAuditRecordService.updateGDThesisAuditRecord(gdThesisAuditRecord);
            if (isUpdate==1){
                result.setResultCode(200);
                result.setMessage("审核完成");
            }else{
                result.setResultCode(500);
                result.setMessage("审核失败");
            }
        }
        return result;
    }
}
