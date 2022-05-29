package com.jiale.thesis.controller.teacher.GDTeam;

import com.jiale.thesis.entity.FileInfo;
import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.GDThesisFinal;
import com.jiale.thesis.entity.GD.GDThesisInput;
import com.jiale.thesis.entity.GD.GDThesisInputSetting;
import com.jiale.thesis.service.FileInfoService;
import com.jiale.thesis.service.GDThesisFinalService;
import com.jiale.thesis.service.GDThesisInputService;
import com.jiale.thesis.service.GDThesisInputSettingService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/teacher/thesisFinalDetail/")
public class ThesisFinalDetailController {

    @Autowired
    GDThesisInputSettingService gdThesisInputSettingService;
    @Autowired
    GDThesisInputService gdThesisInputService;
    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    GDThesisFinalService gdThesisFinalService;


    @RequestMapping("/thesisFinalInputSettingList")
    @ResponseBody
    public Result<List<GDThesisInputSetting>> thesisFinalInputSettingList(){
        Result<List<GDThesisInputSetting>> result = new Result<>();
        List<GDThesisInputSetting> list = gdThesisInputSettingService.findGDThesisInputSettingList();
        if (null!=list){
            result.setResultCode(200);
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }

    @RequestMapping("/thesisFinalInputList")
    @ResponseBody
    public Result<List<GDThesisInput>> thesisFinalInputList(BigInteger studentId){
        Result<List<GDThesisInput>> result = new Result<>();
        GDThesisFinal gdThesisFinal = gdThesisFinalService.findGDThesisByStudentId(studentId);
        List<GDThesisInput> list = gdThesisInputService.findGDThesisInputListByGDThesisId(gdThesisFinal.getId());
        if (null!=list){
            result.setResultCode(200);
            result.setData(list);
        }else{
            result.setResultCode(204);
            result.setMessage("没有找到更多信息");
        }
        return result;
    }

    @RequestMapping("/postThesisFinalAccessoryFileInfo")
    @ResponseBody
    public Result<FileInfo> postThesisFinalAccessoryFileInfo(BigInteger studentId){
        Result<FileInfo> result = new Result<>();
        GDThesisFinal gdThesisFinal = gdThesisFinalService.findGDThesisByStudentId(studentId);
        if (null!=gdThesisFinal){
            FileInfo fileInfo = fileInfoService.findFileInfoByFileId(gdThesisFinal.getAccessoryFileId());
            if (null!=fileInfo){
                result.setResultCode(200);
                result.setData(fileInfo);
            }else{
                result.setResultCode(204);
                result.setMessage("没有找到文件");
            }
        }else{
            result.setResultCode(500);
            result.setMessage("服务错误");
        }
        return result;
    }
}
