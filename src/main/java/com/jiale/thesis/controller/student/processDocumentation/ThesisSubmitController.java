package com.jiale.thesis.controller.student.processDocumentation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.jiale.thesis.entity.GD.GDThesis;
import com.jiale.thesis.entity.GD.GDThesisInput;
import com.jiale.thesis.entity.GD.GDThesisInputSetting;
import com.jiale.thesis.entity.GD.GDTopicFinal;
import com.jiale.thesis.service.GDThesisInputService;
import com.jiale.thesis.service.GDThesisInputSettingService;
import com.jiale.thesis.service.GDThesisService;
import com.jiale.thesis.service.GDTopicFinalService;
import com.jiale.thesis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/student/thesisSubmit")
public class ThesisSubmitController {

    @Autowired
    GDThesisService gdThesisService;
    @Autowired
    GDThesisInputService gdThesisInputService;
    @Autowired
    GDThesisInputSettingService GDThesisInputSettingservice;
    @Autowired
    GDTopicFinalService gdTopicFinalService;

    /*
    1.获取论文页面输入设置
     */
    @RequestMapping("/getThesisInputSettingList")
    @ResponseBody
    public Result<List<GDThesisInputSetting>> getThesisInputSettingList(){
        Result<List<GDThesisInputSetting>> result = new Result<>();

        List<GDThesisInputSetting> list =  GDThesisInputSettingservice.findGDThesisInputSettingList();
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

    /*
    2.通过学生获取论文输入内容
     */
    @RequestMapping("/postThesisInputList")
    @ResponseBody
    public Result<List<GDThesisInput>> postThesisInputList(BigInteger studentId){
        Result<List<GDThesisInput>> result = new Result<>();
        List<GDThesisInput> list = gdThesisInputService.findGDThesisInputListByStudentId(studentId);
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

    /*
    创建新的毕业设计论文
     */
    @RequestMapping("/postSubmitThesis")
    @ResponseBody
    public Result postSubmitThesis(@RequestBody String json) throws UnsupportedEncodingException{
        Result result = new Result();

        String deURL = URLDecoder.decode(json,"UTF-8");
        JSONObject obj = JSONObject.parseObject(deURL);

        BigInteger studentId = BigInteger.valueOf(Long.parseLong((String) obj.get("studentId")));
        GDTopicFinal gdTopicFinal = gdTopicFinalService.findGDTopicFinalByStudentId(studentId);

        GDThesis gdThesis = new GDThesis();
        gdThesis.setTopicFinalId(gdTopicFinal.getId());
        if (!Objects.equals(obj.get("fileInfoId").toString(), "")){
            gdThesis.setAccessoryFileId(Long.parseLong((String) obj.get("fileInfoId")));
        }
        gdThesis.setIsDeleted(0);
        if(gdThesisService.addGDThesis(gdThesis)==1){
            String inputListJSONArrayStr = String.valueOf(obj.get("inputList"));
            JSONArray inputListJSONArray = JSONArray.parseArray(inputListJSONArrayStr);
            for (int i=0;i<inputListJSONArray.size();i++){
                JSONObject object = inputListJSONArray.getJSONObject(i);
                GDThesisInput gdThesisInput = new GDThesisInput();
                gdThesisInput.setGdthesisId(gdThesis.getId());
                gdThesisInput.setGdthesisInputSettingId(Long.parseLong((String) object.get("gdthesisInputSetting")));
                gdThesisInput.setInputContent((String) object.get("inputContent"));
                gdThesisInput.setIsDeleted(0);
                gdThesisInputService.addGDThesisInput(gdThesisInput);
            }
            result.setResultCode(200);
            result.setMessage("提交成功");
        }else{
            result.setResultCode(500);
            result.setMessage("提交失败");
        }
        return result;
    }
}
